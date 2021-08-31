package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.config.DbConfig;
import com.cocoblue.securitytest.dto.Holiday;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// DB의 휴일 데이터가 오래된 경우 백그라운드를 통해 업데이트하는 클래스
@Service
public class HolidayDbUpdateThread implements Runnable {
    private final HolidayService holidayService;

    public HolidayDbUpdateThread(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    // 출처: https://blog.naver.com/birdparang/221436045529
    public void updateHolidayDbFromNia(LocalDate localDate) throws Exception {
        // URL 구성 -> GET 방식으로 요청하므로, Parameter가 노출되어 있음.
        final String urlString = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"
                + "?ServiceKey=" + DbConfig.OPEN_DATA_API_KEY
                + "&solYear=" + localDate.getYear()
                + "&solMonth=" + (localDate.getMonthValue() > 9 ? "" : "0") + localDate.getMonthValue();

        final URL url = new URL(urlString);
        final HttpURLConnection con = (HttpURLConnection)url.openConnection();

        try (AutoCloseable autoCloseable = con::disconnect) {
            con.setRequestProperty("Accept-language", "ko");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(con.getInputStream());
            // header의 값을 받아와서 NodeList에 저장
            NodeList nodeList = doc.getElementsByTagName("header");

            // nodeList의 길이가 0이면, AssertError을 일으킴. 즉, 제대로 받아와지지 않았을 경우 에러를 표출하며 함수를 중단
            assert nodeList.getLength() > 0;
            Element element = (Element) nodeList.item(0);

            // NIA 서버에서 제대로 값을 반환하면, resultCode에 00을 반환함. 즉, 00이면 값 등록을 진행해도 됨.
            if (element.getElementsByTagName("resultCode").item(0).getTextContent().equals("00")) {
                NodeList items = doc.getElementsByTagName("item");

                for(int i = 0; i < items.getLength(); i++)  {
                    // item을 갖고 옴.
                    Element item = (Element) items.item(i);

                    String holidayDateString = item.getElementsByTagName("locdate").item(0).getTextContent(); // 날짜
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    String holidayName = item.getElementsByTagName("dateName").item(0).getTextContent(); // 이름

                    Holiday holiday = new Holiday(LocalDate.parse(holidayDateString, formatter), holidayName, false, LocalDateTime.now());
                    insertToDb(holiday);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void insertToDb(Holiday holiday) {
        try {
            holidayService.insertHoliday(holiday);
        } catch (DuplicateKeyException duplicateKeyException) {
            holidayService.updateHolidayImf(holiday);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        updateHolidayDbFromNia(LocalDate.now());
        updateHolidayDbFromNia(LocalDate.now().plusMonths(1));
    }
}

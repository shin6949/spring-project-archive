package com.cocoblue.securitytest.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import com.cocoblue.securitytest.config.DbConfig;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class HolidayServiceImpl implements HolidayService {
    // 출처: https://blog.naver.com/birdparang/221436045529
    // [in] y : 년
    // [in] m : 월
    // [out] v[i*2 +0]=휴일날짜(YYYYMMDD), v[i*2 +1]=휴일 명칭
    // 반환값 : 에러메시지, null == OK
    public String getItemsFromOpenApi(int year, int month, List<LocalDate> value) throws Exception {
        String errorMessage = null; // 에러 메시지
        final String urlString = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"
                + "?ServiceKey=" + DbConfig.OPEN_DATA_API_KEY
                + "&solYear=" + year
                + "&solMonth=" + (month > 9 ? "" : "0") + month;

        final URL url = new URL(urlString);
        final HttpURLConnection con = (HttpURLConnection)url.openConnection();

        try (AutoCloseable autoCloseable = con::disconnect) {
            con.setRequestProperty("Accept-language", "ko");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(con.getInputStream());

            boolean ok = false; // <resultCode>00</resultCode> 획득 여부

            Element element;
            NodeList nodeList = doc.getElementsByTagName("header");
            if (nodeList.getLength() > 0)  {
                element = (Element) nodeList.item(0);
                if (element.getElementsByTagName("resultCode").item(0).getTextContent().equals("00"))
                    ok = true; // 성공 여부
                else // 에러 메시지
                    errorMessage = element.getElementsByTagName("resultMsg").item(0).getTextContent();
            }

            if (ok) {
                nodeList = doc.getElementsByTagName("item");
                for (int i = 0; i < nodeList.getLength(); i++)  {
                    // item을 갖고 옴.
                    element = (Element) nodeList.item(i);

                    String holidayDateString = element.getElementsByTagName("locdate").item(0).getTextContent(); // 날짜
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

                    value.add(LocalDate.parse(holidayDateString, formatter));
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        return errorMessage;
    }

    @Override
    public Boolean judgeHoliday(List<LocalDate> holidayList, LocalDate localDate) {
        // 일요일인지 판단
        if(localDate.getDayOfWeek().getValue() == 7) {
            return true;
        }

        // 만일, 인터넷 등의 문제로 받아오지 못한 경우 일단 평일로 인정함.
        if(holidayList == null) {
            return false;
        }

        for(LocalDate holiday : holidayList) {
            if(holiday.toString().equals(localDate.toString())) {
                return true;
            }
        }
        return false;
    }
}

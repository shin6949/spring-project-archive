package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.ReservationDao;
import com.cocoblue.securitytest.dto.AvailableDateDto;
import com.cocoblue.securitytest.dto.Doctor;
import com.cocoblue.securitytest.dto.Holiday;
import com.cocoblue.securitytest.dto.Reservation;
import org.omg.PortableInterceptor.HOLDING;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ReservationServiceImpl implements  ReservationService{
    private final ReservationDao reservationDao;
    private final HolidayService holidayService;

    public ReservationServiceImpl(ReservationDao reservationDao, HolidayService holidayService) {
        this.reservationDao = reservationDao;
        this.holidayService = holidayService;
    }

    @Override
    public List<Reservation> getAllConfirmedReservation() {
        return reservationDao.getAllConfirmedReservation();
    }

    @Override
    public List<Reservation> getAllConfirmedReservationByDoctorNo(long doctorNo, LocalDateTime localDateTime) {
        return reservationDao.getAllConfirmedReservationByDoctorNo(doctorNo, localDateTime);
    }

    @Override
    public Boolean makeReservation(Reservation reservation) {
        return reservationDao.makeReservation(reservation);
    }

    @Override
    public Boolean cancelReservation(long rno) {
        return reservationDao.cancelReservation(rno);
    }

    @Override
    public List<AvailableDateDto> getAvailableDate() {
        List<Holiday> holidayList = getRecentHoliday();
        List<AvailableDateDto> availableDateList = new ArrayList<>();

        for(int i = 1; i < 8; i++) {
            LocalDate plusDate = LocalDate.now().plusDays(i);

            if(!holidayService.judgeHoliday(holidayList, plusDate)) {
                availableDateList.add(new AvailableDateDto(plusDate));
            }
        }

        return availableDateList;
    }

    private List<Holiday> getRecentHoliday() {
        // 휴일 리스트를 받아옴.
        List<Holiday> holidayList = holidayService.getHolidaysUntilSevenDaysLater();

        // 받아온 국가 휴일 리스트 중에서 하나라도 갱신된지 하루가 지났으면, 백그라운드에서 NIA에 접속해 정보를 갱신.
        for(Holiday holiday : holidayList) {
            if(!holiday.getCustomDate() && holiday.getRegTime().plusDays(1).isBefore(LocalDateTime.now())) {
                HolidayDbUpdateThread hdt = new HolidayDbUpdateThread(holidayService);
                Thread t = new Thread(hdt,"HolidayDbUpdateThread");
                System.out.println("Thread Called");
                t.start();
                break;
            }
        }

        return holidayList;
    }

    @Override
    public List<String> configureAvailableTime(String dateString, Doctor doctor) {
        List<String> result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDate.parse(dateString, formatter).atStartOfDay();

        // 평일이 아니라면, 토요일 시간표로 구성
        if(date.getDayOfWeek().getValue() != 6) {
            result = new ArrayList<>(Arrays.asList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                                        "14:00", "14:30", "15:00", "15:30", "16:00", "17:00", "17:30"));

        } else {
            result = new ArrayList<>(Arrays.asList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"));
        }

        // 이터레이터를 사용하여 안전한 LIST Element 삭제
        Iterator<String> iter = result.iterator();
        while (iter.hasNext()) {
            String time = iter.next();
            for(Reservation reservation : reservationDao.getAllConfirmedReservationByDoctorNo(doctor.getDoctorNo(), date)) {
                if(reservation.getReservationTime().toLocalTime().toString().equals(time)) {
                    iter.remove();
                    // 한번 일치하면 더 이상 일치하지 않을 것이므로, Reservation 쪽의 for를 종료 시킴
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public long getReservationCount(long cno){
        return reservationDao.getReservationCount(cno);
    }
}

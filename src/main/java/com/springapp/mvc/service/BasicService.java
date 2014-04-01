package com.springapp.mvc.service;

import com.springapp.mvc.dao.AppointmentDAO;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BasicService {
    @Autowired AppointmentDAO appointmentDAO;

    public List<Map<Integer, Object>> findScheduleByVisit(int type, int idNumber, String start, String end) {
        String todayString;
        String lastDayString;
        int daysSize = 7;
        Date now = new Date();
        Calendar today = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();
        today.setTime(now);
        lastDay.setTime(now);
        lastDay.add(Calendar.DATE, daysSize);
        DateFormat myDate = new SimpleDateFormat("yyyy/MM/dd");
        todayString = myDate.format(today.getTime());
        lastDayString = myDate.format(lastDay.getTime());

        List<Visit> visits;
        if (type == 1) {
            visits = appointmentDAO.getAppointmentsByDoctorId(idNumber, todayString, lastDayString);
        } else {
            visits = appointmentDAO.getAppointmentsByPatientId(idNumber, todayString, lastDayString);
        }

        List<Map<Integer, Object>> visitDailySet = new ArrayList<Map<Integer, Object>>();

        for (Visit visit : visits) {
            Date startDate = today.getTime();
            Date endDate = visit.getDate();
            visit.setDayCode(FormatService.findDiffDays(startDate, endDate));
        }

        for (int i = 0; i < daysSize; i++) {
            Map<Integer, Object> schedule = new HashMap<Integer, Object>();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            todayString = df.format(today.getTime());
            schedule.put(0, todayString);

            for (Visit visit : visits) {
                if (visit.getDayCode() == i) {
                    schedule.put(visit.getDateCode(), "filled");
                }
            }

            for (int j = 1; j < 15; j++ ) {
                if (!schedule.containsKey(j)) {
                    schedule.put(j, todayString);
                }
            }
            visitDailySet.add(schedule);
            today.add(Calendar.DATE, 1);
        }
        return visitDailySet;
    }
    public Date getSchedule(Visit visit, String time) {
        List<String> timeList = Arrays.asList(time.split(","));
        String timeValue;
        visit.setDateCode(Integer.parseInt(timeList.get(1).toString()));
        if (timeList.get(1).equals("1")) {timeValue="09:00:00";}
        else if (timeList.get(1).equals("2")) {timeValue="09:30:00";}
        else if (timeList.get(1).equals("3")) {timeValue="10:00:00";}
        else if (timeList.get(1).equals("4")) {timeValue="10:30:00";}
        else if (timeList.get(1).equals("5")) {timeValue="11:00:00";}
        else if (timeList.get(1).equals("6")) {timeValue="11:30:00";}
        else if (timeList.get(1).equals("7")) {timeValue="13:00:00";}
        else if (timeList.get(1).equals("8")) {timeValue="13:30:00";}
        else if (timeList.get(1).equals("9")) {timeValue="14:00:00";}
        else if (timeList.get(1).equals("10")) {timeValue="14:30:00";}
        else if (timeList.get(1).equals("11")) {timeValue="15:00:00";}
        else if (timeList.get(1).equals("12")) {timeValue="15:30:00";}
        else if (timeList.get(1).equals("13")) {timeValue="16:00:00";}
        else {timeValue="16:30:00";}

//        visit.setDate(timeList.get(0));
//        visit.setDate_modified(new Date());
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String dateInString = timeList.get(0) + " " + timeValue;
        try {
            Date dates = format.parse(dateInString);
            return dates;
        } catch (Exception e) {
            return new Date();
        }
    }
    public List<Map<Integer, Object>> findScheduleByVisit(int type, int docId) {
        return findScheduleByVisit(type, docId, null, null);
    }
}

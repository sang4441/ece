package com.springapp.mvc.service;

import com.springapp.mvc.dao.BasicDAO;
import com.springapp.mvc.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BasicService {
    @Autowired BasicDAO basicDAO;

    public List<Map<Integer, Object>> findScheduleByDoctorId(int docId, String start, String end) {
        String todayString;
        String lastDayString;
        int daysSize = 5;
        Date now = new Date();
        Calendar today = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();
        today.setTime(now);
        lastDay.setTime(now);
        lastDay.add(Calendar.DATE, daysSize);
        DateFormat myDate = new SimpleDateFormat("yyyy/MM/dd");
        todayString = myDate.format(today.getTime());
        lastDayString = myDate.format(lastDay.getTime());
//        if (start == null || end == null) {
//            todayString = start;
//            lastDayString = end;
//        } else {
//
//        }

        List<Visit> visits = basicDAO.getAppointmentsByDoctorId(docId, todayString, lastDayString);


        List<Map<Integer, Object>> visitDailySet = new ArrayList<Map<Integer, Object>>();

        for (Visit visit : visits) {
            Date startDate = today.getTime();
            Date endDate = visit.getDate();
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            long diffTime = endTime - startTime;
            long diffDays = (long) Math.ceil(diffTime / (1000.0 * 60.0 * 60.0 * 24.0));
            visit.setDayCode(diffDays);
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
    public List<Map<Integer, Object>> findScheduleByDoctorId(int docId) {
        return findScheduleByDoctorId(docId, null, null);
    }
}

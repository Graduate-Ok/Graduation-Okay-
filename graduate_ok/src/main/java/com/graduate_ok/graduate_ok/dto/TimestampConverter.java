package com.graduate_ok.graduate_ok.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampConverter {

    public static Timestamp timestampConverter(Timestamp input) { // String input
        Timestamp timestamp = Timestamp.valueOf("2022-09-20 01:23:45");

        try {
            //Long original = Long.parseLong(input);
            String str = input.toString().replaceAll("[^0-9]", "");
            Long original = Long.parseLong(str);
            Date date = new Date(original * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9"));
            timestamp = new Timestamp(sdf.parse(String.valueOf(date)).getTime());

//            String test = sdf.format(date);
//            timestamp = Timestamp.valueOf(test);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return timestamp;
    }
}

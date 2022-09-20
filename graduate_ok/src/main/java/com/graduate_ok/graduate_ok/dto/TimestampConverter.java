package com.graduate_ok.graduate_ok.dto;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampConverter {

    public static Timestamp timestampConverter(Timestamp input) { // String input
        Timestamp timestamp = Timestamp.valueOf("2022-09-20 01:23:45");

        // test
//        System.out.println("input : " + input + ", type : " + input.getClass().getName());

        try {
            //Long original = Long.parseLong(input);
            String str = input.toString().replaceAll("[^0-9]", "");
            Long original = Long.parseLong(str);
            // test
//            System.out.println("original : " + original + ", type : " + original.getClass().getName());
            Date date = new Date(original * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9"));
            timestamp = new Timestamp(sdf.parse(String.valueOf(date)).getTime());

//            String test = sdf.format(date);
//            // test
////            System.out.println("sdf.format(date) : " + sdf.format(date));
//            timestamp = Timestamp.valueOf(test);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // test
//        System.out.println("timestamp : " + timestamp + ", type : " + timestamp.getClass().getName());

        return timestamp;
    }
}

package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by lixian on 2016/6/3.
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date StartDate = new Date();
            String StartString = df.format(StartDate);
            Date newStartDate = df.parse(StartString);
            long startTime = newStartDate.getTime();

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(newStartDate); //你自己的数据进行类型转换
            calendar.add(calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
            long endTime = calendar.getTime().getTime() - 1;


            System.out.print("startTime:"+startTime+","+"endTime:"+endTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

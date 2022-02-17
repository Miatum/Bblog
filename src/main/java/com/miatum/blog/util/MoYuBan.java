package com.miatum.blog.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.util.Calendar;

/**
 * @Description
 * @ClassName MoYuBan
 * @Author Miatum
 * @date 2022.02.17 09:39
 */
@Service
public class MoYuBan {
    private int month;
    private int date;
    private String interval;
    private int daysAfterWork;
    private int toWeekend;
    private int toQingMing;
    private int toWuYi;
    private int toDuanWu;
    private int toZhongQiu;
    private int toGuoQing;
    private int toYuanDan;
    private int toChunJie;
    public String getMessage(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        this.month = localDate.get(ChronoField.MONTH_OF_YEAR);
        this.date = localDate.getDayOfMonth();
        if (localTime.getHour() < 12){
            this.interval = "上午";
        } else if (localTime.getHour() <18){
            this.interval = "下午";
        } else {
            this.interval = "晚上";
        }
        LocalDate startWorkDate = LocalDate.of(2022, 02, 07);
        this.daysAfterWork = (int)(localDate.toEpochDay() - startWorkDate.toEpochDay());
        this.toWeekend = 6 - localDate.get(ChronoField.DAY_OF_WEEK);
        String message = String.format("【摸鱼办】提醒您：%d月%d日%s好，摸鱼人！即使今天是开工第%d天，也一定不要忘记摸鱼哦！有事没事起身去茶水间，去厕所，去廊道走走别老在工位上坐着，钱是老板的,但命是自己的。\n" +
                "距离【周末】还有:%d天\n" +
                "距离【清明】还有:%d天\n" +
                "距离【五一】还有:%d天\n" +
                "距离【端午】还有:%d天\n" +
                "距离【中秋】还有:%d天\n" +
                "距离【国庆】还有:%d天\n" +
                "距离【元旦】还有:%d天\n" +
                "距离【春节】还有:%d天", month, date,interval, daysAfterWork, toWeekend, toQingMing, toWuYi, toDuanWu, toZhongQiu, toGuoQing, toYuanDan, toChunJie);
        System.out.print(message);
        return message;
    }
}

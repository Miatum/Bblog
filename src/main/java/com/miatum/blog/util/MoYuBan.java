package com.miatum.blog.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

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
        // 获取当前日期
        LocalDate localDate = LocalDate.now();
        // 获取当前时间
        LocalTime localTime = LocalTime.now();
        // 获取月份
        this.month = localDate.get(ChronoField.MONTH_OF_YEAR);
        // 获取号数
        this.date = localDate.getDayOfMonth();
        // 获取时间段
        if (localTime.getHour() < 12){
            this.interval = "上午";
        } else if (localTime.getHour() <18){
            this.interval = "下午";
        } else {
            this.interval = "晚上";
        }
        // 设置开工日期
        LocalDate startWorkDate = LocalDate.of(2022, 02, 07);
        // 计算已开工天数
        this.daysAfterWork = (int)(localDate.toEpochDay() - startWorkDate.toEpochDay());
        // 计算到周末的时间
        this.toWeekend = 6 - localDate.get(ChronoField.DAY_OF_WEEK);
        // 计算中国传统节假日对应的日期
        

        // 计算到清明的天数
        
        // 计算到五一的天数
        LocalDate WuYi = LocalDate.of(localDate.getYear(), 5, 1);
        this.toWuYi = (int) (WuYi.toEpochDay() - localDate.toEpochDay());
        //计算到国庆的天数
        LocalDate GuoQing = LocalDate.of(localDate.getYear(), 10, 1);
        this.toGuoQing = (int) (GuoQing.toEpochDay() - localDate.toEpochDay());
        // int chineseDate = 14 * Q + 10.6 * ();
        // 生成结果
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

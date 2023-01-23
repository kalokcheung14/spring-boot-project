package com.example.actuatorservice.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
    // Cron expression reference:
    // https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm
    @Scheduled(cron = "0/10 * * * * ?")
    public void cronJobSch() {
        System.out.println("Java cron job expression: " + getDateStr());
    }

    @Scheduled(fixedRate = 1000)
    public void fixedRateSch() {
        System.out.println("Fixed rate scheduler: " + getDateStr());
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 2000)
    public void fixedDelaySch() {
        System.out.println("Fixed delay scheduler: " + getDateStr());
    }

    private String getDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        return simpleDateFormat.format(now);
    }
}

package com.ereport.master.sheduler;

import com.ereport.master.kenML.service.PublicationsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
@AllArgsConstructor
public class Scheduler {
    private final PublicationsService publicationsService;
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "0 * * * * *") //Every minute
    private void checkGenerationDate() throws IOException, InterruptedException {
//        System.out.println("Scheduler");
        publicationsService.createPublicationByScheduler();
    }

    @Scheduled(cron = "0 * * * * *") //Every minute
    private void checkSendingDate() throws ParseException, IOException {
        publicationsService.sendPublicationByScheduler();
    }
}

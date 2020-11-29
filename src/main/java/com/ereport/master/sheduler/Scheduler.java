package com.ereport.master.sheduler;

import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.service.PublicationsService;
import com.ereport.master.service.ReportService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@AllArgsConstructor
public class Scheduler {
    private final ReportService reportService;
    private final PublicationsService publicationsService;
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "0 * * * * *") //Every minute
    private void checkGenerationDate() throws ServiceException, ParseException {
        logger.info("CHECK GENERATION DATE");
//        publicationsService.createPublicationByScheduler();
    }

    @Scheduled(cron = "0 * * * * *") //Every minute
    private void checkSendingDate() throws ServiceException, ParseException {
        logger.info("CHECK SENDING DATE");
//        publicationsService.sendPublicationByScheduler();
    }
}

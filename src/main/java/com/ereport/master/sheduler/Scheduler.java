package com.ereport.master.sheduler;

import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.kenML.service.PublicationsService;
import com.ereport.master.service.HtmlToPdfService;
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
    private final HtmlToPdfService htmlToPdfService;
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "0 * * * * *") //Every minute
    private void checkGenerationDate() throws ServiceException, ParseException, IOException, InterruptedException {
        logger.info("KUKA KUKA || CHECK GENERATION DATE ");
//        String s = htmlToPdfService.generate();
//        System.out.println("PDF name " + s);
//        publicationsService.createPublicationByScheduler();
    }

    @Scheduled(cron = "0 * * * * *") //Every minute
    private void checkSendingDate() throws ServiceException, ParseException {
        logger.info("CHECK SENDING DATE");
//        publicationsService.sendPublicationByScheduler();
    }
}

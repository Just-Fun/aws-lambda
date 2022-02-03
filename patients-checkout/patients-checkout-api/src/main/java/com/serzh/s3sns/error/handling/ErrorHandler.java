package com.serzh.s3sns.error.handling;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    public void handler(SNSEvent event) {
        event.getRecords().forEach(snsRecord ->
                logger.info(String.format("Dead Letter Queue Event%s", snsRecord.toString()))
        );
    }

}

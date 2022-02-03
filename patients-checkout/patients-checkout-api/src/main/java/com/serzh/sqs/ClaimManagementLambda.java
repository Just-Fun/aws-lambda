package com.serzh.sqs;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClaimManagementLambda {

    private static final Logger logger = LoggerFactory.getLogger(ClaimManagementLambda.class);

    public void handler(SQSEvent event) {
        event.getRecords().forEach(message -> logger.info(message.getBody()));
    }

}

package com.serzh.s3sns;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillManagementLambda {

	private static final Logger logger = LoggerFactory.getLogger(BillManagementLambda.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	public void handler(SNSEvent event) {
		event.getRecords().forEach(snsRecord -> {
			try {
				PatientCheckoutEvent patientCheckoutEvent = objectMapper.readValue(snsRecord.getSNS().getMessage(), PatientCheckoutEvent.class);
				logger.info(patientCheckoutEvent.toString());
			} catch (JsonProcessingException e) {
				logger.error("Unable to handle event.", e);
				throw new RuntimeException("Error while processing SNS event", e);
			}
		});
	}

}

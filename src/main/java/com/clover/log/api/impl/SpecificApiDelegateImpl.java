package com.clover.log.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clover.log.api.SpecificApiDelegate;
import com.clover.log.model.LogRequest;





public class SpecificApiDelegateImpl implements SpecificApiDelegate {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public @ResponseBody ResponseEntity<Void> specific(@RequestBody LogRequest logRequest) {
		logRequest.getLogs().forEach(log->{
			logger.info(
				"{},{},{},{},{},{},{}", 
				log.getVersionNumber(),
				log.getClassName(),
				log.getMethodName(),
				log.getEventTime(),
				log.getSerialNumber(),
				log.getRequestUuid(),
				logRequest.getPackageName()
			);
		});
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

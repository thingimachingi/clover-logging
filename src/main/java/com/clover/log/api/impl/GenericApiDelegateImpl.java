package com.clover.log.api.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.clover.log.api.GenericApiDelegate;

public class GenericApiDelegateImpl implements GenericApiDelegate {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public ResponseEntity<Void> generic(List<Object> requestBody) {
		// TODO Auto-generated method stub
		requestBody.forEach(
			o-> {
				logger.info("Type of o is {}",o.getClass());
				logger.info(o.toString());
			}
		);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

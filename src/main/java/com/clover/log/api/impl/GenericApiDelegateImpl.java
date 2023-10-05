package com.clover.log.api.impl;

import java.util.List;

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
		requestBody.forEach(o->logger.info(o.toString()));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

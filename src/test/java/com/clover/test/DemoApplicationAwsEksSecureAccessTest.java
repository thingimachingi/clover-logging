package com.clover.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.util.MultiValueMap;

import com.clover.log.model.LogInfo;
import com.clover.log.model.LogRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoApplicationAwsEksSecureAccessTest {
	
	private static final String GZIP = "gzip";

	private static final String AWS_ELB_DNS="a12a7a709a492475f936e78037b31778-509849875.us-east-2.elb.amazonaws.com";
	@Autowired
	TestRestTemplate testRestTemplate;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Value(value="${local.server.port}")
	private int port;
	@Autowired
	private AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager;

	private OAuth2AccessToken accessToken;
	
	@Test
	void contextLoads() {
		assertNotNull(authorizedClientServiceAndManager);
	}
	
	
	@BeforeEach
	void beforeEachTest() {
		OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("okta")
				.principal("Mohan Krishnamurthy")
				.build();
		
		OAuth2AuthorizedClient authorizedClient = this.authorizedClientServiceAndManager.authorize(authorizeRequest);
		assertNotNull(authorizedClient);
		accessToken = Objects.requireNonNull(authorizedClient).getAccessToken();
		assertNotNull(accessToken);
		
	}
	
	@Test
	void testSpecificApi() throws JsonProcessingException {
		LogRequest logRequest = new LogRequest();
		logRequest.setPackageName("pkgName1");
		List<LogInfo> logs = new ArrayList<>();
		LogInfo logInfo = new LogInfo();
		logInfo.eventTime(12312312312L).className("clzA").methodName("methodA").versionNumber(123).serialNumber("ser123").requestUuid("req123");
		logs.add(logInfo );
		logRequest.setLogs(logs );
		String logRequestJson = objectMapper.writeValueAsString(logRequest);
		logger.info(logRequestJson);
		String url = "http://"+AWS_ELB_DNS+"/specific";
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken.getTokenValue());
		//String jsonRequest = "{packageName:\"pkgName1\", logs:[{versionNumber:10,className:\"clzA\",methodName:\"methodA\",eventTime:12312312312,serialNumber:\"ser123\",requestUuid:\"req123\"}]}"; 
		HttpEntity<String> request = new HttpEntity<String>(logRequestJson, headers );
		ResponseEntity<?> response = this.testRestTemplate.postForEntity(url, request,ResponseEntity.class);
		
		
		assertTrue(response.getStatusCode()==HttpStatus.OK);
		
	}
	@Test
	void testSpecificApiCompressed() throws IOException {
		
		LogRequest logRequest = new LogRequest();
		logRequest.setPackageName("pkgName1");
		List<LogInfo> logs = new ArrayList<>();
		LogInfo logInfo = new LogInfo();
		logInfo.eventTime(12312312312L).className("clzA").methodName("methodA").versionNumber(123).serialNumber("ser123").requestUuid("req123");
		logs.add(logInfo );
		logRequest.setLogs(logs );
		String logRequestJson = objectMapper.writeValueAsString(logRequest);
		
		byte[] logRequestJsonBytes = logRequestJson.getBytes();
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bytesOut)) {
	        gzipOutputStream.write(logRequestJsonBytes);
	    }
		String url = "http://"+AWS_ELB_DNS+"/specific";
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_ENCODING, GZIP);
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken.getTokenValue());
		byte[] byteArray = bytesOut.toByteArray();
		
		File tmpFile = Files.newTemporaryFile();
		logger.info("tmpFile = "+tmpFile.getAbsolutePath());
		
		try (FileOutputStream out = new FileOutputStream(tmpFile);) {
			out.write(byteArray);
		}
		
		HttpEntity<byte[]> request = new HttpEntity<byte[]>(byteArray, headers );
		System.out.println("----");
		for (byte b: byteArray) System.out.print(b);
		System.out.println(byteArray);
		System.out.println("----");
		ResponseEntity<?> response = this.testRestTemplate.postForEntity(url, request,ResponseEntity.class);
		
		
		assertTrue(response.getStatusCode()==HttpStatus.OK);
		
	}
	@Test
	void testGenericApi() throws JsonProcessingException {
		
		List<Map<String, String>> data  = new ArrayList<>();
		Map<String, String> 	  data1 = new HashMap<>();
		data1.put("random", "test");
		data1.put("dataSet", "1");
		data1.put("type", "Unknown");
		data1.put("songName", "TwinkleTwinkle");
		data1.put("artistName", "unsure");
		data1.put("source", "none");
		
		Map<String, String> data2 = new HashMap<>();
		data2.put("football", "Spurs");
		data2.put("olympics", "gold");
		data2.put("player", "Messi");
		data2.put("league", "All Star");
		
		data.add(data1);
		data.add(data2);
		String logRequestJson = objectMapper.writeValueAsString(data);
		logger.debug(logRequestJson);
		String url = "http://"+AWS_ELB_DNS+"/generic";
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken.getTokenValue());
		HttpEntity<String> request = new HttpEntity<>(logRequestJson, headers );
		ResponseEntity<?> response = this.testRestTemplate.postForEntity(url, request,ResponseEntity.class);
		
		
		assertTrue(response.getStatusCode()==HttpStatus.OK);
		
	}

	@Test
	void testGenericApiCompressed() throws IOException {
		
		List<Map<String, String>> data = new ArrayList<>();
		Map<String, String> 	  data1 = new HashMap<>();
		data1.put("random", "test");
		data1.put("dataSet", "1");
		data1.put("type", "Unknown");
		data1.put("songName", "TwinkleTwinkle");
		data1.put("artistName", "unsure");
		data1.put("source", "none");
		
		Map<String, String> data2 = new HashMap<>();
		data2.put("football", "Spurs");
		data2.put("olympics", "gold");
		data2.put("player", "Messi");
		data2.put("league", "All Star");
		data.add(data1);
		data.add(data2);
		String logRequestJson = objectMapper.writeValueAsString(data);
		byte[] logRequestJsonBytes = logRequestJson.getBytes();
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bytesOut)) {
	        gzipOutputStream.write(logRequestJsonBytes);
	    }
		String url = "http://"+AWS_ELB_DNS+"/generic";
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_ENCODING, GZIP);
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken.getTokenValue());
		HttpEntity<byte[]> request = new HttpEntity<byte[]>(bytesOut.toByteArray(), headers );
		ResponseEntity<?> response = this.testRestTemplate.postForEntity(url, request,ResponseEntity.class);
		
		
		assertTrue(response.getStatusCode()==HttpStatus.OK);
		
		
	}
	
}

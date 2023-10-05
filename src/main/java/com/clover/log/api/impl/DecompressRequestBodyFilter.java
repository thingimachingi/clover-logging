package com.clover.log.api.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.zip.GZIPInputStream;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class DecompressRequestBodyFilter extends OncePerRequestFilter{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		logger.info("DecompressRequestBodyFilter called");
        boolean isGzipped = request.getHeader(HttpHeaders.CONTENT_ENCODING) != null
                && request.getHeader(HttpHeaders.CONTENT_ENCODING).contains("gzip");
        
        HttpServletRequestWrapper requestWrapper = null;
        if (isGzipped) {
        	logger.info("DecompressRequestBodyFilter: gzip content encoding found");
        	requestWrapper = new DecompressedRequestWrapper(request);
        } else {
        	requestWrapper = new HttpServletRequestWrapper(request);
        }
        super.doFilter(requestWrapper, response, filterChain);

		
	}
	
	final class DecompressedRequestWrapper extends HttpServletRequestWrapper {

		public DecompressedRequestWrapper(HttpServletRequest request) {
			super(request);
		}


		@Override
		public ServletInputStream getInputStream() throws IOException {
			
			try (GZIPInputStream gis = new GZIPInputStream(super.getInputStream()) ){
	            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len;
	            try {
	            while((len = gis.read(buffer)) != -1) {
	                bytesOut.write(buffer,0,len);
	            }
	            } catch (EOFException err) {
	            	logger.error("EOFEx", err);
	            }
	            
	            
	            return new BodyInputStream(bytesOut.toByteArray());
			}
		}
		
	}
	
	private static class BodyInputStream extends ServletInputStream {

		private final InputStream delegate;

		public BodyInputStream(byte[] body) {
			this.delegate = new ByteArrayInputStream(body);
		}

		@Override
		public boolean isFinished() {
			return false;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int read() throws IOException {
			return this.delegate.read();
		}

		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			return this.delegate.read(b, off, len);
		}

		@Override
		public int read(byte[] b) throws IOException {
			return this.delegate.read(b);
		}

		@Override
		public long skip(long n) throws IOException {
			return this.delegate.skip(n);
		}

		@Override
		public int available() throws IOException {
			return this.delegate.available();
		}

		@Override
		public void close() throws IOException {
			this.delegate.close();
		}

		@Override
		public synchronized void mark(int readlimit) {
			this.delegate.mark(readlimit);
		}

		@Override
		public synchronized void reset() throws IOException {
			this.delegate.reset();
		}

		@Override
		public boolean markSupported() {
			return this.delegate.markSupported();
		}
	}
	
}

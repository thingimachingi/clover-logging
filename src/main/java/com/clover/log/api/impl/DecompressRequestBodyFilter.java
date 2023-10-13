package com.clover.log.api.impl;

import java.io.IOException;
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
import org.springframework.web.filter.OncePerRequestFilter;


public class DecompressRequestBodyFilter extends OncePerRequestFilter{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		logger.info("DecompressRequestBodyFilter2 called. mappingJackson2HttpMessageConverter = ");
        boolean isGzipped = request.getHeader(HttpHeaders.CONTENT_ENCODING) != null
                && request.getHeader(HttpHeaders.CONTENT_ENCODING).contains("gzip");
        
        HttpServletRequestWrapper requestWrapper = null;
        if (isGzipped) {
        	logger.info("DecompressRequestBodyFilter2: gzip content encoding found");
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
            return new BodyInputStream(new GZIPInputStream(super.getInputStream()));
		}
		
		
	}
	
	private static class BodyInputStream extends ServletInputStream {

		private final GZIPInputStream delegate;

		public BodyInputStream(GZIPInputStream delegate) {
			this.delegate = delegate;
		}

		@Override
		public boolean isFinished() {
			try {
				return delegate.available()==0;
			} catch (IOException e) {
				return true;
			}
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

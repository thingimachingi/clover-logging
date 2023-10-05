package com.clover;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class Decomp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (GZIPInputStream gis =  new GZIPInputStream( new FileInputStream(new File("E:/Clover/demo/specific.gz")));) {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int len;
            try {
	            while((len = gis.read(buffer)) != -1) {
	                bytesOut.write(buffer,0,len);
	            }
	            String s = new String(bytesOut.toByteArray());
	            System.out.println(s);
            
            } catch (EOFException err) {
            	err.printStackTrace();
            }

		}

	}

}

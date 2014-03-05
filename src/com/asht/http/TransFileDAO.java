package com.asht.http;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;



import android.os.AsyncTask;

import com.asht.model.Resume;
import com.asht.utl.Settings;

public class TransFileDAO {
	String url = Settings.WEB_SERVER;
	
	public static String transImageToString(String imagePath){
		try {
			FileInputStream fis = new FileInputStream(imagePath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int count = 0;
			while( (count = fis.read(buffer)) >= 0){
				baos.write(buffer, 0, count);
			}
			String uploadBuffer = new String(Base64.encode(baos.toByteArray()));
			fis.close();
			return uploadBuffer;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static boolean saveImage(String name,String type,String image){
		FileOutputStream fos = null;
		try {
			byte[] buffer = new Base64().decode(image);
			fos = new FileOutputStream("/mnt/sdcard/"+name+type);
			fos.write(buffer);
			fos.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}

package com.asht.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.asht.model.Resume;
import com.asht.utl.ConnCallback;

public class CasePictureDAO {
	private static String URL_NAME = "TbMedicalrecordGroup";
	
	/**
	 * 上传单张图片
	 * @param callback
	 * @param medicalRecordItemId 病例组id
	 * @param resume
	 */
	public static void upload( ConnCallback callback, Resume resume, int medicalRecordItemId){
		String name = "newUploadMedicalRecordItemsToGroup";
//		String name = "demo";
		JSONObject param = new JSONObject();
		try {
			resume.setRecordItemId(medicalRecordItemId);
			param.put("data", resume.toJson());
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());		
	}
	
	
}

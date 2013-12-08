package com.asht.http;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import com.asht.utl.Settings;

public class HttpClient {
	static String NAMESPACE = null;;
	static String SERVICEURL = null;;
	static {
//		NAMESPACE = Settings.NAMESPACE;
		NAMESPACE = "http://CXFWebservice.modules.www.ascs.com/";
//		SERVICEURL = Settings.WEB_SERVER;
	}
	
	public AshtResponse httpRequest(String url, PostParameter[] postParams, String method) {
		AshtResponse res = null;
		try {
			// 构造SoapHeader
			// 用于验证是否有接口调用权限==========================================

			Element header = new Element();
			header = new Element().createElement(NAMESPACE, "SoapHeader");

			Element name = new Element().createElement(NAMESPACE, "name");
			name.addChild(Node.TEXT, "admin");
			header.addChild(Node.ELEMENT, name);

			Element pass = new Element().createElement(NAMESPACE, "password");
			pass.addChild(Node.TEXT, "admin123456");
			header.addChild(Node.ELEMENT, pass);

			// ====================================================
			
			SoapObject rpc = new SoapObject(NAMESPACE, method);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("username", "admin");
			jsonObject.put("pwd", "123456");
			rpc.addProperty("username","admin");
			rpc.addProperty("pwd","123456");
//			rpc.addProperty("json", jsonObject.toString());
			System.out.println(" fa song "+ rpc.getPropertyCount()+" ");
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.encodingStyle = "UTF-8";
			
//			envelope.headerOut = new Element[] { header };
			envelope.bodyOut = rpc;

			HttpTransportSE ht = new HttpTransportSE(url);

			ht.call(null, envelope);
			// 此处如果用soapobject会报错
			SoapPrimitive detail = (SoapPrimitive) envelope.getResponse();
			System.out.println("rs : " + detail.toString());
			return new AshtResponse( detail.toString() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}

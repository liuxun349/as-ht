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

import com.asht.AsHtException;
import com.asht.utl.Settings;

public class HttpClient {
	
	public static final String ERROR = "错误";
	
	private String NAMESPACE = null; 
	private String SERVICEURL = null; 
	public HttpClient(String nameSpace,String serviceUrl){
		this.NAMESPACE = nameSpace;
		this.SERVICEURL = serviceUrl;
	}
	public AshtResponse get(String method,String json) throws AsHtException{
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
			System.out.println(" json : "+ json+" "+NAMESPACE+ " "+ SERVICEURL);
			rpc.addProperty("json", json);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.encodingStyle = "UTF-8";
			
			envelope.headerOut = new Element[] { header };
			envelope.bodyOut = rpc;

			HttpTransportSE ht = new HttpTransportSE(SERVICEURL+method);

			ht.call(null, envelope);
			// 此处如果用soapobject会报错
			SoapPrimitive detail = (SoapPrimitive) envelope.getResponse();
			System.out.println("rs : " + detail.toString());
			return AshtResponse.getResponse( detail.toString() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AsHtException(Settings.NET_CONN_ERROR, Settings.RETURN_CODE_FAILED);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			throw new AsHtException(Settings.NET_CONN_ERROR, Settings.RETURN_CODE_FAILED);
		}
	}
}

package com.asht.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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

	public HttpClient(String nameSpace, String serviceUrl) {
		this.NAMESPACE = nameSpace;
		this.SERVICEURL = serviceUrl;
	}

	public AshtResponse get(String method, String json) throws AsHtException {
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
			System.out.println(" json : " + json + " " + NAMESPACE + " "
					+ SERVICEURL);
			rpc.addProperty("json", json);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.encodingStyle = "UTF-8";

			envelope.headerOut = new Element[] { header };
			envelope.bodyOut = rpc;

			HttpTransportSE ht = new HttpTransportSE(SERVICEURL + method,
					5 * 1000);

			ht.call(null, envelope);
			// 此处如果用soapobject会报错
			SoapPrimitive detail = (SoapPrimitive) envelope.getResponse();
			System.out.println("rs : " + detail.toString());
			return AshtResponse.getResponse(detail.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new AsHtException(Settings.NET_CONN_ERROR,
					Settings.RETURN_CODE_FAILED);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			throw new AsHtException(Settings.NET_CONN_ERROR,
					Settings.RETURN_CODE_FAILED);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" 未知。。 ");
			throw new AsHtException(Settings.NET_CONN_ERROR,
					Settings.RETURN_CODE_FAILED);

		}
	}

	/**
	 * 网络连接部分
	 * 
	 * @param name
	 *            接口名称
	 * @param param
	 *            发送的JSON数据包
	 * @return
	 */
	public String downloadFile(String fileAddress) {
		// File result = new File(filePath+"/"+fileName);
		String result = null;
		String url = SERVICEURL + fileAddress;
		HttpPost request = new HttpPost(url);

		StringEntity se;
		try {
			// 绑定到请求 Entry
			// se = new StringEntity(param.toString());
			// request.setEntity(se);
			// 发送请求
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(request);
			// 得到应答的字符串，这也是一个JSON格式保存的数据
			String retSrc = EntityUtils.toString(httpResponse.getEntity());
			// 生成JSON对象
			result = retSrc;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

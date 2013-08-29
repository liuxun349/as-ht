package com.asht.dao;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.asht.utl.ConnCallback;
import com.asht.utl.Settings;

import android.os.AsyncTask;

public class ConnServer extends AsyncTask<String, Void, String> {
	static String NAMESPACE = null;;
	static String SERVICEURL = null;;
	static {
		NAMESPACE = Settings.NAMESPACE;
		SERVICEURL = Settings.WEB_SERVER;
	}
	ConnCallback ccb = null;

	String url;
	String method;

	public ConnServer(String className, String method, ConnCallback ccb) {
		url = SERVICEURL + className;
		this.method = method;
		this.ccb = ccb;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (ccb != null) {
			ccb.connCode(ConnCallback.CODE_START, null);
		}
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpTransportSE hts = new HttpTransportSE(url);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		SoapObject request = new SoapObject(NAMESPACE, method);

		request.addProperty("text", params[0]);
		envelope.bodyOut = request;
		envelope.dotNet = false;

		try {
			hts.call(null, envelope);

			// 获取返回的数据
			SoapObject object = (SoapObject) envelope.bodyIn;
			// 获取返回的结果
			if (object != null) {
				String result = object.getProperty(0).toString();

				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		if (ccb != null) {
			ccb.connCode(ConnCallback.CODE_STOP, result);
		}
	}

}

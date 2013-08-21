package com.asht.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.asht.utl.Settings;

public class NetworkConnectionDAO {
	/**
	 * 网络连接部分
	 * @param name	接口名称
	 * @param param	发送的JSON数据包
	 * @return	
	 */
	public static JSONObject connection(String name, JSONObject param) {
		JSONObject result = null;
		String url = Settings.WEB_SERVER + name;
		HttpPost request = new HttpPost(url);

		StringEntity se;
		try {
			// 绑定到请求 Entry
			se = new StringEntity(param.toString());
			request.setEntity(se);
			// 发送请求
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(request);
			// 得到应答的字符串，这也是一个JSON格式保存的数据
			String retSrc = EntityUtils.toString(httpResponse.getEntity());
			// 生成JSON对象
			result = new JSONObject(retSrc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

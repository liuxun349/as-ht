package com.asht;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 异步数据加载
 * 
 * @author starry
 * 
 */
public class AsyncDataLoader extends AsyncTask<Void, Long, Object> {
	private AsyncDataLoader.Callback mCallback;

	public AsyncDataLoader(AsyncDataLoader.Callback callback) {
		setCallback(callback);
	}

	@Override
	protected Object doInBackground(Void... voids) {
		if (mCallback != null) {
			mCallback.onStartAsync();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (mCallback != null) {
			mCallback.onFinishAsync();
		}
	}
	
	@Override
	protected void onProgressUpdate(Long... values) {
		super.onProgressUpdate(values);
		System.out.println("yj_onProgressUpdate"+values);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (mCallback != null) {
			mCallback.onPrepareAsync();
		}
	}

	public void setCallback(AsyncDataLoader.Callback callback) {
		this.mCallback = callback;
	}

	public interface Callback {
		public void onPrepareAsync();

		public void onStartAsync();

		public void onFinishAsync();
	}

	void Log(String msg) {
		Log.i("asht", "Asyc---" + msg);
	}

}

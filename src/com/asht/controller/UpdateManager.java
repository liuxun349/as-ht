package com.asht.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AshtSettings;
import com.asht.R;
import com.asht.model.AppInfo;
import com.asht.utl.Settings;
import com.asht.view.WaitingDialog;

/**
 * APK更新管理类
 * 
 * @author Royal
 * 
 */
public class UpdateManager {

	// 上下文对象
	private Context mContext;
	// // 更新版本信息对象
	private AppInfo info = null;
	// 下载进度条
	private ProgressBar progressBar;
	// 是否终止下载
	private boolean isInterceptDownload = false;
	// 进度条显示数值
	private int progress = 0;

	private WaitingDialog waitingDialog;

	/**
	 * 参数为Context(上下文activity)的构造函数
	 * 
	 * @param context
	 */
	public UpdateManager(final Context context) {
		this.mContext = context;

	}

	public void checkUpdate() {
		waitingDialog = new WaitingDialog(mContext);
		waitingDialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 从服务端获取版本信息
				try {
					info = getVersionInfoFromServer();
				} catch (AsHtException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Message msg = new Message();
				msg.what = 2;
				msg.obj = info;
				handler.sendMessage(msg);
			}
		}).start();

	}

	/**
	 * 从服务端获取版本信息
	 * 
	 * @return
	 * @throws AsHtException
	 */
	private AppInfo getVersionInfoFromServer() throws AsHtException {
		return AsHt.getInstance().getProgramVersionInfo();
	}

	/**
	 * 提示更新对话框
	 * 
	 * @param info
	 *            版本信息对象
	 */
	private void showUpdateDialog() {
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		Builder builder = new Builder(mContext);
		builder.setTitle("版本更新");
		builder.setMessage("更新功能：" + info.txtversionnnote);
		builder.setPositiveButton("下载", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// 弹出下载框
				showDownloadDialog();
			}
		});
		builder.setNegativeButton("以后再说", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();
	}

	/**
	 * 弹出下载框
	 */
	private void showDownloadDialog() {

		Builder builder = new Builder(mContext);
		builder.setTitle("版本更新中...");
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.update_progress, null);
		progressBar = (ProgressBar) v.findViewById(R.id.pb_update_progress);
		builder.setView(v);
		builder.setNegativeButton("取消", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// 终止下载
				isInterceptDownload = true;
			}
		});
		builder.create().show();
		// 下载apk
		downloadApk();
	}

	/**
	 * 下载apk
	 */
	private void downloadApk() {
		// 开启另一线程下载
		Thread downLoadThread = new Thread(downApkRunnable);
		downLoadThread.start();
	}

	/**
	 * 从服务器下载新版apk的线程
	 */
	private Runnable downApkRunnable = new Runnable() {
		@Override
		public void run() {
			if (!android.os.Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {
				// 如果没有SD卡
				Builder builder = new Builder(mContext);
				builder.setTitle("提示");
				builder.setMessage("当前设备无SD卡，数据无法下载");
				builder.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.show();
				return;
			} else {
				try {
					// 服务器上新版apk地址
					URL url = new URL(Settings.WEB_URL
							+ info.txtdownloadaddress);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					int length = conn.getContentLength();
					InputStream is = conn.getInputStream();
					File file = new File(Environment
							.getExternalStorageDirectory().getAbsolutePath()
							+ "/updateApkFile/");
					if (!file.exists()) {
						// 如果文件夹不存在,则创建
						file.mkdir();
					}
					// 下载服务器中新版本软件（写文件）
					String apkFile = Environment.getExternalStorageDirectory()
							.getAbsolutePath()
							+ "/updateApkFile/"
							+ info.txtversionno;
					File ApkFile = new File(apkFile);
					FileOutputStream fos = new FileOutputStream(ApkFile);
					int count = 0;
					byte buf[] = new byte[1024];
					do {
						int numRead = is.read(buf);
						count += numRead;
						// 更新进度条
						progress = (int) (((float) count / length) * 100);
						handler.sendEmptyMessage(1);
						if (numRead <= 0) {
							// 下载完成通知安装
							handler.sendEmptyMessage(0);
							break;
						}
						fos.write(buf, 0, numRead);
						// 当点击取消时，则停止下载
					} while (!isInterceptDownload);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	/**
	 * 声明一个handler来跟进进度条
	 */
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			waitingDialog.dismiss();
			switch (msg.what) {
			case 1:
				// 更新进度情况
				progressBar.setProgress(progress);
				break;
			case 0:
				progressBar.setVisibility(View.INVISIBLE);
				// 安装apk文件
				installApk();
				break;
			case 2:
				// 判断是否需要更新
				if (info != null) {
					try {
						// 获取当前软件包信息
						PackageInfo pi = mContext.getPackageManager()
								.getPackageInfo(mContext.getPackageName(),
										PackageManager.GET_CONFIGURATIONS);
						System.out.println(" info " + pi.versionCode + " v "
								+ info.iversion);
						// 当前软件版本号
						float versionCode = pi.versionCode;
						if (versionCode < info.iversion) {
							// 如果当前版本号小于服务端版本号,则弹出提示更新对话框
							showUpdateDialog();
							AshtSettings.getInstance().setNeadUpdate(true);
						} else {
							AshtSettings.getInstance().setNeadUpdate(false);
						}
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 安装apk
	 */
	private void installApk() {
		// 获取当前sdcard存储路径
		File apkfile = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/updateApkFile/" + info.txtversionno);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		// 安装，如果签名不一致，可能出现程序未安装提示
		i.setDataAndType(Uri.fromFile(new File(apkfile.getAbsolutePath())),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}
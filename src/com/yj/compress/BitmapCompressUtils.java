package com.yj.compress;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class BitmapCompressUtils {

	public String getMedicalRecordImageFileToByte(String path) {
		if (path == null) {
			return "";
		} else {
			Bitmap bm = BitmapFactory.decodeFile(path);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
			byte[] b = baos.toByteArray();
			return Base64.encodeToString(b, Base64.DEFAULT);
		}
	}

}

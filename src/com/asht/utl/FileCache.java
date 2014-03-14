package com.asht.utl;

import java.io.File;

import android.os.Environment;

public class FileCache {
	private static boolean ExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	public static String getPath() {
		boolean fag = ExistSDCard();
		if (fag) {
			File path = Environment.getExternalStorageDirectory();
			File cache = new File(path, "ashtImageCache");
			if (cache.exists() && cache.isDirectory()) {
				return cache.getAbsolutePath();
			} else {
				cache.delete();
				cache.mkdirs();
				return cache.getAbsolutePath();
			}
		}
		return null;
	}

	public static void clearCache() {
		String path = getPath();
		if (path != null) {
			delete(new File(getPath()), false);
		}

	}

	/**
	 * 删除文件以及文件夹 和文件夹下面的文件
	 * 
	 * @param f
	 *            需要删除文件路径
	 * @param fag
	 *            是否删除传入文件夹
	 */
	private static void delete(File f, boolean fag) {
		if (!f.exists()) {
			return;
		}
		if (f.isFile()) {
			f.delete();
			return;
		}
		if (f.isDirectory()) {
			File fs[] = f.listFiles();
			for (File file : fs) {
				delete(file, true);
			}
			if (fag) {
				f.delete();
			}
		}

	}
}

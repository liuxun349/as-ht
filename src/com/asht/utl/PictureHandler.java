package com.asht.utl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PictureHandler {
	
	public static List<String> getSdCardIamge(){
		List<String> list = new ArrayList<String>();
		String url = "/mnt/sdcard";
		return list;
	}
	private static void getSDImageFiles(String url,List<String> list) {

	    try {
	        File file = new File(url);     
	        File[] files = file.listFiles();

	        for(int i=0; i<files.length; i++) {
	            if(files[i].isDirectory()) {
	            	getSDImageFiles(files[i].getAbsolutePath(),list);
	            }else {
	                    list.add(files[i].getAbsolutePath());
	            }
	        }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}

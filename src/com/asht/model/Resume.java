package com.asht.model;

import java.io.File;
import java.io.Serializable;
import java.util.Hashtable;

import org.json.JSONObject;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.graphics.Bitmap;


public class Resume {
	private static final long serialVersionUID = -6193310436318894856L;
	/**
	 * 病例组id
	 */
	private String medicalRecordGroupID;
	/**
	 * 病理图片　
	 */
	private Bitmap medicalRecordItemFile;
	private String state;
	private int imedicalRecordItemId;

}

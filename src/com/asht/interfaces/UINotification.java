package com.asht.interfaces;

import java.util.List;

import android.view.View;

public interface UINotification {

	void notificationStart(int size);

	void notificationLast();

	void notificationSelected(int size);

	void delete();

	void onClick(int index, View citem, Object object, List<?> list);
}
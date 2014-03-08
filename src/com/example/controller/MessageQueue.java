package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class MessageQueue {
	private Vector<Object> vector = new Vector<Object>();

	/**
	 * 添加对象
	 * 
	 * @param object
	 */
	public synchronized void add(Object object) {
		this.vector.addElement(object);
		notify();
	}

	/**
	 * 插入对象
	 * 
	 * @param list
	 */
	public synchronized void add(Object object, int index) {
		this.vector.insertElementAt(object, index);
		notify();
	}

	/**
	 * 添加集合
	 * 
	 * @param list
	 */
	public synchronized void addAll(Collection<?> collection) {
		this.vector.addAll(collection);
		notify();
	}

	/**
	 * 插入集合
	 * 
	 * @param list
	 */
	public synchronized void addAll(List<?> list, int index) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				this.vector.insertElementAt(list.get(i), 0);
			}
		}
		notify();
	}

	/**
	 * 获取第一个并移除
	 * 
	 * @return
	 */
	public synchronized Object pull() {
		if (isEmpty()) {
			return null;
		}
		return get();
	}

	/**
	 * 取队列中单个对象
	 * 
	 * @return
	 */
	public synchronized Object get() {
		Object object = peek();
		if (object != null) {
			this.vector.removeElementAt(0);
		}
		return object;
	}

	/**
	 * 移除队列里面的对象
	 * 
	 * @param obj
	 */
	public synchronized Object getElement() {
		if (!isEmpty()) {
			return this.vector.get(0);
		}
		return null;
	}

	/**
	 * 取队列返回Vector
	 * 
	 * @return
	 */
	public synchronized Vector<Object> getElements() {
		return this.vector;
	}

	/**
	 * 取队列返回List
	 * 
	 * @return
	 */
	public synchronized List<Object> getAll() {
		List<Object> list = new ArrayList<Object>();
		while (!isEmpty()) {
			Object obj = pull();
			list.add(obj);
		}
		return list;
	}

	/**
	 * 根据大小取队列返回List
	 * 
	 * @return
	 */
	public synchronized List<Object> getAll(int size) {
		List<Object> list = new ArrayList<Object>();
		int i = 0;
		while (!isEmpty()) {
			if (i == size) {
				break;
			}
			Object obj = pull();
			list.add(obj);
			i++;
		}
		return list;
	}

	/**
	 * 获取第一个对象
	 * 
	 * @return
	 */
	private Object peek() {
		if (isEmpty()) {
			return null;
		}
		return this.vector.elementAt(0);
	}

	/**
	 * 清空
	 * 
	 * @return
	 */
	public synchronized void clear() {
		this.vector.clear();
	}

	/**
	 * 移除队列里面的对象
	 * 
	 * @param obj
	 */
	public synchronized void remove(Object obj) {
		if (!isEmpty()) {
			this.vector.remove(obj);
		}
	}

	/**
	 * 判断是否为空
	 * 
	 * @return
	 */
	public synchronized boolean isEmpty() {
		return this.vector.isEmpty();
	}

	/**
	 * 获取长度
	 * 
	 * @return
	 */
	public synchronized int size() {
		return this.vector.size();
	}

}
package com.sistema.desktop_cr7_imports.objects;

public class ThreadCustom extends Thread{
	
	public ThreadCustom(Runnable object) {
		super(object);
	}

	public static void sleepThread(long milis) {
		try {
			sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

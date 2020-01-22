package com.JavaPractice;

public class SwitchBrowserWindow {

	public static int i =5;
	public static void main(String[] args) {

        System.out.print("Switch to browser window");

	}

	static void xyz() {
		SwitchBrowserWindow  br = new SwitchBrowserWindow();
		br.abc();
		System.out.println(i);
	}
	
	void abc() {
		SwitchBrowserWindow.xyz();
		
	}
}

























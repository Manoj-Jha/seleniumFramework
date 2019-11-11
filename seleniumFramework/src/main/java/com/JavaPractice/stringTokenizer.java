package com.JavaPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class stringTokenizer {

	public static void main(String[] args) {
		String username ="username&&xpath&&//input[@id='username']";
         List<String> list =  new ArrayList<String>();
		
		String objectName = null;
		String objectType = null;
		String objectValue = null;
		try {
			StringTokenizer st = new StringTokenizer(username, "&&");
			while(st.hasMoreTokens()){
				objectName=st.nextToken().toString();
				objectType=st.nextToken().toString();
				objectValue=st.nextToken().toString();
			}
			System.out.println(objectName);
			System.out.println(objectType);
			System.out.println(objectValue);
			
			list.add(objectName);
			list.add(objectType);
			list.add(objectValue);
			
			System.out.println(list);
			System.out.println(list.get(0).toString());
			System.out.println(list.get(1).toString());
			System.out.println(list.get(2).toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}




















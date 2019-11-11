package com.JavaPractice;

class Student {
	int roll_on;
	String name;
	Student(int roll_no, String name){
		this.roll_on=roll_no;
		this.name=name;
	}
}
public class StudentArray {

	public static void main(String[] args) {
		Student[] arr;
		arr=new Student[5];
		arr[0]= new Student(1, "aman");
		arr[1]= new Student(2, "Raman");
		arr[2]= new Student(3, "Rohan");
		arr[3]= new Student(4, "Ramesh");
		arr[4]= new Student(5, "Sankar");
		
		for(int i=0; i<arr.length; i++){
		System.out.println("Student Roll No: " +arr[i].roll_on + "," + " Student name : "+arr[i].name);
		
		}

	}

}

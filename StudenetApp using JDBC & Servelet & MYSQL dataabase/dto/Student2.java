package com.sri.dto;

public class Student2 {
	
private int sid;
private String sname;
private double sper;
public Student2() {
}
public Student2(int sid, String sname, double sper) {
	this.sid = sid;
	this.sname = sname;
	this.sper = sper;
}
public int getSid() {
	return sid;
}
@Override
public String toString() {
	return "Student [sid=" + sid + ", sname=" + sname + ", sper=" + sper + "]";
}
public void setSid(int sid) {
	this.sid = sid;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public double getSper() {
	return sper;
}
public void setSper(double sper) {
	this.sper = sper;
}


}

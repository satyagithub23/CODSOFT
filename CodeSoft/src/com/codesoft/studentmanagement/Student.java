package com.codesoft.studentmanagement;

public class Student {
    static String name;
    static int rollNo;
    static String grade;
    static long aadharNumber;
    static String address;

    public Student(String name, int rollNo, String grade, long aadharNumber, String address) {
        Student.name = name;
        Student.rollNo = rollNo;
        Student.grade = grade;
        Student.aadharNumber = aadharNumber;
        Student.address = address;
    }

    public Student(){

    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getGrade() {
        return grade;
    }

    public long getAadharNumber() {
        return aadharNumber;
    }

    public void setName(String name) {
        Student.name = name;
    }

    public void setRollNo(int rollNo) {
        Student.rollNo = rollNo;
    }

    public void setGrade(String grade) {
        Student.grade = grade;
    }

    public void setAadharNumber(long aadharNumber) {
        Student.aadharNumber = aadharNumber;
    }

    public void setAddress(String address) {
        Student.address = address;
    }

    public String getAddress() {
        return address;
    }
}

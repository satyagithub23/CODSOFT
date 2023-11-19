package com.codesoft.studentmanagement;

import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        DBConnection connection = new DBConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO STUDENT MANAGEMENT SYSTEM");

        int choice;
        do {
            System.out.println("Choose What You Want To Do From The Below Options!\n");
            System.out.println("1. Add A New Student");
            System.out.println("2. Search A Student");
            System.out.println("3. Edit Student Information");
            System.out.println("4. Remove A Student");
            System.out.println("5. Exit!");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    Student student = new Student();
                    System.out.println("Enter Roll Number: ");
                    student.setRollNo(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter Name: ");
                    student.setName(sc.nextLine());
                    System.out.println("Enter Grade: ");
                    student.setGrade(sc.next());
                    System.out.println("Enter Aadhar Number: ");
                    student.setAadharNumber(sc.nextLong());
                    sc.nextLine();
                    System.out.println("Enter Address: ");
                    student.setAddress(sc.nextLine());
                    int rollNo = student.getRollNo();
                    String name = student.getName();
                    String grade = student.getGrade();
                    long aadharNumber = student.getAadharNumber();
                    String address = student.getAddress();
                    if (rollNo < 1 || name == null || grade == null || aadharNumber < 1 || address == null) {
                        System.out.println("No fields can be remain blank try again");
                    } else {
                        connection.addStudent(student);
                    }
                }
                case 2 -> {
                    System.out.println("Enter Roll No.: ");
                    int rollNum = sc.nextInt();
                    connection.searchStudent(rollNum);
                }
                case 3 -> {
                    System.out.println("Enter Roll No.: ");
                    int rollNm = sc.nextInt();
                    connection.editStudent(rollNm);
                }
                case 4 -> {
                    System.out.println("Enter Roll No.: ");
                    int rollNumber = sc.nextInt();
                    connection.removeStudent(rollNumber);
                }
                case 5 -> System.exit(0);
                default -> System.out.println("Enter a valid choice!");
            }
        } while (choice != 5);
    }
}

package com.codesoft.studentmanagement;

import java.sql.*;
import java.util.Scanner;

public class DBConnection {
    private static Statement statement;
    private static ResultSet resultSet;
    public DBConnection() {
        String url = "jdbc:mysql://localhost:3300/student_management";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudent(Student student){
        System.out.println("Adding student...");
        String insertQuery = "INSERT INTO "+Params.tableName+" VALUES ("
                +student.getRollNo()+", '"
                +student.getName()+"', '"
                +student.getGrade()+"', "
                +student.getAadharNumber()+", '"
                +student.getAddress()+"')";
        try {
            int queryExecutionStatus = statement.executeUpdate(insertQuery);
            if (queryExecutionStatus > 0){
                System.out.println("Student Added Successfully!");
            } else {
                System.out.println("Something Wrong Happened! Try Again Later");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeStudent(int rollNumber){
        String removeQuery = "DELETE FROM "+Params.tableName+" WHERE "+Params.rollNumber+" = "+rollNumber;
        System.out.println("Removing Student...");
        try {
            int queryExecutionStatus = statement.executeUpdate(removeQuery);
            if (queryExecutionStatus > 0){
                System.out.println("Student Removed Successfully");
            } else {
                System.out.println("Something Wrong Happened! Try Again Later.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchStudent(int rollNo){
        String searchQuery = "SELECT * FROM "+Params.tableName+" WHERE "+Params.rollNumber+" = "+rollNo;

        Student student = new Student();

        try {
            resultSet = statement.executeQuery(searchQuery);
            while(resultSet.next()){
                student.setRollNo(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setGrade(resultSet.getString(3));
                student.setAadharNumber(resultSet.getLong(4));
                student.setAddress(resultSet.getString(5));
            }
            System.out.println("Name: "+student.getName());
            System.out.println("Roll No.: "+student.getRollNo());
            System.out.println("Aadhar Number: "+student.getAadharNumber());
            System.out.println("Grade: "+student.getGrade());
            System.out.println("Address: "+student.getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editStudent(int rollNo) {
        String searchQuery = "SELECT * FROM " + Params.tableName + " WHERE " + Params.rollNumber + " = " + rollNo;

        Student std = new Student();
        Scanner sc = new Scanner(System.in);

        System.out.println("Searching student!");

        boolean flag = false;

        try {
            resultSet = statement.executeQuery(searchQuery);
            while (resultSet.next()) {
                std.setRollNo(resultSet.getInt(1));
                std.setName(resultSet.getString(2));
                std.setGrade(resultSet.getString(3));
                std.setAadharNumber(resultSet.getLong(4));
                std.setAddress(resultSet.getString(5));
            }
            System.out.println("Roll No.: "+std.getRollNo());
            System.out.println("Name: "+std.getName());
            System.out.println("Grade: "+std.getGrade());
            System.out.println("Aadhar No.: "+std.getAadharNumber());
            System.out.println("Address: "+std.getAddress());
            System.out.println("\n\nChoose what you want to edit from the below options.\n");
            System.out.println("1. Name");
            System.out.println("2. Aadhar Number");
            System.out.println("3. Address");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter correct name:\n");
                    String newName = sc.nextLine();
                    String updateNameQuery = "UPDATE "+ Params.tableName +" SET `"+Params.studentName+"` = '" + newName + "' WHERE `"+Params.rollNumber+"` = " + rollNo;
                    int nameQueryExecutionStatus = statement.executeUpdate(updateNameQuery);
                    if (nameQueryExecutionStatus > 0) {
                        System.out.println("Name updated successfully!");
                        flag = true;
                    } else {
                        System.out.println("Sorry something wrong happened!");
                    }
                }
                case 2 -> {
                    System.out.println("Enter correct aadhar no.:\n");
                    long newAadhar = sc.nextLong();
                    String updateAadharQuery = "UPDATE "+Params.tableName+" SET "+Params.studentAadharNumber+" = " + newAadhar + " WHERE "+Params.rollNumber+" = " + rollNo;
                    int aadharQueryExecutionStatus = statement.executeUpdate(updateAadharQuery);
                    if (aadharQueryExecutionStatus > 0) {
                        System.out.println("Aadhar number updated successfully!");
                        flag = true;
                    } else {
                        System.out.println("Sorry something wrong happened!");
                    }
                }
                case 3 -> {
                    System.out.println("Enter correct address:\n");
                    String newAddress = sc.nextLine();
                    String updateAddressQuery = "UPDATE `"+Params.tableName+"` SET `"+Params.studentAddress+"` = '" + newAddress + "' WHERE "+Params.rollNumber+" = " + rollNo;
                    int addressQueryExecutionStatus = statement.executeUpdate(updateAddressQuery);
                    if (addressQueryExecutionStatus > 0) {
                        System.out.println("Address updated successfully!");
                        flag = true;
                    } else {
                        System.out.println("Sorry something wrong happened!");
                    }
                }
                default -> System.out.println("Sorry Something Went Wrong! Please Try Again Later.");
            }
            if (flag){
                searchStudent(rollNo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }    
}

package com.codesoft;

import java.util.Scanner;

public class GradeSystem {
    public static void main(String[] args) {
        gradeCalculate();
    }

    private static void gradeCalculate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of subjects:");
        int subjectNumbers = sc.nextInt();
        sc.nextLine();
        String[] subjects = new String[subjectNumbers];
        int[] marks = new int[subjectNumbers];
        int totalMarks = 0;
        int securedTotalMark = 0;
        float percentage = 0.0f;
        String grade = "";
        System.out.println("Which subjects did you have ? \nEnter name of each subject: ");
        for (int i = 0; i < subjects.length; i++){
            subjects[i] = sc.nextLine();
        }
        for (int j = 0; j < subjects.length; j++){
            System.out.println("Enter marks secured in "+subjects[j]+":");
            marks[j] = sc.nextInt();
        }
        for (int k = 0; k < subjects.length; k++){
            System.out.println("Marks secured in "+subjects[k]+": "+marks[k]);
            securedTotalMark += marks[k];
        }
        totalMarks = 100 * subjectNumbers;
        percentage = (float) securedTotalMark/subjectNumbers;
        System.out.println("Total Marks: "+totalMarks);
        System.out.println("Total Secured Marks: "+securedTotalMark);
        System.out.println("Average Percentage: "+percentage+"%");

        if (percentage >= 90.0f && percentage <= 100.0f){
            grade = "A1 (Outstanding)";
        } else if (percentage >= 80.0f && percentage < 90.0f) {
            grade = "A2 (Excellent)";
        } else if (percentage >= 70.0f && percentage < 80.0f) {
            grade = "B1 (Very Good)";
        } else if (percentage >= 60.0f && percentage < 70.0f) {
            grade = "B2 (Good)";
        } else if (percentage >= 50.0f && percentage < 60.0f) {
            grade = "C (Above Average)";
        } else if (percentage >= 40.0f && percentage < 50.0f) {
            grade = "D (Average)";
        } else if (percentage >= 30.0f && percentage < 40.0f) {
            grade = "E (Fair)";
        } else if (percentage < 30.0f) {
            grade = "F (Unsatisfactory)";
        }
        System.out.println("GRADE: "+ grade);
    }
}

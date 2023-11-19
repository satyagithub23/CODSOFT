package com.codesoft;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
//        Calling the playGame() function
        playGame();
    }

    public static void playGame(){
        System.out.println("WELCOME TO NUMBER GAME");

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

//        Variable Declaration
        int randNumber = random.nextInt(0, 100);
        int attempt = 10;
        int range = 10;
        int playChoice;

        do {
            System.out.println("Guess the correct number");
            System.out.println("Attempt remaining: "+attempt);

            int userGuess = sc.nextInt();

            if (userGuess >= randNumber + range) {
                System.out.println("You guessed a very large number.");
            } else if (userGuess > randNumber) {
                System.out.println("You guessed  a large number.");
            } else if (userGuess == randNumber) {
                System.out.println("Congrats! You guessed the correct number");
                System.out.println("You scored: " + attempt * 10 + " points!");
                break;
            } else if (userGuess <= randNumber - range) {
                System.out.println("You guessed a very small number.");
            } else if (userGuess >= randNumber - range) {
                System.out.println("You guessed a smaller number.");
            }

            attempt -= 1;

            if (attempt == 0){
                System.out.println("You Lose!");
                System.out.println("The correct number was: "+randNumber);
            }

        }while(attempt > 0);

        System.out.println("\n\nDo You Want To Play Again ?");
        System.out.println("Enter Your Choice:");
        System.out.println("1. Play Again\n2. Exit");

        playChoice = sc.nextInt();
        switch (playChoice) {
            case 1 -> playGame();
            case 2 -> System.exit(0);
            default -> System.out.println("Better Luck Next Time");
        }
    }


}

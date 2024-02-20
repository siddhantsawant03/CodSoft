package CodSoft;

import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Start");
            System.out.println("2. Attempts Over? Play Again");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            
            if (choice == 1) {
                start(sc, r);
            } else if (choice != 2) {
                System.out.println("Invalid Choice");
            }
        } while (choice != 3);
        
        sc.close();
    }
    
    public static void start(Scanner sc , Random r) {
        int Minno = 1;  
        int Maxno = 100;
        
        int roundsWon = 0;
        int totalNoOfRounds = 0;
        int totalNoOfAttempts = 0;
        
        boolean startOver = true;
        while (startOver) {
            totalNoOfRounds++;
            int randomNumber = r.nextInt(Maxno - Minno + 1) + Minno;

            System.out.println("You will have only 3 attempts");
            
            int attempts = 3;
            while (attempts > 0) {
                System.out.println("Enter your guess (" + Minno + " to " + Maxno + ", " + attempts + " attempts remaining): ");
                int userInput = sc.nextInt();
                totalNoOfAttempts++;
                
                if (userInput < Minno || userInput > Maxno) {
                    System.out.println("Your guess is out of range. Please guess between " + Minno + " and " + Maxno);
                    continue; // Skip the rest of the loop iteration and start the next one
                }
                
                if (userInput == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    roundsWon++;
                    break;
                } else if (userInput > randomNumber) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Your guess is too low.");
                }
                
                attempts--;
            }
            
            if (attempts == 0) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }
            
            System.out.println("Do you want to start over? (y/n)");
            String play = sc.next();
            startOver = play.equals("y");
        }
        
        System.out.println("Total Rounds Played: " + totalNoOfRounds);
        System.out.println("Total Attempts Made: " + totalNoOfAttempts);
        System.out.println("Total Rounds Won: " + roundsWon);
    }
}

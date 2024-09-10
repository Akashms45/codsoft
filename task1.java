mport java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        
        int rounds = 0;
        int totalAttempts = 0;
        boolean playAgain = true;
        
        while (playAgain) {
            rounds++;
            int secretNumber = random.nextInt(100) + 1; // Generates random number between 1 and 100
            int attempts = 0;
            final int maxAttempts = 10; // Maximum number of attempts allowed
            
            while (attempts < maxAttempts) {
                System.out.println("\nAttempts left: " + (maxAttempts - attempts));
                System.out.print("Enter your guess (1-100): ");
                int guess = scanner.nextInt();
                attempts++;
                
                // Compare the user's guess with the secret number
                if (guess < secretNumber) {
                    System.out.println("Too low. Try again!");
                } else if (guess > secretNumber) {
                    System.out.println("Too high. Try again!");
                } else {
                    System.out.println("Congratulations! You've guessed the number " + secretNumber + " correctly!");
                    break;
                }
                
                // Check if the user has used all attempts
                if (attempts == maxAttempts) {
                    System.out.println("Sorry, you've run out of attempts!");
                    System.out.println("The correct number was " + secretNumber + ". Better luck next time!");
                }
            }
            
            totalAttempts += attempts;
            
            // Ask user if they want to play again
            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next();
            playAgain = playAgainResponse.equalsIgnoreCase("yes");
        }
        
        scanner.close();
        
        // Display game summary
        System.out.println("\nGame summary:");
        System.out.println("Total rounds played: " + rounds);
        System.out.println("Total attempts made: " + totalAttempts);
        if (rounds > 0) {
            System.out.printf("Average attempts per round: %.2f\n", (double) totalAttempts / rounds);
        }
        
        System.out.println("\nThank you for playing! Goodbye.");
    }
}

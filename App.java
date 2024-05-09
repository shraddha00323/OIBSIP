import java.util.Random;
import java.util.Scanner;

public class App {
    private static final int min_range = 1;
    private static final int max_range = 100;
    private static final int max_attempts = 5;
    private static final int max_rounds = 5;

    public static void main(String args[]) {
        Random random = new Random();
        Scanner obj = new Scanner(System.in);
        int totscore = 0;
        System.out.println("Number guessing game task");
        System.out.println("Total rounds - 5");
        System.out.println("Attempts to guess number - 5");
        for (int i = 1; i <= max_rounds; i++) {
            int num = random.nextInt(max_range) + min_range;
            int attempts = 0;
            System.out.printf("Round %d: Guess the number between %d and %d in %d attempts.\n", i, min_range, max_range, max_attempts);
            while (attempts < max_attempts) {
                System.out.println("Enter your guess - ");
                int guess_num = obj.nextInt();
                attempts = attempts + 1;
                if (guess_num == num) {
                    int score = max_attempts - attempts;
                    totscore = totscore + score;
                    System.out.printf("Yippee! Number guessed correctly. Attempts = %d. Round Score = %d\n", attempts, score);
                    break;
                } else if (guess_num < num) {
                    System.out.printf("Number is greater than %d. Attempts left = %d.\n", guess_num, max_attempts - attempts);
                } else if (guess_num > num) {
                    System.out.printf("Number is less than %d. Attempts left = %d.\n", guess_num, max_attempts - attempts);
                }
            }
            if (attempts == max_attempts) {
                System.out.printf("\nRound = %d\n", i);
                System.out.println("Attempts - 0");
                System.out.printf("Random Number - %d\n\n", num);
            }
        }
        System.out.printf("Game over. Total score = %d\n", totscore);
        obj.close();
    }
}
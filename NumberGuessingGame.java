import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int roundsPlayed = 0;

        JOptionPane.showMessageDialog(null, "Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfTries = 0;
            int maxAttempts = 5; // Set the maximum number of attempts per round
            boolean hasGuessedCorrectly = false;

            JOptionPane.showMessageDialog(null, "I have randomly chosen a number between 1 and 100.\nYou have "
                    + maxAttempts + " attempts to guess it.");

            while (!hasGuessedCorrectly && numberOfTries < maxAttempts) {
                // Create a custom dialog with input and hint button
                String[] options = { "Guess", "Hint" };
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter a guess between 1 and 100:");
                JTextField textField = new JTextField(10);
                panel.add(label);
                panel.add(textField);
                int option = JOptionPane.showOptionDialog(null, panel, "Guess the Number",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);

                if (option == JOptionPane.CLOSED_OPTION) {
                    hasGuessedCorrectly = false;
                    break;
                }

                if (option == 1) { // Hint button pressed
                    if (numberOfTries >= 3) {
                        int rangeStart = (numberToGuess / 10) * 10;
                        int rangeEnd = rangeStart + 10;
                        JOptionPane.showMessageDialog(null,
                                "Hint: The number is between " + rangeStart + " and " + rangeEnd + ".");
                    } else {
                        JOptionPane.showMessageDialog(null, "You need to make at least 3 attempts to get a hint.");
                    }
                    continue; // Do not count this as a guess
                }

                String guessStr = textField.getText();
                if (guessStr == null) {
                    // User pressed cancel
                    hasGuessedCorrectly = false;
                    break;
                }

                int guess;
                try {
                    guess = Integer.parseInt(guessStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    continue;
                }

                numberOfTries++;

                if (guess < 1 || guess > 100) {
                    JOptionPane.showMessageDialog(null, "The number must be between 1 and 100.");
                } else if (guess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                } else if (guess > numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                } else {
                    hasGuessedCorrectly = true;
                    JOptionPane.showMessageDialog(null, "Congratulations! You have guessed the correct number.");
                    int scoreForRound = maxAttempts - numberOfTries + 1;
                    totalScore += scoreForRound;
                    JOptionPane.showMessageDialog(null,
                            "You took " + numberOfTries + " tries. Your score for this round is: " + scoreForRound);
                }
            }

            if (!hasGuessedCorrectly) {
                JOptionPane.showMessageDialog(null,
                        "Sorry, your attempts were over. The correct number was: " + numberToGuess);
            }

            roundsPlayed++;
            JOptionPane.showMessageDialog(null,
                    "Your total score after " + roundsPlayed + " round(s) is: " + totalScore);

            int response = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
                    JOptionPane.YES_NO_OPTION);
            playAgain = (response == JOptionPane.YES_OPTION);
        }

        JOptionPane.showMessageDialog(null, "Thank you for playing! Your final score is: " + totalScore);
    }
}

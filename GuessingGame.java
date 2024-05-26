import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGame extends JFrame {

    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel, attemptsLabel;
    private int randomNumber;
    private int maxAttempts = 5;
    private int guessCount = 0;

    public GuessingGame() {
        super("Guessing Game");

        // Generate random number (1-100)
        randomNumber = (int) (Math.random() * 100) + 1;

        // Create UI components
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("Welcome to the Guessing Game!");
        attemptsLabel = new JLabel("Attempts left: " + (maxAttempts - guessCount));

        // Set layout
        getContentPane().setLayout(new GridLayout(5, 1));
        add(resultLabel);
        add(new JLabel("I'm thinking of a number between 1 and 100. Can you guess it?"));
        add(guessField);
        add(guessButton);
        add(attemptsLabel);

        // Add action listener for guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess = Integer.parseInt(guessField.getText());
                guessCount++;

                if (guess == randomNumber) {
                    resultLabel.setText("Congratulations! You guessed the number in " + guessCount + " attempts.");
                    guessButton.setEnabled(false);
                    if (showRetryDialog()) {
                        resetGame();
                    }
                } else if (guess < randomNumber) {
                    resultLabel.setText("Your guess is too low. Try again.");
                } else {
                    resultLabel.setText("Your guess is too high. Try again.");
                }

                attemptsLabel.setText("Attempts left: " + (maxAttempts - guessCount));
                guessField.setText("");

                if (guessCount == maxAttempts) {
                    resultLabel.setText("Sorry, you ran out of attempts. The number was " + randomNumber + ".");
                    guessButton.setEnabled(false);
                    if (showRetryDialog()) {
                        resetGame();
                    }
                }
            }
        });

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean showRetryDialog() {
        int option = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    private void resetGame() {
        randomNumber = (int) (Math.random() * 100) + 1;
        guessCount = 0;
        resultLabel.setText("Welcome to the Guessing Game!");
        attemptsLabel.setText("Attempts left: " + (maxAttempts - guessCount));
        guessButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessingGame();
            }
        });
    }
}

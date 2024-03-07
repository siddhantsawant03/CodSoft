import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame extends JFrame implements ActionListener {
    private JButton startButton;
    private JButton playAgainButton;
    private JButton exitButton;
    private JTextArea outputArea;
    private Random r;

    public NumberGame() {
        super("Number Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        startButton = new JButton("Start");
        startButton.setBounds(50, 30, 100, 30);
        startButton.addActionListener(this);
        add(startButton);

        playAgainButton = new JButton("Attempts Over? Play Again");
        playAgainButton.setBounds(50, 80, 200, 30);
        playAgainButton.addActionListener(this);
        add(playAgainButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(50, 130, 100, 30);
        exitButton.addActionListener(this);
        add(exitButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(50, 180, 300, 80);
        add(scrollPane);

        r = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            start();
        } else if (e.getSource() == playAgainButton) {
            start();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void start() {
        int Minno = 1;
        int Maxno = 100;

        int randomNumber = r.nextInt(Maxno - Minno + 1) + Minno;

        outputArea.append("New game started! You will have only 3 attempts.\n");

        int attempts = 3;
        while (attempts > 0) {
            int userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your guess (" + Minno + " to " + Maxno + ", " + attempts + " attempts remaining): "));
            if (userInput < Minno || userInput > Maxno) {
                JOptionPane.showMessageDialog(null, "Your guess is out of range. Please guess between " + Minno + " and " + Maxno);
                continue;
            }

            if (userInput == randomNumber) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number.");
                break;
            } else if (userInput > randomNumber) {
                JOptionPane.showMessageDialog(null, "Your guess is too high.");
            } else {
                JOptionPane.showMessageDialog(null, "Your guess is too low.");
            }

            attempts--;
        }

        if (attempts == 0) {
            JOptionPane.showMessageDialog(null, "Sorry, you've run out of attempts. The correct number was: " + randomNumber);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGame numberGame = new NumberGame();
            numberGame.setVisible(true);
        });
    }
}

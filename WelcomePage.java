import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame {
    private JButton btnLogin;
    private JButton btnSignUp;

    public WelcomePage() {
        initialize();
    }

    private void initialize() {
        setTitle("Welcome to Digibank");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Welcome to Digibank");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setForeground(Color.BLUE);
        panel.add(lblWelcome, BorderLayout.CENTER);

        btnLogin = new JButton("Login");
        btnSignUp = new JButton("Sign Up");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnSignUp);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login button click
                // Open the login page or perform any desired action
                UserLoginPage userLoginPage = new UserLoginPage();
                userLoginPage.setVisible(true);
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle sign up button click
                // Open the sign-up form or perform any desired action
                SignUpForm signUpForm = new SignUpForm();
                signUpForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WelcomePage welcomePage = new WelcomePage();
                welcomePage.setVisible(true);
            }
        });
    }
}

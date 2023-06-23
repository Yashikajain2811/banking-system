import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLoginPage extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public UserLoginPage() {
        initialize();
    }

    private void initialize() {
        setTitle("User Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login button click
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Perform login logic here
                // You can compare the entered username and password with the saved data

                // Clear the fields after login attempt
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
    }
}

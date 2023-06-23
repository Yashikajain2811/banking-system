import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SignUpForm extends JFrame {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JTextField txtPhoneNumber;
    private JTextArea txtAddress;
    private JTextField txtDateOfBirth;
    private JButton btnSubmit;

    public SignUpForm() {
        initialize();
    }

    private void initialize() {
        setTitle("Sign Up Form");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(9, 2, 10, 10));

        JLabel lblFirstName = new JLabel("First Name:");
        txtFirstName = new JTextField();
        JLabel lblLastName = new JLabel("Last Name:");
        txtLastName = new JTextField();
        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        txtPhoneNumber = new JTextField();
        JLabel lblAddress = new JLabel("Address:");
        txtAddress = new JTextArea();
        JLabel lblDateOfBirth = new JLabel("Date of Birth:");
        txtDateOfBirth = new JTextField();

        btnSubmit = new JButton("Submit");

        formPanel.add(lblFirstName);
        formPanel.add(txtFirstName);
        formPanel.add(lblLastName);
        formPanel.add(txtLastName);
        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(lblEmail);
        formPanel.add(txtEmail);
        formPanel.add(lblPhoneNumber);
        formPanel.add(txtPhoneNumber);
        formPanel.add(lblAddress);
        formPanel.add(new JScrollPane(txtAddress));
        formPanel.add(lblDateOfBirth);
        formPanel.add(txtDateOfBirth);
        formPanel.add(new JLabel());
        formPanel.add(btnSubmit);

        panel.add(formPanel, BorderLayout.CENTER);

        add(panel);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                String email = txtEmail.getText();
                String phoneNumber = txtPhoneNumber.getText();
                String address = txtAddress.getText();
                String dateOfBirth = txtDateOfBirth.getText();

                if (validateInput(firstName, lastName, username, password, email, phoneNumber, address, dateOfBirth)) {
                    // Create a JSON object to store the user data
                    JSONObject userObject = new JSONObject();
                    userObject.put("firstName", firstName);
                    userObject.put("lastName", lastName);
                    userObject.put("username", username);
                    userObject.put("password", password);
                    userObject.put("email", email);
                    userObject.put("phoneNumber", phoneNumber);
                    userObject.put("address", address);
                    userObject.put("dateOfBirth", dateOfBirth);

                    // Read the existing JSON data from the file, if any
                    JSONArray jsonArray = readJSONFile("user_data.json");

                    // Add the new user object to the existing JSON array
                    jsonArray.add(userObject);

                    // Write the updated JSON array to the file
                    writeJSONFile(jsonArray, "user_data.json");

                    clearFields();
                    JOptionPane.showMessageDialog(SignUpForm.this, "User registered successfully!");
                } else {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Please fill all the required fields.");
                }
            }
        });
    }

    private boolean validateInput(String firstName, String lastName, String username, String password, String email,
            String phoneNumber, String address, String dateOfBirth) {
        return !firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty()
                && !email.isEmpty() && !phoneNumber.isEmpty() && !address.isEmpty() && !dateOfBirth.isEmpty();
    }

    private void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
        txtDateOfBirth.setText("");
    }

    private JSONArray readJSONFile(String fileName) {
        JSONArray jsonArray = new JSONArray();
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONParser jsonParser = new JSONParser();
            if (!jsonData.isEmpty()) {
                jsonArray = (JSONArray) jsonParser.parse(jsonData);
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return jsonArray;
    }

    private void writeJSONFile(JSONArray jsonArray, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonArray.toJSONString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SignUpForm signUpForm = new SignUpForm();
                signUpForm.setVisible(true);
            }
        });
    }
}

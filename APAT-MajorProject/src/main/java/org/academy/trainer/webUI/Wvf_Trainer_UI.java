package org.academy.trainer.webUI;

import javax.swing.*;

import org.academy.trainer.Academy_Trainer;
import org.academy.trainer.login.LoginPage;
import org.academy.trainer.mail.SendAttachment;

import java.awt.*;
import java.io.IOException;

public class Wvf_Trainer_UI extends JFrame{

	/**/
	private static final long serialVersionUID = 1L;

	public static JTextField loginID;
	public static JPasswordField password;
	public static JTextField courseName;
	public static JTextField batchName;
	public static JTextField co_Ordinate_ID;

	public Wvf_Trainer_UI() {
		initComponents();
	}

	private void initComponents() {
		setTitle("WVF - Academy | Trainer Login");
		setSize(400, 250); // Increased the height to fit the brand board
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Center Panel with BoxLayout
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.WHITE); // Custom color

		// Brand board with a logo image
		JPanel brandPanel = new JPanel();
		brandPanel.setBackground(Color.WHITE);
		//        JLabel brandLogo = new JLabel(new ImageIcon("brand_logo.png"));
		JLabel brandLogo = new JLabel();// Add your brand logo image
		brandLogo.setIcon(new ImageIcon("wvf-logo.png"));
		brandPanel.add(brandLogo);
		
		// Main content panel with GridBagLayout
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

		// Labels with custom font and color
		Font labelFont = new Font("Arial", Font.PLAIN, 14);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;


		gbc.gridy = 1;
		JLabel userIdLabel = new JLabel("Login ID:");
		userIdLabel.setFont(labelFont);
		contentPanel.add(userIdLabel, gbc);

		gbc.gridy = 2;
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(labelFont);
		contentPanel.add(passwordLabel, gbc);

		gbc.gridy = 3;
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setFont(labelFont);
		contentPanel.add(courseNameLabel, gbc);

		gbc.gridy = 4;
		JLabel batchNameLabel = new JLabel("Batch Name:");
		batchNameLabel.setFont(labelFont);
		contentPanel.add(batchNameLabel, gbc);

		gbc.gridy = 5;
		JLabel co_OrdinateLabel = new JLabel("Co-Ordinate ID:");
		co_OrdinateLabel.setFont(labelFont);
		contentPanel.add(co_OrdinateLabel, gbc);

		// TextFields with custom font and border
		Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 6;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		//        nameField = new JTextField(20);
		//        nameField.setFont(textFieldFont); // Set font size to 14 for JTextField
		//        contentPanel.add(nameField, gbc);

		gbc.gridy = 1;
		loginID = new JTextField(20);
		loginID.setFont(textFieldFont); // Set font size to 14 for JTextField
		contentPanel.add(loginID, gbc);

		gbc.gridy = 2;
		password = new JPasswordField(20);
		password.setFont(textFieldFont); // Set font size to 14 for JPasswordField
		contentPanel.add(password, gbc);

		gbc.gridy = 3;
		courseName = new JTextField(20);
		courseName.setFont(textFieldFont); // Set font size to 14 for JTextField
		contentPanel.add(courseName, gbc);

		gbc.gridy = 4;
		batchName = new JTextField(20);
		batchName.setFont(textFieldFont); // Set font size to 14 for JTextField
		contentPanel.add(batchName, gbc);

		gbc.gridy = 5;
		co_Ordinate_ID = new JTextField(20);
		co_Ordinate_ID.setFont(textFieldFont); // Set font size to 14 for JTextField
		contentPanel.add(co_Ordinate_ID, gbc);

		// Submit Button with custom font and color
		//        gbc.gridx = 1;
		gbc.gridy = 6;
		//        gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER; // Center alignment
		JButton submitButton = new JButton("SUBMIT");
		submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Custom font
		submitButton.setBackground(new Color(255, 164, 56)); // Custom color
		submitButton.setForeground(Color.WHITE); // Custom color
		submitButton.addActionListener(e -> {
			try {
				onSubmit();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		contentPanel.add(submitButton, gbc);

		// Add brand panel and content panel to the JFrame
		add(brandPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);

		pack(); // Adjust the frame size based on the components
		setVisible(true);
	}

	private void onSubmit() throws InterruptedException, IOException {
		if(!loginID.getText().toString().equals("") && !new String(Wvf_Trainer_UI.password.getPassword()).equals("") && !courseName.getText().equals("") && !batchName.getText().equals("")
				&&!co_Ordinate_ID.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "Data collected! Click OK button for future actions and wait for some time…","Information",JOptionPane.INFORMATION_MESSAGE);
			LoginPage.trainerLogin();
			Academy_Trainer.collectBatchData();
			JOptionPane.showMessageDialog(this, "Candidate Data – Successfully collected, click OK button to send email with candidate performance document!","Information",JOptionPane.INFORMATION_MESSAGE);
			SendAttachment.sendmail();
			System.exit(0);
		}
		else {
			JOptionPane.showMessageDialog(this, "Please Enter all data.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}

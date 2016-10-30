package beerdiary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Register {

	private JFrame frame;
	private JTextField txtUserName;
	private JTextField txtLName;
	private JTextField txtFName;
	private JPasswordField passPass1;
	private JPasswordField passPass2;

	/**
	 * Launch the application.
	 */
	public void register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 0));
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 153));
		panel.setBounds(10, 23, 414, 70);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Beer Diary");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(129, 11, 138, 30);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(94, 184, 85, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password\r\n:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(94, 219, 85, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("First Name:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(94, 256, 85, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Last Name:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(94, 294, 85, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("User Name:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(94, 144, 101, 14);
		frame.getContentPane().add(lblNewLabel_5);

		txtUserName = new JTextField();
		txtUserName.setBounds(220, 144, 86, 20);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		txtLName = new JTextField();
		txtLName.setBounds(220, 293, 86, 20);
		frame.getContentPane().add(txtLName);
		txtLName.setColumns(10);

		txtFName = new JTextField();
		txtFName.setBounds(220, 255, 86, 20);
		frame.getContentPane().add(txtFName);
		txtFName.setColumns(10);
		frame.setBounds(100, 100, 450, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String user = txtUserName.getText();
				char[] pass1 = passPass1.getPassword();
				char[] pass2 = passPass2.getPassword();
				String FName = txtFName.getText();
				String LName = txtLName.getText();
				
				String newPass1 = new String(pass1);
				String newPass2 = new String(pass2);

				if (user.isEmpty() == true || pass1.length == 0 || pass2.length == 0 || FName.isEmpty() == true
						|| LName.isEmpty() == true) {
					JOptionPane.showMessageDialog(frame, "Please complete all fields.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else if (newPass1.equals(newPass2)==false) {
					JOptionPane.showMessageDialog(frame, "Your Password must match.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String newPass = new String(pass1);
					String securePassword = User.get_SHA_1_SecurePassword(newPass);
					Connection connection = null;
					Statement statement = null;
					String SQL = "insert into users values (" + null + ", '" + user + "' ,'" + securePassword + "' ,'" + FName
							+ "' ,'" + LName + "')";

					try {
						connection = JDBCMySQLConnection.getConnection();
						statement = connection.createStatement();
						statement.executeUpdate(SQL);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
					txtUserName.setText("");
					passPass1.setText("");
					passPass2.setText("");
					txtFName.setText("");
					txtLName.setText("");
					// Login nw = new Login();
					// nw.main(null);
				}
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.setBounds(94, 344, 89, 23);
		frame.getContentPane().add(btnRegister);

		JButton btnBack = new JButton("Close");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(217, 344, 89, 23);
		frame.getContentPane().add(btnBack);
		
		passPass1 = new JPasswordField();
		passPass1.setBounds(220, 184, 86, 19);
		frame.getContentPane().add(passPass1);
		
		passPass2 = new JPasswordField();
		passPass2.setBounds(220, 218, 86, 19);
		frame.getContentPane().add(passPass2);

	}
}

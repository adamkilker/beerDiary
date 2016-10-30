package beerdiary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField txtUserName;
	private JPasswordField passPassword;

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
		panel.setBounds(10, 11, 414, 73);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Beer Diary");
		lblNewLabel.setBounds(149, 11, 124, 29);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblNewLabel_1 = new JLabel("User Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(112, 109, 91, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(112, 158, 83, 14);
		frame.getContentPane().add(lblNewLabel_2);

		txtUserName = new JTextField();
		txtUserName.setBounds(228, 108, 86, 20);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Register nw = new Register();
				nw.register();
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.setBounds(225, 206, 89, 23);
		frame.getContentPane().add(btnRegister);

		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{

					/**
					 * if(user.isEmpty() == true || pass.length == 0) {
					 * JOptionPane.showMessageDialog(frame,
					 * "User Name and Password required.", "Warning",
					 * JOptionPane.WARNING_MESSAGE); } else{
					 **/
					Connection connection = null;
					Statement stmt = null;
					String query = "select * from users where userName ='" + txtUserName.getText() 
					//+ "'and password= '"+ passPassword.getPassword().toString() 
					+ "'";

					try {

						connection = JDBCMySQLConnection.getConnection();
						stmt = connection.createStatement();
						ResultSet rs = stmt.executeQuery(query);

						int count = 0;
						String user = "";
						String pass = "";
						String fName = "";
						String lName = "";
						while (rs.next()) {
							count++;
							user = rs.getString(2);
							pass = rs.getString(3);
							fName = rs.getString(4);
							lName = rs.getString(5);
						}

						if (count == 0) {
							JOptionPane.showMessageDialog(frame, "No User Name found.", "Warning",
									JOptionPane.WARNING_MESSAGE);

							txtUserName.setText("");
							passPassword.setText("");
						} else if (count > 1) {
							JOptionPane.showMessageDialog(frame, "Error. Duplicate users found.", "Warning",
									JOptionPane.WARNING_MESSAGE);

							txtUserName.setText("");
							passPassword.setText("");
						} else {
							char[] pass1 = passPassword.getPassword();
							String newPass = new String(pass1);
							String securePassword = User.get_SHA_1_SecurePassword(newPass);
							
							if(pass.equals(securePassword) == false){
								JOptionPane.showMessageDialog(frame, "Error. Incorrect Password.", "Warning",
										JOptionPane.WARNING_MESSAGE);
								txtUserName.setText("");
								passPassword.setText("");
							}else{
								User currentUser = new User(user, fName, lName);
								beerUserInterface nw = new beerUserInterface();
								nw.beerUserInterface();
								frame.dispose();
							}
						}

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

				} // end else
			}// end button
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(112, 206, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		passPassword = new JPasswordField();
		passPassword.setBounds(227, 158, 91, 19);
		frame.getContentPane().add(passPassword);

	}
}

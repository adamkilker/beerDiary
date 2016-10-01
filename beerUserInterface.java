package beerdiary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import java.awt.TextArea;
import javax.swing.JEditorPane;
import java.awt.Scrollbar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class beerUserInterface {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtLocation;
	private JTextField txtRating;

	/**
	 * Launch the application.
	 */
	public void beerUserInterface() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					beerUserInterface window = new beerUserInterface();
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
	public beerUserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 0));
		frame.setBounds(100, 100, 413, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 153));
		panel.setBounds(10, 11, 377, 66);
		frame.getContentPane().add(panel);

		JLabel lblTitle = new JLabel("Beer Diary");
		panel.add(lblTitle);
		lblTitle.setBackground(new Color(240, 230, 140));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("What's your Beer's Name?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 98, 191, 14);
		frame.getContentPane().add(lblNewLabel);

		txtName = new JTextField();
		txtName.setBounds(221, 97, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(100);

		JLabel lblNewLabel_1 = new JLabel("Where'd you drink it?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 128, 191, 14);
		frame.getContentPane().add(lblNewLabel_1);

		txtLocation = new JTextField();
		txtLocation.setBounds(221, 127, 86, 20);
		frame.getContentPane().add(txtLocation);
		txtLocation.setColumns(100);

		JLabel lblNewLabel_2 = new JLabel("How type of Beer was it?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(20, 153, 177, 20);
		frame.getContentPane().add(lblNewLabel_2);

		JComboBox comboType = new JComboBox();
		comboType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboType.setModel(new DefaultComboBoxModel(new String[] { "", "Amber", "Blonde", "Brown", "Cream", "Dark",
				"IPA", "Light", "Pale", "Pilsner", "Porter", "Red", "Stout", "Wheat" }));
		comboType.setBounds(221, 158, 86, 20);
		frame.getContentPane().add(comboType);

		JLabel lblNewLabel_3 = new JLabel("<html>What form did your Beer<br> come in?</html>");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(20, 189, 164, 49);
		frame.getContentPane().add(lblNewLabel_3);

		JRadioButton rdbtnDraft = new JRadioButton("Draft");
		rdbtnDraft.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnDraft.setBounds(221, 194, 86, 23);
		frame.getContentPane().add(rdbtnDraft);

		JRadioButton rdbtnCan = new JRadioButton("Can");
		rdbtnCan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnCan.setBounds(221, 257, 86, 23);
		frame.getContentPane().add(rdbtnCan);

		JRadioButton rdbtnBottle = new JRadioButton("Bottle");
		rdbtnBottle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnBottle.setBounds(221, 226, 86, 23);
		frame.getContentPane().add(rdbtnBottle);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnDraft);
		group.add(rdbtnBottle);
		group.add(rdbtnCan);

		JLabel lblNewLabel_5 = new JLabel("Ranking? (1-10)");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 297, 177, 28);
		frame.getContentPane().add(lblNewLabel_5);

		txtRating = new JTextField();
		txtRating.setBounds(221, 303, 86, 20);
		frame.getContentPane().add(txtRating);
		txtRating.setColumns(10);

		JButton btnRecommend = new JButton("Recommend");
		btnRecommend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Recommend nw = new Recommend();
				nw.recommend();
			}
		});
		btnRecommend.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRecommend.setBounds(262, 364, 125, 23);
		frame.getContentPane().add(btnRecommend);

		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				list nw = new list();
				nw.list();
			}
		});
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnList.setBounds(134, 364, 115, 23);
		frame.getContentPane().add(btnList);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtName.getText().toString();
				String location = txtLocation.getText().toString();
				String type = comboType.getSelectedItem().toString();
				double rating = 0;

				String form = "";
				if (rdbtnDraft.isSelected()) {
					form = "draft";
				} else if (rdbtnCan.isSelected()) {
					form = "can";
				} else {
					form = "bottle";
				}

				try {
					rating = Double.parseDouble(txtRating.getText());
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frame, "Rating must be a number.", "Warning",
							JOptionPane.WARNING_MESSAGE);
					txtRating.setText("");
				}
				if (rating < 1 || rating > 10) {
					JOptionPane.showMessageDialog(frame, "Rating must be between 1 and 10.", "Warning",
							JOptionPane.WARNING_MESSAGE);
					txtRating.setText("");
				} else if (name.isEmpty() == true || location.isEmpty() == true || type.isEmpty() == true
						|| form.isEmpty() == true) {
					JOptionPane.showMessageDialog(frame, "Please complete all fields.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {

					Beer newBeer = new Beer(name, location, type, form, rating);
					//String user = User.getUserName();

					Connection connection = null;
					Statement statement = null;
					String user = User.getUserName();
					String SQL = "insert into beer values (" + null + ", '" + newBeer.getName() + "' ,'"
							+ newBeer.getLocation() + "' ,'" + newBeer.getType() + "' ,'" + newBeer.getForm() + "' ,'"
							+ newBeer.getRating() + "','" + User.getUserName() +  "')";

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
					txtName.setText("");
					txtLocation.setText("");
					comboType.setSelectedItem("");
					/*
					 * rdbtnBottle.setSelected(false);
					 * rdbtnCan.setSelected(false);
					 * rdbtnDraft.setSelected(false);
					 */
					group.clearSelection();
					txtRating.setText("");
				} // end else
			}// end button
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(10, 364, 115, 23);
		frame.getContentPane().add(btnAdd);
	}
}

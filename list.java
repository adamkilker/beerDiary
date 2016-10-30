package beerdiary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class list {

	private JFrame frame;
	private JTable tableList;
	private JTextField txtBarName;

	public JTable getTableList() {
		return tableList;
	}

	/**
	 * Launch the application.
	 */
	public static void list() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					list window = new list();
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
	public list() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 0));
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 579, 176);
		frame.getContentPane().add(scrollPane);

		tableList = new JTable();
		scrollPane.setViewportView(tableList);
		tableList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableList.setBackground(new Color(255, 255, 153));
		tableList.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));

		JLabel lblNewLabel = new JLabel("Beer List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(252, 11, 124, 31);
		frame.getContentPane().add(lblNewLabel);

		JButton btnShow = new JButton("My List");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = JDBCMySQLConnection.getConnection();
				String sql = "select name, location, type, form, rating from beer where user ='" + User.getUserName() + "'";;

				try {

					java.sql.PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					tableList.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShow.setBounds(41, 301, 105, 23);
		frame.getContentPane().add(btnShow);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(451, 301, 89, 23);
		frame.getContentPane().add(btnClose);
		
		txtBarName = new JTextField();
		txtBarName.setBounds(171, 269, 124, 20);
		frame.getContentPane().add(txtBarName);
		txtBarName.setColumns(10);
		
		JComboBox comboType = new JComboBox();
		comboType.setBackground(Color.WHITE);
		comboType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboType.setModel(new DefaultComboBoxModel(new String[] {"", "Amber", "Blonde", "Brown", "Cream", "Dark", "IPA", "Light", "Pale", "Pilsner", "Porter", "Red", "Stout", "Wheat"}));
		comboType.setBounds(307, 270, 124, 20);
		frame.getContentPane().add(comboType);
		frame.setBounds(100, 100, 615, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnSortByBar = new JButton("Sort by Bar");
		btnSortByBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String barName = txtBarName.getText();
				Connection conn = JDBCMySQLConnection.getConnection();
				String sql = "select name, location, type, form, rating from beer where location ='" + barName + "'";

				try {

					java.sql.PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					tableList.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		btnSortByBar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSortByBar.setBounds(171, 301, 124, 23);
		frame.getContentPane().add(btnSortByBar);
		
		JButton btnSortByType = new JButton("Sort by Type");
		btnSortByType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String beerType = comboType.getSelectedItem().toString();
				Connection conn = JDBCMySQLConnection.getConnection();
				String sql = "select name, location, type, form, rating from beer where type ='" + beerType + "'" ;

				try {

					java.sql.PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					tableList.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSortByType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSortByType.setBounds(307, 301, 124, 23);
		frame.getContentPane().add(btnSortByType);
		
		JLabel lblNewLabel_1 = new JLabel("View your list or search other ranked beers by bar or type.");
		lblNewLabel_1.setBounds(136, 47, 365, 14);
		frame.getContentPane().add(lblNewLabel_1);
		

		
	}
}

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

public class list {

	private JFrame frame;
	private JTable tableList;

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

		JLabel lblNewLabel = new JLabel("Here's the Beer's You've Ranked");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(136, 30, 335, 31);
		frame.getContentPane().add(lblNewLabel);

		JButton btnShow = new JButton("Show List");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = JDBCMySQLConnection.getConnection();
				String sql = "select name, location, type, form, rating from beer";

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
		btnShow.setBounds(136, 276, 149, 23);
		frame.getContentPane().add(btnShow);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(345, 276, 89, 23);
		frame.getContentPane().add(btnClose);
		frame.setBounds(100, 100, 615, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

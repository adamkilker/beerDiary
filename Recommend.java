package beerdiary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Recommend {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void recommend() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recommend window = new Recommend();
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
	public Recommend() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 343, 185);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("What's for dinner?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(92, 18, 147, 23);
		panel.add(lblNewLabel);

		JComboBox foodList = new JComboBox();
		foodList.setModel(new DefaultComboBoxModel(new String[] { "", "Burgers", "Chicken", "Dessert", "Fried Foods",
				"Pasta", "Seafood", "Spicy Foods", "Steak" }));
		foodList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		foodList.setBounds(119, 52, 89, 20);
		panel.add(foodList);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String food = foodList.getSelectedItem().toString();

				String chickSeaPasta = " blonde ales and wheat beers";
				String friedFood = " blonde ales and lagers";
				String steakBurger = " pale, ameber, and brown ales or stouts";
				String spicy = " lighter lagers and IPAs";
				String dessert = " stouts and IPAs";

				String rec = " The best pairing for " + food + " are ";

				if (food == "Chicken" || food == "Seafood" || food == "Pasta") {
					rec += chickSeaPasta;
				} else if (food == "Fried Foods") {
					rec += food;
				} else if (food == "Dessert") {
					rec += dessert;
				} else if (food == "Spicy Foods") {
					rec += spicy;
				} else {
					rec += steakBurger;
				}

				if (food == "") {
					JOptionPane.showMessageDialog(frame, "Please select a food.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, rec, "Recommendation", JOptionPane.PLAIN_MESSAGE);
					foodList.setSelectedItem("");
				}
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(49, 100, 89, 23);
		panel.add(btnOk);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(168, 102, 89, 23);
		panel.add(btnClose);

	}
}

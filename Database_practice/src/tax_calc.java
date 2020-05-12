import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tax_calc extends JFrame {

	private JPanel contentPane;
	private JTextField txtamount;
	private final ButtonGroup options = new ButtonGroup();
	private JRadioButton rdonlytax;
	private JComboBox tax;
	private JRadioButton rdtaxand;
	private JButton calculate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tax_calc frame = new tax_calc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tax_calc() {
		setComponents();
		setEvents();
	}
	
	public void setComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel Amount = new JLabel("Amount $");
		
		txtamount = new JTextField();
		txtamount.setColumns(10);
		
		tax = new JComboBox();
		tax.setModel(new DefaultComboBoxModel(new String[] {"5%", "12%", "18%", "20%"}));
		
		JLabel lblNewLabel = new JLabel("Tax");
		
		rdonlytax = new JRadioButton("only tax");
		options.add(rdonlytax);
		
		rdtaxand = new JRadioButton("tax + amount");
		options.add(rdtaxand);
		
		calculate = new JButton("Calculate");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(calculate)
						.addComponent(rdtaxand)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(rdonlytax)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel)
										.addComponent(Amount))
									.addGap(21)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtamount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(200, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Amount)
						.addComponent(txtamount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addComponent(rdonlytax)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdtaxand)
					.addGap(18)
					.addComponent(calculate)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setEvents() {
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double amount = Double.parseDouble(txtamount.getText());
				int tax_am;
				double tax_sum;
				String taxAmount= (String) tax.getSelectedItem();
				tax_am = Integer.parseInt(taxAmount.split("%")[0]);
//				System.out.println(tax_am);
				tax_sum = amount*tax_am/100;
				if(rdonlytax.isSelected()) {
					JOptionPane.showMessageDialog(null, "Tax amount: $"+ tax_sum);
				}
				else if(rdtaxand.isSelected()) {
					JOptionPane.showMessageDialog(null, "the whole amount: $"+ (tax_sum+amount));
				}
				
			}
		});
	}
}

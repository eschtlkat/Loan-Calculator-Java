package frame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.JTextField;

import anuitetinis.calculatorAnuit;
import anuitetinis.secondFrameAnuit;
import linijinis.Calculator;
import linijinis.SecondFrame;

import javax.swing.JButton;

public class MyFrame extends JFrame implements ActionListener {

	JPanel panelOne,panelTwo, panelThree;
	
	JLabel labelOne, labelTwo, labelThree, labelFour, labelFive;
	
	JTextField textFieldLoan, textFieldPercentage;
	
	JComboBox<Integer> comboBoxMonths, comboBoxYears;
	
	JButton button, buttonAnuiteto, buttonLinijinis;
	public static int whichGraph = 0;

	int month = 0;

	int year = 0; 
	double loanSum, percentage;
	
	ImageIcon image;
	
	Calculator calculator1;
	calculatorAnuit calculator2;
	
	
	public MyFrame() {	
		this.setTitle("Busto paskolos skaiciuokle");
		
		labelOne = new JLabel("Pageidautina paskolos suma"); //create a label and set text of label
		labelTwo = new JLabel("Metai"); //create a label and set text of label
		labelThree = new JLabel("Menesiai"); //create a label and set text of label
		labelFour = new JLabel("Metinis procentas"); //create a label and set text of label
		labelFive = new JLabel("Pasirinkite grafika"); //create a label and set text of label
		
		
		
		panelOne = new JPanel();
		panelOne.setBackground(Color.white);
		panelOne.setLayout(new FlowLayout(FlowLayout.CENTER));

		
		panelTwo = new JPanel();
		panelTwo.setBackground(Color.white);
		
		panelThree = new JPanel();
		panelThree.setBackground(Color.white);
				
		
		button = new JButton("Ivykdyti");
		button.addActionListener(this);
		
		buttonAnuiteto = new JButton("Anuiteto grafikas");
		buttonAnuiteto.addActionListener(this);
		
		buttonLinijinis = new JButton("Linijinis grafikas");
		buttonLinijinis.addActionListener(this);
		
		textFieldLoan = new JTextField();
		textFieldLoan.setPreferredSize(new Dimension(100, 25));
		textFieldLoan.setFont(new Font("Consolas ", Font.PLAIN, 15));
		
		textFieldPercentage = new JTextField();
		textFieldPercentage.setPreferredSize(new Dimension(100, 25));
		textFieldPercentage.setFont(new Font("Consolas ", Font.PLAIN, 15));
		
		Integer[] months = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		comboBoxMonths = new JComboBox(months);
		comboBoxMonths.addActionListener(this);
		
		Integer[] years = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
		comboBoxYears = new JComboBox(years);
		comboBoxYears.addActionListener(this);
		
		this.add(panelOne, BorderLayout.NORTH);
		this.add(panelTwo, BorderLayout.CENTER);
		this.add(panelThree, BorderLayout.SOUTH);
		
		panelOne.add(labelOne);
		panelOne.add(textFieldLoan);
		panelOne.add(labelTwo);
		panelOne.add(comboBoxYears);
		panelOne.add(labelThree);
		panelOne.add(comboBoxMonths);
		panelOne.add(labelFour);
		panelOne.add(textFieldPercentage);
		panelTwo.add(labelFive);
		panelTwo.add(buttonAnuiteto);
		panelTwo.add(buttonLinijinis);
		panelThree.add(button);
		
		panelOne.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelOne.setPreferredSize(new Dimension(800, 100));
		panelTwo.setPreferredSize(new Dimension(800, 100));
		panelThree.setPreferredSize(new Dimension(800, 100));
		
		this.pack(); //make sure components fit
		this.setLocationRelativeTo(null);
		this.show();
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonAnuiteto) {
			whichGraph = 1;
			buttonAnuiteto.setEnabled(false);
			buttonLinijinis.setEnabled(true);
		}
		if (e.getSource() == buttonLinijinis) {
			whichGraph = 2;
			buttonLinijinis.setEnabled(false);
			buttonAnuiteto.setEnabled(true);
		}
		if(e.getSource() == comboBoxYears) {
			year = comboBoxYears.getSelectedIndex();
		}
		if(e.getSource() == comboBoxMonths) {
			month = comboBoxMonths.getSelectedIndex();
		}
		if(e.getSource() == button) {
			if (whichGraph == 0) {
				System.out.println("Pasirinkite grafika pries ivykdant.");
			}
			else {
				String loanSumString = textFieldLoan.getText();
				String percentageString = textFieldPercentage.getText();
				try {
					loanSum = Double.parseDouble(loanSumString);
					percentage = Double.parseDouble(percentageString);
					System.out.println("Pageidautina paskolos suma: " + loanSum);
					if (whichGraph == 1) {
						System.out.println("Bus vaizduojamas anuiteto grafikas.");
					}
					else {
						System.out.println("Bus vaizduojamas linijinis grafikas.");
					}
					
					System.out.println("Paskola pageidautina grazinti per "+ year +" metu ir " + month + " menesiu");
					System.out.println("Pasirinktas " + percentage + "% metinis procentas");
					button.setEnabled(false);
					textFieldLoan.setEditable(false);
					if (whichGraph == 2) {
						calculator1 = new Calculator(whichGraph, month, year, loanSum, percentage);
						this.dispose();
						SecondFrame secondFrame = new SecondFrame(Calculator.monthsDisplay, Calculator.monthlyPay,
				                calculator1.interest, calculator1.leftToPay, calculator1.loanSumAll);
					}
					else if (whichGraph == 1) {
						calculator2 = new calculatorAnuit(whichGraph, month, year, loanSum, percentage);
						this.dispose();
						secondFrameAnuit secondFrame = new secondFrameAnuit(calculatorAnuit.monthsDisplay, calculatorAnuit.monthlyPay,
				                calculator2.interest, calculator2.leftToPay, calculator2.loanSumAll);
					}
					
		
				} 
				catch (NumberFormatException ex) {
					System.err.println("Netinkama ivestis. Paskolos suma ir metinis procentas turi buti realieji skaiciai.");
					button.setEnabled(true);
				}		
				
			}	
		}
	}
}

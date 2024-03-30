package linijinis;

import java.awt.Dimension;
import loanCalculator.afterMain;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javafx.application.Platform;


public class SecondFrame extends Calculator implements ChangeListener, ActionListener{
	
	JFrame frame = new JFrame();
	JTable table;
	DefaultTableModel model1, model2;
	
	JPanel panelPostpone, panelSaveData;
	
	JSlider sliderFrom, sliderTo;
	
	JLabel filter, postpone, postponeFrom, postponeHowLong, postponeYearlyPercentage;
	
	JTextField TextFieldfrom, TextFieldhowLong, TextFieldYearlyPercentage;
	
	JButton submit, showGraph, saveData;

	int from = 0, howLong = 0;
	double yearlyPercentage;
	
	double loanSumLeft;
	
	int firstGraph = 0;

	public SecondFrame(ArrayList<Integer> monthsDisplay, ArrayList<Double> monthlyPay, 
            ArrayList<Double> interest, ArrayList<Double> leftToPay, double loanSumAll) {
		
		super(monthsDisplay, monthlyPay, interest, leftToPay, loanSumAll);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(510, 800);
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		
		
		createTable();
		
		filter = new JLabel("Filtruokite mokejimus nuo iki:");
		filter.setHorizontalTextPosition(JLabel.CENTER);
		filter.setFont(new Font("MV BOLI", Font.PLAIN, 28));
		frame.add(filter);
		
		sliderFrom = new JSlider();
		sliderFrom.setMinimum(1);
		sliderFrom.setMaximum(Calculator.allMonths);
		sliderFrom.setValue(1);
		
		//System.out.println("all months: "+Calculator.allMonths);
		
		sliderTo = new JSlider();
		sliderTo.setMinimum(1);
		sliderTo.setMaximum(Calculator.allMonths);
		sliderTo.setValue(Calculator.allMonths);
		
		formatSlider(sliderFrom);
		formatSlider(sliderTo);
		
		postponement();
		
		
		frame.setVisible(true);
		
		
		
	}
	
	public void postponement() {
		panelPostpone = new JPanel();
		panelPostpone.setBackground(Color.white);
		panelPostpone.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelPostpone.setPreferredSize(new Dimension(500, 300));
		
		postpone = new JLabel("Atideti mokejima");
		postpone.setHorizontalTextPosition(JLabel.CENTER);
		postpone.setFont(new Font("MV BOLI", Font.PLAIN, 28));
		frame.add(postpone);
		
		TextFieldfrom = new JTextField();
		TextFieldfrom.setPreferredSize(new Dimension(100, 25));
		TextFieldfrom.setFont(new Font("Consolas ", Font.PLAIN, 15));
		
		TextFieldhowLong = new JTextField();
		TextFieldhowLong.setPreferredSize(new Dimension(100, 25));
		TextFieldhowLong.setFont(new Font("Consolas ", Font.PLAIN, 15));
		
		TextFieldYearlyPercentage = new JTextField();
		TextFieldYearlyPercentage.setPreferredSize(new Dimension(100, 25));
		TextFieldYearlyPercentage.setFont(new Font("Consolas ", Font.PLAIN, 15));
		
		postponeFrom = new JLabel("Nuo kurio menesio?");
		postponeFrom.setFont(new Font("MV BOLI", Font.PLAIN, 10));
		postponeHowLong = new JLabel("Kiek menesiu?");
		postponeHowLong.setFont(new Font("MV BOLI", Font.PLAIN, 10));
		postponeYearlyPercentage = new JLabel("Metinis atidejimo procentas");
		postponeYearlyPercentage.setFont(new Font("MV BOLI", Font.PLAIN, 10));
		
		
		submit = new JButton("Vykdyti");
		submit.addActionListener(this);
		
		saveData = new JButton("Issaugoti ataskaita faile");
		saveData.addActionListener(this);
		
		
		
		panelPostpone.add(postpone);
		panelPostpone.add(postponeFrom);
		panelPostpone.add(TextFieldfrom);
		panelPostpone.add(postponeHowLong);
		panelPostpone.add(TextFieldhowLong);
		panelPostpone.add(postponeYearlyPercentage);
		panelPostpone.add(TextFieldYearlyPercentage);		
		panelPostpone.add(submit);
		panelPostpone.add(saveData);
		
		frame.add(panelPostpone);
		
	}
	
	public void formatSlider(JSlider slider) {
		slider.setBounds(100, 50, 300, 50);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(9);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(49);
		slider.addChangeListener(this);
		slider.setPaintLabels(true);
		slider.setFont(new Font("MV Boli", Font.PLAIN, 8));
		
		
		frame.add(slider);
	}
	
	public void createTable() {
		// Create a panel to hold the table
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 300);
        frame.add(panel);

        // Create columns for the table
        String[] columnHeaders = {"Menesis", "Ismoka", "Palukanos si menesi", "Liko moketi"};

        // Create a DefaultTableModel with specified columns
        model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(columnHeaders);

        // Create a JTable using the DefaultTableModel
        table = new JTable();
        table.setModel(model1);
        
        for (int i = 0; i < monthsDisplay.size(); i++) {
        	int month = monthsDisplay.get(i);
        	model1.addRow(new Object[]{month, monthlyPay.get(i) , interest.get(i), leftToPay.get(i)});
        }

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        
        
        showGraph = new JButton("Parodyti grafika");
		showGraph.addActionListener(this);
		frame.add(showGraph);
		
		}
	
	public void updateTableFilter(JSlider sliderFrom, JSlider sliderTo) {
		model1.setRowCount(0);
		
		System.out.println("SliderFrom pateko: " + sliderFrom.getValue());
		System.out.println("SliderTo pateko: " + sliderTo.getValue());
		
		for (int i = sliderFrom.getValue(); i <= sliderTo.getValue(); i++) {
        	int month = i;
        	model1.addRow(new Object[]{month, monthlyPay.get(i - 1), interest.get(i - 1), leftToPay.get(i - 1)});
        }
	}
	
	public void updateMonthsDisplayFilter(ArrayList<Integer> monthsDisplay, JSlider sliderFrom, JSlider sliderTo) {
		monthsDisplay.clear();
		//System.out.println("SliderFrom: " + sliderFrom.getValue());
		//System.out.println("SliderTo: " + sliderTo.getValue());
		for (int i = sliderFrom.getValue(); i <= sliderTo.getValue(); i++) {
			monthsDisplay.add(i);
			System.out.println("monthsDisplay value: "+ i);
		}
	}
	
	public void updateTablePostpone (int from, int howLong, double yearlyPercentage) {
		model1.setRowCount(0);
		leftToPay.clear();
		
		///////////////////debug
		System.out.println("BEFOREupdateTablePostpone: MonthDisplay size: " + monthsDisplay.size());
		System.out.println("BEFOREupdateTablePostpone:MonthlPay size" + monthlyPay.size());
		System.out.println("BEFOREupdateTablePostpone:leftToPay size "+ leftToPay.size());
		System.out.println("BEFOREupdateTablePostpone:Interest size: " + interest.size());
		System.out.println("BEFOREupdateTablePostpone: MonthDisplay index 0: " + monthsDisplay.get(0));
		
		loanSumLeft = Calculator.loanSum;
		
		for (int i = 0; i < monthsDisplay.size(); i++) {
			if (i < from - 1 || i >= from + howLong - 1 || (howLong == 0 && from == 0)) {
				double monthlyPayment = Calculator.loanSum / (allMonths - howLong + 1);
				monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0; 
				monthlyPay.set(i, monthlyPayment);
				
				double inter = loanSumLeft * Calculator.percentage / 12 / 100;
				inter = Math.round(inter * 100.0) / 100.0;
				interest.set(i, inter);
				
				loanSumLeft -= monthlyPayment;
				loanSumLeft = Math.round(loanSumLeft * 100.0) / 100.0; 
				leftToPay.add(loanSumLeft);
				
				//skaicuojam kitam kartui
				monthlyPayment = loanSumLeft / allMonths;
			}
			else if (i >= from - 1 && i < from + howLong - 1) { 
				monthlyPay.set(i, (double) 0);
				double tmp = Calculator.loanSum * yearlyPercentage / 100 / 12;
				tmp = Math.round(tmp * 100.0) / 100.0; 
				interest.set(i, tmp);
				
				leftToPay.add(leftToPay.get(from - 2));
			}
			
			
		}
		
		///////////////////debug
		System.out.println("AFTERupdateTablePostpone: MonthDisplay size: " + monthsDisplay.size());
		System.out.println("AFTERupdateTablePostpone:MonthlPay size" + monthlyPay.size());
		System.out.println("AFTERupdateTablePostpone:leftToPay size "+ leftToPay.size());
		System.out.println("AFTERupdateTablePostpone:Interest size: " + interest.size());
		System.out.println("AFTERupdateTablePostpone: MonthDisplay index 0: " + monthsDisplay.get(0));
		
		
		
		//System.out.println("Left to pay size: " + leftToPay.size());
		//System.out.println("3: MonthDisplay index 0: " + monthsDisplay.get(0));
		for (int i = 0; i < monthsDisplay.size(); i++) {
			int month;
			if (i == monthsDisplay.size() - 1) {
			month = allMonths;
			}
			else {
				month = monthsDisplay.get(i + 1);
			}
        	model1.addRow(new Object[]{monthsDisplay.get(i), monthlyPay.get(i), interest.get(i), leftToPay.get(i)});
        }
		//System.out.println("4: MonthDisplay index 0: " + monthsDisplay.get(0));
	}	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == sliderFrom || e.getSource() == sliderTo) { 
	        updateTableFilter(sliderFrom, sliderTo);
	        //System.out.println("sliderFrom: "+ sliderFrom.getValue());
	        //System.out.println("sliderTo " + sliderTo.getValue());
	    }
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == submit) {
			String fromString = TextFieldfrom.getText();
			String howLongString = TextFieldhowLong.getText();
			String yearlyPercentageString = TextFieldYearlyPercentage.getText();
			try {
				from = Integer.parseInt(fromString);
				howLong = Integer.parseInt(howLongString);
				yearlyPercentage = Double.parseDouble(yearlyPercentageString);
				
				updateTablePostpone(from, howLong, yearlyPercentage);
				updateTableFilter(sliderFrom, sliderTo);

			}
			catch (NumberFormatException ex) {
				System.err.println("Netinkama ivestis");
				submit.setEnabled(true);
			}
		}
		if(e.getSource() == saveData) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\laura\\Desktop\\AntrosProgramosAtaskaita\\ataskaita.txt"));
				bw.write("Menesis: Ismoka: Palukanos: Liko moketi: \n");
				for (int i = 0; i < monthsDisplay.size(); i++) {
					bw.write("     "+monthsDisplay.get(i) + "      "+monthlyPay.get(i)+"         "+interest.get(i)+"           "+ leftToPay.get(i)+"\n");
				}
				saveData.setEnabled(false);
				bw.close();
			} catch (Exception ex) {
				System.err.println("Nepavyko issaugoti i faila");
			}
		}
		
		if (e.getSource() == showGraph) {

			updateMonthsDisplayFilter(monthsDisplay, sliderFrom, sliderTo);

			updateTablePostpone(from, howLong, yearlyPercentage);
			Platform.setImplicitExit(false);
			Platform.runLater(() -> { 

				afterMain.doing();

		      });
		}
	}
	
	
	
	
	

}

package calculator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class SecondFrame extends Calculator implements ChangeListener, ActionListener{
	
	JFrame frame = new JFrame();
	JTable table;
	DefaultTableModel model1, model2;
	
	JSlider sliderFrom, sliderTo;
	
	JLabel filter, postpone;
	
	JTextField TextFieldfrom, TextFieldhowLong, TextFieldYearlyPercentage;
	
	JButton submit;
	
	int from, howLong;
	double yearlyPercentage;
	
	double loanSumLeft;

	public SecondFrame(ArrayList<Integer> monthsDisplay, ArrayList<Double> monthlyPay, 
            ArrayList<Double> interest, ArrayList<Double> leftToPay, double loanSumAll) {
		
		super(monthsDisplay, monthlyPay, interest, leftToPay, loanSumAll);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 800);
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		
		createTable();
		
		filter = new JLabel("Filtruokite mokejimus nuo iki:");
		filter.setHorizontalTextPosition(JLabel.CENTER);
		filter.setFont(new Font("MV BOLI", Font.PLAIN, 28));
		frame.add(filter);
		
		sliderFrom = new JSlider();
		sliderFrom.setValue(0);
		
		sliderTo = new JSlider();
		sliderTo.setValue(monthsDisplay.size() - 1);
		
		formatSlider(sliderFrom);
		formatSlider(sliderTo);
		
		postponement();
		
		frame.setVisible(true);
		
		
		
	}
	
	public void postponement() {
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
		
		submit = new JButton("Vykdyti");
		submit.addActionListener(this);
		
		frame.add(postpone);
		frame.add(TextFieldfrom);
		frame.add(TextFieldhowLong);
		frame.add(TextFieldYearlyPercentage);		
		frame.add(submit);
		
	}
	
	public void formatSlider(JSlider slider) {
		slider.setBounds(100, 50, 300, 50);
		slider.setMaximum(monthsDisplay.size() - 1);
		slider.addChangeListener(this);
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
        	model1.addRow(new Object[]{month, monthlyPay.get(i), interest.get(i), leftToPay.get(i)});
        }

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
	}
	
	public void updateTableFilter(JSlider sliderFrom, JSlider sliderTo) {
		model1.setRowCount(0);
		for (int i = sliderFrom.getValue(); i <= sliderTo.getValue(); i++) {
        	int month = monthsDisplay.get(i);
        	model1.addRow(new Object[]{month, monthlyPay.get(i), interest.get(i), leftToPay.get(i)});
        }
	}
	
	public void updateTablePostpone (int from, int howLong, double yearlyPercentage) {
		model1.setRowCount(0);
		
		// Ensure from is within bounds
	    from = Math.max(0, from - 1); // Adjust from to be 0-based index
	    howLong = Math.min(howLong, leftToPay.size() - from); // Adjust howLong accordingly
	    
		System.out.println(from);
		for (int i = from; i < howLong + from; i++) {
			monthlyPay.set(i, (double) 0);
			double tmp = leftToPay.get(0) * yearlyPercentage / 12;
			tmp = Math.round(tmp * 100.0) / 100.0; 
			interest.set(i, tmp);
		}
		
		loanSumLeft = leftToPay.get(from);
		for (int i = from; i <  Math.min(from + howLong, leftToPay.size()); i++) {
			leftToPay.set(i, loanSumLeft);
			loanSumLeft -= monthlyPay.get(i);
		}
		
		
		for (int i = 0; i < monthsDisplay.size(); i++) {
        	int month = monthsDisplay.get(i);
        	model1.addRow(new Object[]{month, monthlyPay.get(i), interest.get(i), leftToPay.get(i)});
        }
		
		
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == sliderFrom || e.getSource() == sliderTo) {
	        updateTableFilter(sliderFrom, sliderTo);
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
				submit.setEnabled(false);

			}
			catch (NumberFormatException ex) {
				System.err.println("Netinkama ivestis");
				submit.setEnabled(true);
			}
		}
	}
	
	
	
	

}

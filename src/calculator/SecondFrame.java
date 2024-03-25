package calculator;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class SecondFrame extends Calculator implements ChangeListener{
	
	JFrame frame = new JFrame();
	JTable table;
	DefaultTableModel model1, model2;
	
	JSlider sliderFrom, sliderTo;
	
	JLabel nuo, iki;

	public SecondFrame(ArrayList<Integer> monthsDisplay, ArrayList<Double> monthlyPay, 
            ArrayList<Double> interest, ArrayList<Double> leftToPay, double loanSumAll) {
		
		super(monthsDisplay, monthlyPay, interest, leftToPay, loanSumAll);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		frame.setLocationRelativeTo(null);
		
		createTable();
		
		//nuo = new JLabel("Nuo");
		//nuo.setBounds(50, 50, 50, 20);
		sliderFrom = new JSlider();
		sliderFrom.setBounds(100, 50, 300, 50);
		sliderFrom.setValue(0);
		sliderFrom.setMaximum(monthsDisplay.size() - 1);
		sliderFrom.addChangeListener(this);
		
		sliderTo = new JSlider();
		sliderTo.setBounds(100, 50, 300, 50);
		sliderTo.setMaximum(monthsDisplay.size() - 1);
		sliderTo.setValue(monthsDisplay.size() - 1);
		sliderTo.addChangeListener(this);
		
		frame.add(sliderFrom);
		frame.add(sliderTo);
		//frame.add(nuo);
		
		
		frame.setVisible(true);
		
		
		
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
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == sliderFrom || e.getSource() == sliderTo) {
	        updateTableFilter(sliderFrom, sliderTo);
	    }
		
	}

}

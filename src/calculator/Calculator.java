package calculator;

import java.util.ArrayList;

public class Calculator{
	int whichGraph, month, year; 
	double loanSum, percentage;
	
	////new
	int allMonths;
	
	double loanSumLeft, loanSumAll;
	
	ArrayList<Integer> monthsDisplay;
	ArrayList<Double> monthlyPay;
	ArrayList<Double> leftToPay;
	ArrayList<Double> interest;	
	
	public Calculator(int whichGraph, int month, int year, double loanSum, double percentage) {
		this.whichGraph = whichGraph;
		this.month = month;
		this.year = year;
		this.loanSum = loanSum;
		this.percentage = percentage;
		
		loanSumLeft = loanSum;
		loanSumAll = loanSum;
		
		allMonths = year * 12 + month;
		monthsDisplay = new ArrayList<Integer>(); // Create an ArrayList
		monthlyPay = new ArrayList<Double>();
		leftToPay = new ArrayList<Double>();
		interest = new ArrayList<Double>();
		
		calculatePayments();
		updateTable();
		
	}
	
	public void calculatePayments() {
		leftToPay.add(loanSumLeft);
		for (int i = 1; i <= allMonths; i++) {
			
			monthsDisplay.add(i);
			
			double monthlyPayment = this.loanSum / allMonths;
			monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0; 
			monthlyPay.add(monthlyPayment);
			
			double inter = loanSumLeft * this.percentage / 100;
			inter = Math.round(inter * 100.0) / 100.0;
			interest.add(inter);
			loanSumAll += inter;
			
			loanSumLeft -= monthlyPayment;
			loanSumLeft = Math.round(loanSumLeft * 100.0) / 100.0; 
			leftToPay.add(loanSumLeft);
			
		}
	}
	
	public void updateTable() {
		for (int i = 0; i < monthsDisplay.size(); i++) {
			System.out.print("Menesis: " + monthsDisplay.get(i));
			System.out.print(" Ismoka: " + monthlyPay.get(i));
			System.out.print(" Palukanos si menesi: " + interest.get(i));
			System.out.println(" Liko moketi: " + leftToPay.get(i));	
		}
		System.out.println("Visa grazinama suma: " + loanSumAll);
	}

}

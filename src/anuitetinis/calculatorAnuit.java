package anuitetinis;

import java.util.ArrayList;

public class calculatorAnuit{
	protected int whichGraph, month, year; 
	protected static double loanSum;
	protected static double percentage;
	
	////new
	protected static int allMonths;
	
	public double loanSumLeft, loanSumAll;
	
	public static ArrayList<Integer> monthsDisplay;
	public static ArrayList<Double> monthlyPay;
	public ArrayList<Double> leftToPay;
	public ArrayList<Double> interest;	
	
	public calculatorAnuit(int whichGraph, int month, int year, double loanSum, double percentage) {
		this.whichGraph = whichGraph;
		this.month = month;
		this.year = year;
		calculatorAnuit.loanSum = loanSum;
		calculatorAnuit.percentage = percentage;
		
		allMonths = year * 12 + month;
		monthsDisplay = new ArrayList<Integer>(); // Create an ArrayList
		monthlyPay = new ArrayList<Double>();
		leftToPay = new ArrayList<Double>();
		interest = new ArrayList<Double>();
		
		calculatePayments();
		
	}
	
	public calculatorAnuit(ArrayList<Integer> monthsDisplay, ArrayList<Double> monthlyPay, ArrayList<Double> interest, ArrayList<Double> leftToPay, double loanSumAll) {
		calculatorAnuit.monthsDisplay = monthsDisplay;
		calculatorAnuit.monthlyPay = monthlyPay;
		this.interest = interest;
		this.leftToPay = leftToPay;
		this.loanSumAll = loanSumAll;
}
	
	public void calculatePayments() {
		loanSumLeft = calculatorAnuit.loanSum;
		loanSumAll = calculatorAnuit.loanSum;
		for (int i = 1; i <= allMonths; i++) {
			
			monthsDisplay.add(i);
			
			double monthlyPayment = calculatorAnuit.loanSum / allMonths;
			monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0; 
			monthlyPay.add(monthlyPayment);
			
			double inter = loanSumLeft * (calculatorAnuit.percentage / 12 / 100);
			inter = Math.round(inter * 100.0) / 100.0;
			interest.add(inter);
			loanSumAll += inter;
			
			loanSumLeft = Math.round(loanSumLeft * 100.0) / 100.0; 
			leftToPay.add(loanSumLeft);
			loanSumLeft -= monthlyPayment;
			loanSumLeft = Math.round(loanSumLeft * 100.0) / 100.0; 
		}
		
		for (int i = 0; i < allMonths; i++) {
			double tmp = monthlyPay.get(i) + interest.get(i);
			tmp = Math.round(tmp * 100.0) / 100.0; 
			monthlyPay.set(i, tmp);
		}
		
	}
}

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
		updateTable();
		
	}
	
	public calculatorAnuit(ArrayList<Integer> monthsDisplay, ArrayList<Double> monthlyPay, ArrayList<Double> interest, ArrayList<Double> leftToPay, double loanSumAll) {
		calculatorAnuit.monthsDisplay = monthsDisplay;
		calculatorAnuit.monthlyPay = monthlyPay;
		this.interest = interest;
		this.leftToPay = leftToPay;
		this.loanSumAll = loanSumAll;
}
	
	public void calculatePayments() {
		loanSumLeft = this.loanSum;
		loanSumAll = this.loanSum;
		leftToPay.add(loanSumLeft);
		for (int i = 1; i <= allMonths; i++) {
			
			monthsDisplay.add(i);
			
			double monthlyPayment = calculatorAnuit.loanSum / allMonths;
			monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0; 
			monthlyPay.add(monthlyPayment);
			
			double inter = loanSumLeft * (calculatorAnuit.percentage / 12 / 100);
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
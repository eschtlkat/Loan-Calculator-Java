package anuitetinis;

import java.util.ArrayList;

import javafx.scene.Group;

public class calculatorAnuit{
	protected int whichGraph, month, year; 
	protected double loanSum, percentage;
	
	////new
	protected int allMonths;
	
	public double loanSumLeft, loanSumAll;
	
	public ArrayList<Integer> monthsDisplay;
	public ArrayList<Double> monthlyPay;
	public ArrayList<Double> leftToPay;
	public ArrayList<Double> interest;	
	
	public calculatorAnuit(int whichGraph, int month, int year, double loanSum, double percentage) {
		this.whichGraph = whichGraph;
		this.month = month;
		this.year = year;
		this.loanSum = loanSum;
		this.percentage = percentage;
		
		allMonths = year * 12 + month;
		monthsDisplay = new ArrayList<Integer>(); // Create an ArrayList
		monthlyPay = new ArrayList<Double>();
		leftToPay = new ArrayList<Double>();
		interest = new ArrayList<Double>();
		
		for (int i = 0; i < allMonths; i++) {
			interest.add(0.0);
			leftToPay.add(0.0);
			monthlyPay.add(0.0);
		}
		calculatePayments();
		updateTable();
		
	}
	
	public calculatorAnuit(ArrayList<Integer> monthsDisplay, ArrayList<Double> monthlyPay, ArrayList<Double> interest, ArrayList<Double> leftToPay, double loanSumAll) {
		this.monthsDisplay = monthsDisplay;
		this.monthlyPay = monthlyPay;
		this.interest = interest;
		this.leftToPay = leftToPay;
		this.loanSumAll = loanSumAll;
}
	
	public void calculatePayments() {
		loanSumLeft = this.loanSum;
		loanSumAll = this.loanSum;
		
		double totalInterest = 0;
		for (int i = 0; i < allMonths; i++) {
			monthsDisplay.add(i + 1);
			
			double inter = loanSumLeft * (this.percentage / 12 / 100);
			inter = Math.round(inter * 100.0) / 100.0;
			totalInterest += inter;
		}
		
		double averageInterest = totalInterest / allMonths;
		averageInterest = Math.round(averageInterest * 100.0) / 100.0;
		
		double monthlyPayment = loanSumAll / allMonths;
		for (int i = 0; i < allMonths; i++) {
			leftToPay.set(i, loanSumLeft);
			
			double fullMonthlyPayment = monthlyPayment + averageInterest;
			fullMonthlyPayment = Math.round(fullMonthlyPayment * 100.0) / 100.0;
			monthlyPay.set(i, fullMonthlyPayment);
			interest.set(i, averageInterest);
			
			loanSumLeft -= monthlyPayment;
			loanSumLeft = Math.round(loanSumLeft * 100.0) / 100.0;
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
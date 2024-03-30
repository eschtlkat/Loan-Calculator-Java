package loanCalculator;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import linijinis.Calculator;
import linijinis.SecondFrame;

public class afterMain extends Application {
    
    @Override
    public void start(Stage stage) {       
        
    }


	public static void run() {
		launch();
	}
	
	static public void doing() {
		Stage stage = new Stage();
		NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Months");
        yAxis.setLabel("Monthly Payment");

        // Creating the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Monthly Payments");

        // Creating a series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Monthly Payments");

        // Adding data to the series
        for (int i = 0; i < SecondFrame.monthsDisplay.size(); i++) {
            series.getData().add(new XYChart.Data<>(SecondFrame.monthsDisplay.get(i), SecondFrame.monthlyPay.get(i)));
        }

        // Adding the series to the line chart
        lineChart.getData().add(series);

        // Creating a scene
        HBox hbox = new HBox();
        hbox.getChildren().add(lineChart);
        Scene scene = new Scene(hbox, 800, 600);

        // Setting the scene
        stage.setScene(scene);

        // Showing the stage
        stage.show();
	}

}

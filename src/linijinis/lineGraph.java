package linijinis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class lineGraph extends Application {

    public ArrayList<Integer> monthsDisplay;
    public ArrayList<Double> monthlyPay;

    public lineGraph(ArrayList<Integer> monthsDisplay, ArrayList<Double> monthlyPay) {
        this.monthsDisplay = monthsDisplay;
        this.monthlyPay = monthlyPay;
    }


    @Override
    public void start(Stage stage) {       
        // Defining the axes
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
        for (int i = 0; i < monthsDisplay.size(); i++) {
            series.getData().add(new XYChart.Data<>(monthsDisplay.get(i), monthlyPay.get(i)));
        }

        // Adding the series to the line chart
        lineChart.getData().add(series);

        // Creating a scene
        HBox hbox = new HBox();
        hbox.getChildren().add(new Button());
        Scene scene = new Scene(hbox, 800, 600);

        // Setting the scene
        stage.setScene(scene);

        // Showing the stage
        stage.show();
    }

}
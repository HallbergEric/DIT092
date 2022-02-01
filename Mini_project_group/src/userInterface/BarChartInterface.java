package userInterface;

import mini_project_group.Activity;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarChartInterface
{
	    public void chart1(ArrayList <Activity> activities) 
	    {
	    	Stage stage = new Stage();
			stage.setTitle("Progress Bar");
			
		    CategoryAxis xAxis = new CategoryAxis();
	        xAxis.setLabel("Activities");

	        NumberAxis yAxis = new NumberAxis();
	        yAxis.setLabel("Progress");

	        BarChart barChart = new BarChart(xAxis, yAxis);
	        barChart.setMaxWidth(400);
	        
	        XYChart.Series dataSeries1 = new XYChart.Series();
	        dataSeries1.setName("Progress for activities");
	        
	        for(Activity act : activities)	    	
    	    {	        
	        	for(int i = 0; i < activities.size(); i++)
		        {
		    		dataSeries1.getData().add(new XYChart.Data(act.getName(), act.getProgress()));
		        }
	    	}	        
	        barChart.getData().add(dataSeries1); 
	        
	        VBox vbox = new VBox(barChart);
	        Scene scene = new Scene(vbox, 400, 200);

	        stage.setScene(scene);
	        stage.setHeight(300);
	        stage.setWidth(300);

	        stage.show();
	    }
}//Class




package userInterface;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
 
public class DialogsInterface 
{
	public  String dialogGetTextInput (String title, String header, String content)
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
	
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent())
		{
			return result.get();
		}
		return null;
	}//dialogGetTextInput
	
	public int dialogGetIntegerInput (String title, String header, String content) throws NumberFormatException
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);					
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent())
		{
				return Integer.parseInt(result.get());					
		}
		return 0;
	}//dialogGetIntegerInput
		
	public double dialogGetDoubleInput (String title, String header, String content)
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
	
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent())
		{
			try
			{				
				return Double.parseDouble(result.get());
			}
			catch (NumberFormatException e)
			{
			}
		}
		return 0;
	}//dialogGetTextInput
	
	public void errorWindow(String userMessage)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(userMessage);
		alert.showAndWait();		
	}
	
	public void displayTimeWindow(String timeSpent, String name)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Time spent by " + name);
		alert.setTitle("Total time spent");	
		alert.setContentText(timeSpent);
		alert.showAndWait();	
	}
	
	public void displayCalcWindow(String ev, String sv, String cv)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Calculations");
		alert.setHeaderText("Choose which calculation you want to print");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Ev");
		ButtonType buttonTypeTwo = new ButtonType("Sv");
		ButtonType buttonTypeThree = new ButtonType("Cv");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne)
		{
			showCalc(ev);
		} 
		else if (result.get() == buttonTypeTwo) 
		{
			showCalc(sv);
		} 
		else if (result.get() == buttonTypeThree) 
		{
		    showCalc(cv);
		} 
		else {
		    // ... user chose CANCEL or closed the dialog
		}	
	}//DisplayCalcWindow
	
	public void showCalc(String calc)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Total cost");
		alert.setHeaderText(null);
		alert.setContentText(calc);
		alert.showAndWait();
	}
	
}



package riskMatrix;

import java.util.*;

import mini_project_group.Main;
import javafx.stage.Stage;


public class RiskMain {
		
    private static final int PRINT_RISK_MATRIX = 1;
    private static final int ADD_RISK = 2;
    private static final int REMOVE_RISK = 3;
    private static final int LEAVE_RISK_MENU = 4;
    
    private Scanner input = new Scanner(System.in);
    
    private RiskMatrix rm = new RiskMatrix();
    private Stage stage;
    private Main tempMain;
    
    public RiskMatrix getRiskMatrix() {
    	return this.rm;
    }
   
    public void runRisk(Stage stage, Main main) {
    	this.stage = stage;
    	this.tempMain = main;
    	rm.addMain(main);
        int option;
        do {
            printMenuOptions();
            System.out.print(" Type the option number: " + "\n");
            try
            {
            	option = input.nextInt();
            } catch (InputMismatchException e) 
            {
            	option = 9999;
            }
            
            input.nextLine();
            switch (option) {
            
                case PRINT_RISK_MATRIX:
                    rm.syncRows();
                	rm.print();
                    break;
                    
                case ADD_RISK:
                    addRisk();
                    break;
                    
                case REMOVE_RISK:
                    try {
                        removeRisk();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    break;
                    
                case LEAVE_RISK_MENU:
                    System.out.println("Leaving Risk Manager");
                    stage.requestFocus();
                    stage.toFront();
                    break;
                    
                default:
                    System.out.println("Option " + option + " is not valid.");
                    System.out.println();                   
                    break;
            }
        } while (option != LEAVE_RISK_MENU);
    }

    private void printMenuOptions() {
        System.out.println(" === Welcome to Risk Matrix Manager === " + "\n");
        System.out.println(" Choose an option below: ");
        System.out.println(" ");
        System.out.println(" 1. Print risk matrix. ");
        System.out.println(" 2. Add risk. ");
        System.out.println(" 3. Remove risk. ");
        System.out.println(" 4. Back to main menu. ");
        System.out.println();
    }

    public void addRisk() {
        String riskName;
        int riskProbability;
        int riskImpact;
        System.out.println("Enter risk name:");
        riskName = input.nextLine();
        System.out.println("Enter" + riskName + "Probability:");
        riskProbability = input.nextInt();
        System.out.println("Enter" + riskName + "impact:");
        riskImpact = input.nextInt();
        rm.addRisk(riskName, riskProbability, riskImpact, tempMain);
    }

    public void removeRisk() throws NullPointerException{
        String riskName;
        System.out.println("Enter risk name to remove:" + "\n");
        riskName = input.nextLine();
        rm.removeRisk(riskName);
    }

}

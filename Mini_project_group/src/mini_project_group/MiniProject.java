package mini_project_group;

import userInterface.BarChartInterface;
import userInterface.DialogsInterface;
import riskMatrix.RiskMain;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MiniProject extends Application //implements EventHandler 
{
	
	private Stage window;
	private TreeView<String> tree;
	private TableView <Member> tableView1;
	private TableView <Project> tableView2;
	private TableView <Activity> tableView3;	
	private BarChartInterface progress;
	private DialogsInterface dialog;
	private SimpleDateFormat simpleDateFormat;
	private Calculations calc;
	private RiskMain risks;
	private Main main; 
	
	public MiniProject()
	{
		this.main = new Main();
		this.risks= new RiskMain();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	//Below is the primary interface that shows when you run the program
	@Override
	public void start(Stage primaryStage)
	{
		window = new Stage();
		window = primaryStage;
		window.setTitle("Window");
		window.setWidth(1500);
		window.setHeight(600);
		
		TreeItem<String> root, createAndRemoveMenu, 
			printOptions, gson;
		
		//Root
		root = new TreeItem<>();
		root.setExpanded(true);
		
		createAndRemoveMenu = addLeaf("Create and remove", root);
		addLeaf("Create project", createAndRemoveMenu);
		addLeaf("Create member", createAndRemoveMenu);
		addLeaf("Create Activity", createAndRemoveMenu);
		addLeaf("Add activity to member", createAndRemoveMenu);
		addLeaf("Remove project", createAndRemoveMenu);
		addLeaf("Remove a member", createAndRemoveMenu);
		addLeaf("Remove a activity", createAndRemoveMenu);
		
		printOptions = addLeaf("Print options", root);
		addLeaf("Print individual activity", printOptions);
		addLeaf("Print individual time spent on the project", printOptions);
		addLeaf("Print risk matrix", printOptions);
		addLeaf("Print Schedule", printOptions);
		addLeaf("Print calculations", printOptions);
		
		gson = addLeaf("Save and write gson", root);
		addLeaf("Save to gson", gson); 		
		addLeaf("Load from gson", gson);	
		
		//create tree
		tree = new TreeView<>(root);
		tree.setShowRoot(false);
		
		tree.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {          
		    	dialog = new DialogsInterface();
	            TreeItem<String> item = tree.getSelectionModel().getSelectedItem();
	            String leafTitle = item.getValue();
				String name = ""; 
				String startDate= ""; 
				String endDate = "";
				int budget = 0;
				int salary = 0;
				int startWeek = 0;
				int endWeek = 0;
				int idMember = 0;
				int idActivity = 0;
				double totalTimeActivity = 0;
				double totalTimeMember = 0;

				
				switch (leafTitle)
				{
					case "Create project":						
						createProject(name, budget, startDate, endDate);						
					break;
					
					case "Create member":
						createMember(name, salary, totalTimeMember);
					break;
					
					case "Create Activity":
						createActivity(name, startWeek, endWeek, totalTimeActivity);
					break;
					
					case "Add activity to member":
						addActivity(idMember, idActivity);					
					break;	
																
					case "Remove project":
						removeProject();																																																																																						
					break;
					
					case "Remove a member":
						removeMember(idMember);				
					break;
						
					case "Remove a activity":					
						removeActivity(idActivity);
					break;																								
						
					case "Print individual activity":
						individualActivity(idMember);																					
					break;
					
					case "Print individual time spent on the project":
						individualTime(idMember, totalTimeMember);											
					break;					
					
					case "Print risk matrix":
						riskMatrix(primaryStage);
					break;
					
					case "Print calculations":
						calculations();					
					break;
						
					case "Save to gson":
						saveToGson(name);
					break;
					
					case "Load from gson":
						loadFromGson(name);				
					break;
									
					case "Print Schedule":
						schedule();
					break;
						
				}//switch
		    }//handle
		});//tree.setOnMouseClicked
		
		//Layout for menubar, which is shown when you press "menu"
		Menu pMenu = new Menu("Menu");
		MenuItem menu1 = new MenuItem("Set progess in activity");
		pMenu.getItems().add(menu1);
		
		MenuBar menubar = new MenuBar();
		menubar.getMenus().add(pMenu);
		
		VBox layout = new VBox(menubar);
		
		//Function to set the progress of an activity
		menu1.setOnAction(e -> 
		{
			try
			{
				int idActivity = dialog.dialogGetIntegerInput("Set progress", "Enter id of activity", "Id");				
				double progressActivity = dialog.dialogGetDoubleInput("Set progress", "Enter progress", "Progress");
				Activity act = main.searchForActivity(idActivity);

				if(act == null)
				{
					dialog.errorWindow("Activity doesn't exist");
				}else if(progressActivity < 0)
				{
					dialog.errorWindow("Negative values not allowed");
				}else if(progressActivity > 100)
				{
					dialog.errorWindow("Value higher than 100 not allowed");
				}else
				{
					main.setProgressActivity(idActivity, progressActivity);	
				}												
			}
			catch(NullPointerException ex)
			{
				
			}
			catch(NumberFormatException ex)
			{
				dialog.errorWindow("Incorrect input");
			}
			
		});	//menu1.setOnAction

		//Below is to show all the different tables in the interface that is shown, total of 3 tables 
	  	tableView1 = new TableView<>();
	    TableColumn<Member, String> firstColumn =  new TableColumn<>("Member name");
	    firstColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    
	    TableColumn<Member, Integer> secondColumn = new TableColumn<>("Member id");
	    secondColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("id"));
	    
	    tableView1.getColumns().add(firstColumn);
	    tableView1.getColumns().add(secondColumn);

	  	tableView2 = new TableView<>();
	  	tableView3 = new TableView<>();
	  	
		SplitPane split = new SplitPane(tree, tableView1, tableView2, tableView3);
			
	    TableColumn<Project, String> firstColumn2 =  new TableColumn<>("Project name");
	    firstColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
	    
	    TableColumn<Project, Integer> secondColumn2 =  new TableColumn<>("Project id");
	    secondColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
	    
	    tableView2.getColumns().add(firstColumn2);
	    tableView2.getColumns().add(secondColumn2);
	    
	    TableColumn<Activity, String> firstColumn3 = new TableColumn <>("Activity name");
	    firstColumn3.setCellValueFactory(new PropertyValueFactory<>("name"));
	    
	    TableColumn<Activity, Integer> secondColumn3 = new TableColumn<>("Activity id");
	    secondColumn3.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	    
	    tableView3.getColumns().add(firstColumn3);
	    tableView3.getColumns().add(secondColumn3);
    	    
		layout.getChildren().add(split);
		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);
		window.show();
	}//start
	
	public void updateMemberTable()
	{
		Project pro = main.searchForProject(1);
		if(pro == null)
		{
			dialog.errorWindow("You must create a project before creating a member");
		}
		else
		{
			ArrayList <Member> members = pro.getAllMembers();				
			tableView1.getItems().clear();
		
			for(Member mem : members)
			{
				tableView1.getItems().add(mem);
			}
		}
	}
	
	public void updateProjectTable( )
	{
		Project pro = main.searchForProject(1);		
		tableView2.getItems().clear();	
		tableView2.getItems().add(pro);		
	}
	 
	public void updateActivityTable()
	{
		Project pro = main.searchForProject(1);		
		tableView3.getItems().clear();

		ArrayList<Activity> activities = pro.getAllActivities();
		
		for(Activity act: activities)
		{
			tableView3.getItems().add(act);					
		}	
	}
		
	private TreeItem<String> addLeaf(String title, TreeItem<String> parent) 
	{
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		
		return item;
	}//addLeaf
	
	//Prints the barChart when You click print individual activity
	public void barChart(int id)
	{
		ArrayList <Activity> activities = new ArrayList <>();
		Member mem = main.searchForMember(id);
	
		try
		{
			for(Activity act : mem.getAllActivity())
			{
				activities = mem.getAllActivity();
			}
		}
		catch(NullPointerException e)
		{
			
		}
		progress.chart1(activities);							
	}//barChart 		
	
	public void createProject(String name, int budget, String startDate, String endDate)
	{
		simpleDateFormat = new SimpleDateFormat ("ddMMyyyy");
		
		try 
		{
			name = dialog.dialogGetTextInput("Project", "Enter project name", "Name:");
			budget = dialog.dialogGetIntegerInput("Project", "Enter project budget", "Budget");
			startDate = dialog.dialogGetTextInput("Project ", "Enter start date, ddMMyyyy ", "Start date ");
			endDate = dialog.dialogGetTextInput("Project", "Enter end date, ddMMyyyy", "End date");
			
			Date start = simpleDateFormat.parse(startDate);
			Date end = simpleDateFormat.parse(endDate);
		
			if(name.equals(null) || name.equals(""))
			{														
				dialog.errorWindow("Cannot create projcet with empty name");
			}
			
			else if(end.getTime() < start.getTime())
			{
				dialog.errorWindow("Cannot set start date bigger than end date");
			}							
			else if(name.isEmpty() != true)																									
			{															 																							
				main.createProject(name, budget, startDate, endDate);
				updateProjectTable();
			}						
		}
		catch(NullPointerException e)
		{
			
		}
		catch(NumberFormatException e)
		{
			dialog.errorWindow("incorrect input");
		} 
		catch (ParseException e) 
		{
			dialog.errorWindow("ASDASDASD");
		}
	}
	
	public void createMember(String name, int salary, double totalTimeMember)
	{
		name = dialog.dialogGetTextInput("Member", "Enter member name", "Name");

		try
		{
			salary = dialog.dialogGetIntegerInput("Member", "Enter member salary", "Salary");
			totalTimeMember = dialog.dialogGetDoubleInput("Member", "Enter member time", "Time");
			
			if(name.equals(null) || name.equals(""))
			{
				dialog.errorWindow("Cannot create member with empy name");
			}
			else if(totalTimeMember < 0 || salary < 0)
			{
				dialog.errorWindow("Negative value not allowed");
			}
			else if(name.isEmpty() != true)
			{
				main.createMember(name, salary, totalTimeMember);
				updateMemberTable();
			}
		}
		catch(NullPointerException e)
		{
			
		}
		catch(NumberFormatException e)
		{
			
		}
	}//createMember
	
	public void createActivity(String name, int startWeek, int endWeek, double totalTimeWorked)
	{
		name = dialog.dialogGetTextInput("Activity", "Activity name", "Name");
		
		try 
		{
			startWeek = dialog.dialogGetIntegerInput("Activity", "Start week", "Week");
			endWeek = dialog.dialogGetIntegerInput("Activity", "End week", "Week");
			totalTimeWorked = dialog.dialogGetDoubleInput("Activity ", "Enter Activity time ", "Time ");
			
			if(name.equals(null) || name.equals(""))
			{
				dialog.errorWindow("You must enter a name for the activity");
			}
								
			else if(startWeek < 0 || endWeek < 0 || totalTimeWorked < 0)
			{
				dialog.errorWindow("Negative time not allowed");
			}
			else if(startWeek > 0 && endWeek <= 9 && endWeek > startWeek) 																												
			{								
				main.createActivity(name, startWeek, endWeek, totalTimeWorked);
				updateActivityTable();						
			}
		}
		catch(NullPointerException e)
		{		
			
		}	
		catch(NumberFormatException e)
		{
			dialog.errorWindow("No correct input");
		}//crete
	}//createActivity
	
	public void addActivity(int idMember, int idActivity)
	{
		try
		{
			idMember = dialog.dialogGetIntegerInput("Member", "Member id", "id");
			idActivity = dialog.dialogGetIntegerInput("Activity", "Activity id", "Id");
			Activity act = main.searchForActivity(idActivity);
			Member mem = main.searchForMember(idMember);
			
			if(idMember == 0)
			{
				
			}							
			else
			{
				Activity foundMemActivity = mem.getActivity(idActivity);
				
				if(act == null)
				{
					dialog.errorWindow("Cant find activity");
				}
				else if(foundMemActivity != null)
				{
					dialog.errorWindow("The activity have already been added");
				}
				else if(act != null)
				{				
					main.addActivity(act, idMember);
				}
			}					
		}
		catch(NumberFormatException e)
		{
			dialog.errorWindow("Incorrect input");
		}
		catch(NullPointerException e)
		{
			dialog.errorWindow("Can't find member");
		}	
	}//addActivity
	
	public void removeProject()
	{
			Boolean removed = main.removeProject(1);
			
			if(removed == true)
			{
				tableView1.getItems().clear();
				tableView2.getItems().clear();
				tableView3.getItems().clear();
			}
			else if(removed == false)
			{
				dialog.errorWindow("Cannot find project");
			}

	}//removeProject
	
	public void removeMember(int idMember)
	{ 
		try
		{
			idMember = dialog.dialogGetIntegerInput("Remove member", "Enter member id", "Id");					
			Boolean removed = main.removeMember(idMember);
			
			if(idMember == 0)
			{
				
			}
			else if(removed == true)
			{
				updateActivityTable();
				updateMemberTable();
			}
			else if(removed == false)
			{
				dialog.errorWindow("Member dosn't exist");
			}
		}
		catch(NumberFormatException e)
		{
			dialog.errorWindow("No correct inout");
		}
		catch(NullPointerException e)
		{
			
		}			
	}//removeMember
	
	public void removeActivity(int idActivity)
	{
		try
		{
			idActivity = dialog.dialogGetIntegerInput("Remove activity", "Enter activity id", "Id");
			Boolean removed = main.removeActivity(idActivity);
		
			if( idActivity == 0)
			{
				
			}
			
			else if(removed == true)
			{
				main.removeActivity(idActivity);
				updateActivityTable();
			}
			else if(removed == false)
			{
				dialog.errorWindow("The member does not exist");
			}
										
		}
		catch(NumberFormatException e)
		{
			dialog.errorWindow("Incorrect input");
		}
	}//removeActivity
	
	public void individualActivity(int idMember)
	{

		try
		{
			idMember = dialog.dialogGetIntegerInput("Finding member", "Enter member id", "Id");
			Member mem1 = main.searchForMember(idMember);
		
			if(mem1 != null)
			{
				progress = new BarChartInterface();						
				barChart(idMember);
			}
			else
			{
				dialog.errorWindow("The member does not exist");
			}
		}
		catch(NumberFormatException e)
		{
			dialog.errorWindow("Not correct input");
		}
	}//individualActivity
	
	public void individualTime(int idMember, double totalTimeMember)
	{
		String name1 = "";
		
		try
		{
			idMember = dialog.dialogGetIntegerInput("Finding member", "Enter member id", "Id");
			Member mem1 = main.searchForMember(idMember);	
			
			if(idMember == 0 )
			{
				
			}					
			else if(mem1 != null)
			{
				name1 = mem1.getName();
				totalTimeMember = mem1.getTotTimeWorked();
				String number = Double.toString(totalTimeMember);
				dialog.displayTimeWindow(number, name1);
			}
			else if(mem1 == null)
			{
				dialog.errorWindow("Cannot find member");
			}
					
		}
		catch(NullPointerException e)
		{
			
		}
		catch(NumberFormatException e)
		{
			dialog.errorWindow("incorrect input");
		}
	}//individualTime
	
	public void riskMatrix(Stage primaryStage)
	{
		try
		{
			risks.runRisk(primaryStage, main);	
		}
		catch(NullPointerException e)
		{
			dialog.errorWindow("Create a project first");
		}
	}//riskMatrix
	
	public void calculations()
	{
		
		try
		{
			Project pro = main.searchForProject(1);				

			if(pro == null)
			{
				dialog.errorWindow("Project not created");
			}
			else
			{
				calc = new Calculations(main);
				calc.run();
				
				double ev = calc.getEv();
				double sv = calc.getSv();
				double cv = calc.getCv();
				String stringEv = Double.toString(ev);
				String stringSv = Double.toString(sv);
				String stringCv = Double.toString(cv);
				dialog.displayCalcWindow(stringEv, stringSv, stringCv);
			}						
		}
		catch(NumberFormatException e)
		{
			dialog.errorWindow("incorrect input");
		}
		catch(InputMismatchException e)
		{
			dialog.errorWindow("Incorrect input");
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}//calculations
	
	public void saveToGson(String name)
	{
		 name = dialog.dialogGetTextInput("Name Json file", "Enter file name", "Name");
		 Project pro2 = main.searchForProject(1);	
		
		 try 
		 {
			 
			 if(name.equals(null) || name.equals(""))
			 {
				 dialog.errorWindow("Please enter a correct input");
			 } 
			 else if(pro2 == null)
			 {
				 dialog.errorWindow("The project does not exist");
			 }
			 
			 else if(name.isEmpty() != true)
			 {
				 main.writeToGson(1, name);
			 }
		 }
		 catch(NullPointerException e)
		 {
			
		 }	

	}//saveToGSon
	
	public void loadFromGson(String name)
	{
		name = dialog.dialogGetTextInput("Read Json file", "Enter file name", "Name");
		 
		try 
		{
			if(name.equals(null) || name.equals(""))
			 {
				 dialog.errorWindow("Please enter a correct input");
			 }
			 else 
			 {
				 main.readFromGson(name);
				 updateProjectTable();
				 updateMemberTable();
				 updateActivityTable();							 
			 }
		}
		catch(NullPointerException e)
		{
			
		} 
		catch (FileNotFoundException e) 
		{
			
			dialog.errorWindow("Cannot find filename");
		}
	}//loadFromGson
	
	public void schedule()
	{
		Schedule schedule = new Schedule();
		schedule.addActivites(main);
		schedule.print();
	}
}//class

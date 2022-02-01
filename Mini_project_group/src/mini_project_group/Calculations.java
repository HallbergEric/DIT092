package mini_project_group;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.TimerTask; 

public class Calculations extends TimerTask
{
	
		private double ev;							//Earned Value
		private double cv;							//Cost Variance
		private double sv;							//Schedule Variance
		private double av;							//Actual Value
		private double projectTime; 				//in hours, for the whole project
		private double planedTime = 20; 			//in days, for the whole project
		private Main main;
		
	public Calculations(Main tempMain) {
		this.main = tempMain;
	}
		
	public double projectTime(Project pro) {
		
		ArrayList <Member> members;
		projectTime = 0;
		
		members = pro.getAllMembers();
		
		for (Member mem : members) 
		{			
			projectTime += mem.getTotTimeWorked();
		}
		
		return projectTime;
	}
		
	public double calculateAv(Project pro) {
		
		double pt = projectTime(pro);
		
		double salary = 230;
		double av = pt * salary; //The hours worked on the project*the salary
		return av;
		
	}
	
	/*Since we struggled with the calculations because of miss understandings in A#1
	 * we have spoken to Gül and will do the calculations based on the whole project 
	 * and not for each task. One of the team members did not register the hours worked
	 * which led to us have to make an estimation of the hours spent. the estimation is
	 * based on an average of what the other members have worked.*/
	public double calculateEv(Project pro) {
		
		double budget = pro.getBudget();
		double ev;
		
		try 
		{
			ev = (planedTime/pro.getDayCount())*budget;
			return ev;		
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	public double calculateCv(Project pro)
	{
		double ev = calculateEv(pro);
		double av = calculateAv(pro);
		double cv = ev - av;
		return cv;
	}
	
	public double calculateSv(Project pro)
	{
		double ev = calculateEv(pro);
		double sv = ev - pro.getBudget();
		return sv;
	}	
	
	/*a timer that makes the following calculations
	 * count every second week*/
	
	@Override
	public void run()
	{
		Project pro = main.searchForProject(1);
		ev = calculateEv(pro);
		sv = calculateSv(pro);
		cv = calculateCv(pro);		

	}
	
	double getEv ()
	{
		return this.ev;
	}
	
	double getSv()
	{
		return this.sv;
	}
	
	double getCv()
	{
		return this.cv;
	}
	
}

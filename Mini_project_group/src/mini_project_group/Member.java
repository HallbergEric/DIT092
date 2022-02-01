package mini_project_group;

import java.util.ArrayList;
import java.util.Iterator;

public class Member {
	
	private String name;
	private double salary;
	private int id;
	private double totTimeWorked;
	private ArrayList<Activity> memberActivity;
	private static int nextId = 1;
		
	public Member (String name, double salary, double totTimeWorked) {
		this.name = name;
		this.salary = salary;
		this.totTimeWorked = totTimeWorked;
		this.id = nextId++;
		memberActivity = new ArrayList <Activity>();
	}
	
	public void addActivity(Activity act)
	{
		if(act != null)
		{
			memberActivity.add(act);
		}
	}
	
	public Activity getActivity(int idActivity)
	{
		for(Activity act : memberActivity)
		{
			if(act.getId() == id)
			{
				return act;
			}
		}
		return null;
	}
	
	public ArrayList <Activity>getAllActivity()
	{
		return memberActivity;
	}
	
	public void removeAllActivities()
	{
		memberActivity.clear();
	}
	
	public void removeActivity(int idActivity)
	{
		Iterator<Activity> iterator = memberActivity.iterator();
		
		while(iterator.hasNext())
		{
			Activity act = iterator.next();
			if(act.getId() == id)
			{
				iterator.remove();
			}
		}
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public static void setStatcId(int id)
	{
		nextId = id;
	}

	public int getId() {
		return id;
	}
	
	public void setTotTimeWored(double totTimeWorked) {
		this.totTimeWorked = totTimeWorked;
	}

	public double getTotTimeWorked() {
		return totTimeWorked;
	}

}

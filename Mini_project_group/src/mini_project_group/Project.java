package mini_project_group;

import riskMatrix.Risk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Project
{
	private String name;
	private double budget;
	private int id = 1;
	private String startDate;
	private String endDate;
	
	private ArrayList<Member> members;
	private ArrayList<Activity> activities;
	private ArrayList<Risk> risks;
	
	public Project(String name, double budget, String startDate, String endDate)
	{
		this.name = name;
		this.budget = budget;
		this.endDate = endDate;
		this.startDate = startDate;
		this.risks = new ArrayList<Risk>();
		this.members = new ArrayList<Member>();
		this.activities = new ArrayList<Activity>();
	}
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("ddMMyyyy");
	
	public ArrayList<Risk> getRisksFromProject() {
		return this.risks;
	}
	
	public void addRiskToProject(Risk risk) {
		this.risks.add(risk);
	}
	
	public long getDayCount() throws ParseException  {
		Date start = simpleDateFormat.parse(startDate);
		Date end = simpleDateFormat.parse(endDate);
		long actualTime = end.getTime() - start.getTime();
		return actualTime;
	}
	
	public void addMemberToProject (Member mem)
	{
		int count = 0;
		
		if(members.isEmpty() == true && mem != null)
		{
			members.add(mem);
		}
		else
		{
			for(Member member : members)
			{
				count = member.getId();
				count++;
				Member.setStatcId(count);
				mem.setId(count);
			}
			members.add(mem);
		}						
	}
	
	public void addActivityToProject(Activity act)
	{
		int count = 0;
		
		if(activities.isEmpty() == true && act != null)
		{
			activities.add(act);
		}
		else
		{
			for(Activity activity : activities)
			{
				count = activity.getId();
				count++;
				Activity.setStatcId(count);
				act.setId(count);
			}
			activities.add(act);
		}	
	}
	
	public void removeActivityFromProject(int id)
	{
		Iterator<Activity> iterator = activities.iterator();
		
		while(iterator.hasNext())
		{
			Activity act = iterator.next();
			if(act.getId() == id)
			{
				iterator.remove();
			}
		}
	}
	
	public void removeAMemberActivities(int id)
	{
		for(Member mem : members)
		{
			if(mem.getId() == id)
			{
				mem.removeAllActivities();
			}			
		}
	}
			
	public boolean removeMemberFromProject(int id)
	{
		Iterator<Member> iterator = members.iterator();
		
		while(iterator.hasNext())
		{
			Member mem = iterator.next();

			if(mem.getId() == id)
			{
				iterator.remove();
				return true;
			}
		}
		return false;
	}
	
	public void removeAllMembers()
	{
		for(Member mem : members)
		{
			mem.removeAllActivities();
		}
		members.clear();		
	}
	

	public Activity getActivity(int idActivity)
	{
		for(Activity act : activities)
		{
			if(act.getId() == idActivity)
			{
				return act;
			}
		}
		return null;
	}
	
	public ArrayList<Activity> getAllActivities() {
		return activities;
	}
		
	public Member getMember(int idMember)
	{
		for(Member mem : members)
		{
			if(mem.getId() == idMember)
			{
				return mem;
			}
		}
		return null;
	}
	
	public int countMembers()
	{
		int count = 0;
		count = members.size();
		return count;
	}
	
	public ArrayList<Member> getAllMembers() 
	{
		return members;
	}

	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

	public double getBudget() 
	{
		return budget;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}	
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}

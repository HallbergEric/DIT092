package mini_project_group;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main
{

	private ArrayList<Project> project		= new ArrayList<Project>();
	
	public void writeToGson(int iD, String filename) {
		GsonReadWrite g = new GsonReadWrite();
		g.gsonWriter(searchForProject(iD), filename);
	}
	
	public void readFromGson(String filename) throws FileNotFoundException {
		GsonReadWrite g = new GsonReadWrite();
		project.add(g.gsonReader(filename));
	}
	
	public void createProject (String name, int budget, String startDate, String endDate)
	{
		if(project.size() == 0)
		{
			project.add(new Project(name, budget, startDate, endDate));
		}	
	}
	
	public void createMember (String name, int salary, double totalTimeMember)
	{
		Project pro = searchForProject(1);
		try 
		{
			pro.addMemberToProject(new Member(name, salary, totalTimeMember));				 		 				
		}
		catch(NullPointerException e)
		{
			
		}
	}	
	
	public void createActivity(String userMessage, int startDate, int endDate, double timeActivity)
	{
		Project pro = searchForProject(1);
		
		if(pro != null)
		{
			pro.addActivityToProject(new Activity(userMessage, startDate, endDate, timeActivity));
		}
	}
	
	public void addActivity(Activity act, int idMember)
	{
		
		//need error handling
		Project pro = searchForProject(1);
		
		Member mem = pro.getMember(idMember);
		mem.addActivity(act);
	}
		
	public Boolean removeProject(int id)
	{
		Project pro = searchForProject(id);
		if(pro != null)
		{
			pro.removeAllMembers();
			project.remove(pro);
			return true;
			
		}
		return false;
	}
	
	public Boolean removeMember(int idMember)
	{
		Project pro = searchForProject(1);
		
		if(pro != null)
		{
			Boolean removed = pro.removeMemberFromProject(idMember);
			return removed;
		}
		return false;
	}
	
	public Boolean removeActivity(int idActivity)
	{
		ArrayList <Member> members;
		Project pro = searchForProject(1);
		
		if(pro != null)
		{
			members = pro.getAllMembers();
			
			for(Member mem : members)
			{
				mem.removeActivity(idActivity);
			}
			pro.removeActivityFromProject(idActivity);
			
			return true;
		}
		return false;
	}
				
	public void setProgressActivity(int idActivity, double progress)
	{
		Activity act = searchForActivity(idActivity);
		
		if(act != null)
		{
			
			act.setProgress(progress);
		}						
	}		
	
	
	public Project searchForProject(int idProject)
	{	
		for(Project pro : project)
		{
			if(pro.getId() == idProject)
			{
				return pro;
			}
		}
		return null;
	}
		
	
	public Member searchForMember(int idMember)
	{
		Project pro = searchForProject(1);
		
		if(pro != null)
		{
			Member mem = pro.getMember(idMember);
			return mem;
		}
	return null;
	}
	
	public Activity searchForActivity(int idActivity)
	{
		Project pro = searchForProject(1);
		
		if(pro != null)
		{
			Activity act = pro.getActivity(idActivity);				
			return act;			
		}
		return null;
	}
	
	public int countMember()
	{
		int count = 0;
		Project pro = searchForProject(1);
		
		if(pro != null)
		{
			count = pro.countMembers();
			return count;
		}
		return 0;
	}
}//class

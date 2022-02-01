
package mini_project_group;


public class Activity {
	
	private String name;
	private int startDate;
	private int endDate;
	private int id;
	private double time;
	private static int nextId = 1;
	private double progress;

	// Constructor 
	public Activity(String name, int startDate, int endDate, double time) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.setProgress(0);
		this.id = nextId++;
	}
	
	// Converts a Activity object to a String for Risk Matrix
	public String[] toArray(int length) {
		String[] array = new String[length];
		array[0] = this.getName();
		array[1] = Double.toString(this.getProgress()) + " %";

		for (int i = 2; i <length ; i++) {
			array[i]= "";
		}
		for (int i = this.getStartDate()+1; i <=this.getEndDate()+1 ; i++) {
			array[i]= "   X";
		}
		
		return array;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getEndDate() {
		return endDate;
	}

	public int getStartDate() {
		return startDate;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		if(progress <= 100 || progress >= 0)
		{
			this.progress = progress;			
		}
	}
		
	public double getTime() {
		return time;
	}
	
	public void setTime (double time) {
		this.time = time;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public static void setStatcId(int id)
	{
		nextId = id;
	}
	
}
	


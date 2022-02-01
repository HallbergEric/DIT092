package mini_project_group;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;



public class GsonReadWrite {
	
	// Write to GSON
	public void gsonWriter(Project p, String filename){
		Gson gson = new Gson();
	    try (FileWriter writer = new FileWriter(filename)) {
	        gson.toJson(p, writer);
	    } catch (IOException e) {
	    	
	    }
	}
	
	// Read from GSON
	public Project gsonReader(String fileName) throws FileNotFoundException{
	    Project newProject;
		Gson gson = new Gson();						
		FileReader reader = new FileReader(fileName);
        newProject = gson.fromJson(reader, Project.class);
    	return newProject;		   
	    }	
}//class

	



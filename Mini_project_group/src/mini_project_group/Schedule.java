package mini_project_group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Schedule {
	

    private static final String HORIZONTAL_SEP = "-";
    private String verticalSep = "|";
    private String joinSep = "+";
    private String[] headers = {"Activity", "Progress", "Week 1 ", "Week 2", "Week 3", "Week 4", "Week 5", "Week 6", "Week 7", "Week 8", "Week 9"};
    private List<String[]> rows = new ArrayList<>();
    private boolean rightAlign = true; 

    //Adding all Activities as String[] to the Headers List
    public void addActivites(Main main) {
    	if (main.searchForProject(1) == null) {
    		System.out.println("There is no Project");
    	}else {
    		Project pro = main.searchForProject(1);
    			for (Activity activity : pro.getAllActivities()) {
    				rows.add(activity.toArray(headers.length));
    		}
    	}
    }
    
    // Print function
    public void print() {
        System.out.println("\t\t Project Schedule");

        int[] maxWidths = headers != null ?
                Arrays.stream(headers).mapToInt(String::length).toArray() : null;

        for (String[] cells : rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }
            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
            }
            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }

        }

        if (headers != null) {
            printLine(maxWidths);
            printRow(headers, maxWidths);
            printLine(maxWidths);
        }
        for (String[] cells : rows) {
            printRow(cells, maxWidths);
        }
        if (headers != null) {
            printLine(maxWidths);
        }
        System.out.println("\n");
    }
    
    // Print line function
    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println();
    }

    // Print row function
    private void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
            if (rightAlign) {
                System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            } else {
                System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            }
        }
        System.out.println();
    }
}//class

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AssignmentHandler 
{
	public static ArrayList<Assignment> globalAssignments;
	public static ArrayList<Assignment> globalHomework;
	
	public static void addAssignment(Assignment assn) { globalAssignments.add(assn); }
	public static void addAssignment(Assignment assn, int index) { globalAssignments.add(index, assn); }
	public static Assignment getAssignment(int index) { return globalAssignments.get(index); }
    public static void loadAssignments()
    {
    	try
    	{
    		FileInputStream fileIn = new FileInputStream("data/assignments/assignments.save");
    		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
    		globalAssignments = (ArrayList<Assignment>)objectIn.readObject();
    		objectIn.close();
    	} 
    	catch(Exception e) 
    	{ 
    		System.err.println("Exception caught on 'loadAssignments': " + e); 
    		e.printStackTrace();
    	}
    }
    public static void saveAssignments()
    {
    	try
    	{
    		FileOutputStream fileOut = new FileOutputStream("data/assignments/assignments.save");
    		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
    		objectOut.writeObject(globalAssignments);
    		objectOut.close();
    	} 
    	catch(Exception e) 
    	{ 
    		System.err.println("Exception caught on 'saveAssignments': " + e);
    		e.printStackTrace();
    	}	
    }
}

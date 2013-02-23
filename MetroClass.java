import java.util.ArrayList;

public class MetroClass implements java.io.Serializable
{
	private String className;
	private float classGrade;
	private ArrayList<Assignment> assignments;
	
	public MetroClass(String name, float grade, ArrayList<Assignment> assn)
	{
		this(name, grade);
		this.assignments = assn;
	}
	public MetroClass(String name, float grade)
	{
		this(name);
		this.classGrade = grade;
	}
	public MetroClass(String name)
	{
		this.className = name;
		this.classGrade = -1;
	}
	public void setGrade()
	{
		if(this.assignments != null)
		{
			float grade = 0;
			float points = 0;
			for(int i = 0; i < assignments.size(); i++)
			{
				grade += assignments.get(i).getScore();
				points += assignments.get(i).getPoints();
			}
			this.classGrade = grade / points;
		}
	}
	public float getGrade() { return this.classGrade; }
	public String getName() { return this.className; }
	public void setName(String name) { this.className = name; }
	public void addAssignment(Assignment assn) { this.assignments.add(assn); }
	public void removeAssignment(Assignment assn) { this.assignments.remove(assn); }
	public ArrayList<Assignment> getAssignments() { return this.assignments; }
	public Assignment getAssignment(String name) 
	{ 
		for(int i = 0; i < this.assignments.size(); i++)
		{
			if(this.assignments.get(i).getName().equals(name)) { return this.assignments.get(i); }
		} 
		return null;
	}
}

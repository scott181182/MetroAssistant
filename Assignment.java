
public class Assignment 
{
	private String assnName;
	private MDate date;
	private float assnPoints;
	private float assnScore;
	private boolean assnDone;
	
	public Assignment(String name, MDate dueDate, float points, float score, boolean finished)
	{
		this(name, dueDate, points, finished);
		this.assnScore = score;
	}
	public Assignment(String name, MDate dueDate, float points, boolean finished)
	{
		this(name, dueDate, finished);
		this.assnPoints = points;
	}
	public Assignment(String name, MDate dueDate, boolean finished)
	{
		this.assnName = name;
		this.date = dueDate;
		this.assnDone = finished;
	}
	public String getName() { return assnName; }
	public MDate getDate() { return date; }
	public float getPoints() { return assnPoints; }
	public float getScore() { return assnScore; }
	public boolean isFinished() { return assnDone; }
	public void setName(String name) { this.assnName = name; }
	public void setDueDate(int par1, int par2, int par3) { this.date = new MDate(par1, par2, par3); }
	public void setPoints(float points) { this.assnPoints = points; }
	public void setScore(float score) { this.assnScore = score; }
	public void setFinished(boolean done) { this.assnDone = done; }
}


public class Assignment implements java.io.Serializable
{
	public static final Integer[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	public static final Integer[] days = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
	public static final Integer[] years = { 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020 };
	private String assnName;
	private String subject;
	private MDate date;
	private float assnPoints;
	private float assnScore;
	private boolean assnDone;
	
	public Assignment(String name, String assnClass, MDate dueDate, float points, float score, boolean finished)
	{
		this(name, assnClass, dueDate, points, finished);
		this.assnScore = score;
	}
	public Assignment(String name, String assnClass, MDate dueDate, float points, boolean finished)
	{
		this(name, assnClass, dueDate, finished);
		this.assnPoints = points;
	}
	public Assignment(String name, String assnClass, MDate dueDate, boolean finished)
	{
		this.assnName = name;
		this.subject = assnClass;
		this.date = dueDate;
		this.assnDone = finished;
	}
	public String getName() { return assnName; }
	public String getSubject() { return subject; }
	public MDate getDate() { return date; }
	public float getPoints() { return assnPoints; }
	public float getScore() { return assnScore; }
	public boolean isFinished() { return assnDone; }
	public void setName(String name) { this.assnName = name; }
	public void setSubject(String assnClass) { this.subject = assnClass; }
	public void setDueDate(int par1, int par2, int par3) { this.date = new MDate(par1, par2, par3); }
	public void setPoints(float points) { this.assnPoints = points; }
	public void setScore(float score) { this.assnScore = score; }
	public void setFinished(boolean done) { this.assnDone = done; }
}

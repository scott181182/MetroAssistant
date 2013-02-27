
public class MDate implements java.io.Serializable 
{
	private int month, day, year;
	public MDate(int month, int day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}
	public void setDate(int month, int day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}
	public void setMonth(int par1) { this.month = par1; }
	public void setDay(int par1) { this.day = par1; }
	public void setYear(int par1) { this.year = par1; }
	public int getMonth() { return this.month; }
	public int getDay() { return this.day; }
	public int getYear() { return this.year; }
}

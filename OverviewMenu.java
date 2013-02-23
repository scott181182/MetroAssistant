import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.util.Hashtable;
import java.awt.font.TextAttribute;

public class OverviewMenu extends JPanel
{
	public static JLabel[] headers = { new JLabel("Classes"), new JLabel("Grades"), new JLabel("Status") };
	private static final JLabel waitLabel = new JLabel("Waiting for classes...");
	public static JLabel[] classLabels;
	public static JLabel[] classGrades;
	public static JLabel[] classStatus;
	
	private static final JPanel gradePanel = new JPanel();

	public static Map<TextAttribute, Object> fontMap = new Hashtable<TextAttribute, Object>();
	
	public static int numClasses;
	
	public OverviewMenu()
	{
		super(null);
		numClasses = SubjectMenu.classes.size();
		
		gradePanel.setBackground(Color.GRAY);
		gradePanel.setLayout(new GridLayout(SubjectMenu.classes.size() + 1, 3));
		if(numClasses == 0) { waiting(); } else { establishGrades(); }
		
		JPanel workPanel = new JPanel();
		
		gradePanel.setBounds(0, 0, 960, 350);
		workPanel.setBounds(0, 350, 960, 373);
		add(gradePanel);
		add(workPanel);
	}
	public void establishGrades()
	{ 
		addHeaders(gradePanel);
		addClasses(gradePanel);
	}
	public void reEstablishGrades()
	{
		gradePanel.removeAll();
		establishGrades();
		gradePanel.revalidate();
		gradePanel.repaint();
	}
	public void deEstablishGrades()
	{
		gradePanel.removeAll();
		waiting();
		gradePanel.revalidate();
		gradePanel.repaint();
	}
	public void updateOverview()
	{
		if(numClasses != SubjectMenu.classes.size())
		{
			numClasses = SubjectMenu.classes.size();
			if(numClasses != 0)
			{
				reEstablishGrades();
				System.out.println("'OverviewMenu' updated with classes");
			}
			else if(numClasses == 0)
			{
				deEstablishGrades();
				System.out.println("'OverviewMenu' updated without classes");
			}
		} else { System.out.println("'OverviewMenu' not updated"); }
		gradePanel.setLayout(new GridLayout(SubjectMenu.classes.size() + 1, 3));
		setVisible(true);
	} 
	private static void addHeaders(JPanel par1Panel)
	{
		fontMap.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font font = new Font("Arial", Font.PLAIN, 24);
		font = font.deriveFont(fontMap);
		for(int i = 0; i < headers.length; i++)
		{
			headers[i].setFont(font);
			headers[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			gradePanel.add(headers[i]);
		}
	}
	private static void addClasses(JPanel par1Panel)
	{
		for(int i = 0; i < SubjectMenu.classes.size(); i++)
		{
			classLabels = new JLabel[SubjectMenu.classes.size()];
			classGrades = new JLabel[SubjectMenu.classes.size()];
			classStatus = new JLabel[SubjectMenu.classes.size()];
			
			classLabels[i] = new JLabel(SubjectMenu.classes.get(i).getName());
			classGrades[i] = new JLabel(printGrade(i));
			classStatus[i] = new JLabel(printStatus(i));
			
			classLabels[i].setFont(new Font("Arial", Font.BOLD, 24));
			classGrades[i].setFont(new Font("Courier New", Font.PLAIN, 24));
			classStatus[i].setFont(new Font("Arial", Font.PLAIN, 24));
			classLabels[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			classGrades[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			classStatus[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			
			par1Panel.add(classLabels[i]);
			par1Panel.add(classGrades[i]);
			par1Panel.add(classStatus[i]);
		}
	}
	private static String printGrade(int par1)
	{
		float grade = SubjectMenu.classes.get(par1).getGrade();
		if(grade == -1) { return "N/A"; }
		return grade + "%";
	}
	private static String printStatus(int par1)
	{
		float grade = SubjectMenu.classes.get(par1).getGrade();
		if(grade >= 90.0f) { return "Mastery"; }
		else if(grade >= 50.0f) { return "WIP"; }
		else return "MIA";
	}
	private void waiting()
	{
		waitLabel.setFont(new Font("Arial", Font.BOLD, 24));
		gradePanel.add(waitLabel); 
	}
}

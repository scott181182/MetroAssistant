import java.awt.*;
import javax.swing.*;

public class OverviewMenu extends JPanel
{
	public OverviewMenu()
	{
		super(null);
		repaint();
	}
	public String printGrade(int par1)
	{
		float grade = SubjectMenu.classes.get(par1).getGrade();
		if(grade == -1) { return "N/A"; }
		return grade + "%";
	}
	public String printStatus(int par1)
	{
		float grade = SubjectMenu.classes.get(par1).getGrade();
		if(grade >= 90.0f) { return "Mastery"; }
		else if(grade >= 50.0f) { return "WIP"; }
		else return "MIA";
	}
	@Override
	public void paint(Graphics g) 
	{ 
		JPanel gradePanel = new JPanel(new GridLayout(6, 3));
		gradePanel.setBackground(Color.GREEN);
		if(SubjectMenu.classes.size() == 0) { gradePanel.add(new JLabel("Waiting for classes...")); }
		else
		{
			gradePanel.add(new JLabel("Classes"));
			gradePanel.add(new JLabel("Grades"));
			gradePanel.add(new JLabel("Status"));
		}
		for(int i = 0; i < SubjectMenu.classes.size(); i++)
		{
			gradePanel.add(new JLabel(SubjectMenu.classes.get(i).getName()));
			gradePanel.add(new JLabel(printGrade(i)));
			gradePanel.add(new JLabel(printStatus(i)));
		}
		
		JPanel workPanel = new JPanel();
		
		gradePanel.setBounds(0, 0, 960, 350);
		workPanel.setBounds(320, 350, 960, 373);
		add(gradePanel);
		add(workPanel);
		System.out.println("'OverviewMenu' updated");
	}
}

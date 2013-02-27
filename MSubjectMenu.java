import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MSubjectMenu extends JPanel implements ListSelectionListener
{
	private MetroClass mClass;
	private int menuIndex;

	private HomeWorkPanel homeworkPanel;
	private AssignmentPanel assignmentPanel;
	private JPanel resourcePanel;
	private NotePanel notePanel;
		
	public static JList<String> assnList;
    private DefaultListModel<String> assnListModel;
    
	public MSubjectMenu(MetroClass subject, int index)
	{
		super(null);
		this.mClass = subject;
		this.menuIndex = index;
		establishHomeWork();
		establishAssignments();
		establishResource();
		establishNotes();
		setBackground(Color.PINK);
	}
	private void establishHomeWork()
	{
		homeworkPanel = new HomeWorkPanel(mClass);
		homeworkPanel.setBounds(0, 0, 320, 350);
		add(homeworkPanel);
	}
	private void establishAssignments()
	{
		assignmentPanel = new AssignmentPanel(mClass);
		assignmentPanel.setBounds(0, 350, 320, 373);
		add(assignmentPanel);
	}
	private void establishResource()
	{
		resourcePanel = new JPanel(null);
		resourcePanel.setBackground(Color.BLUE);
		resourcePanel.setBounds(320, 0, 640, 350);
		add(resourcePanel);
	}
	private void establishNotes()
	{
		
	}
	public static void loadComponents()
	{
		
	}
	public static void saveComponents()
	{
		HomeWorkPanel.saveHomeWork();
		AssignmentPanel.saveAssignments();
	}
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		
	}
}

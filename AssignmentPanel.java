import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AssignmentPanel extends JPanel implements ListSelectionListener
{
	public static JList<String> assList;
    private DefaultListModel<String> assListModel;
    private static final String addString = "ADD";
    private static final String removeString = "REMOVE";
    private static final String editString = "EDIT";
    private static final String viewString = "VIEW";    
    private static JButton removeButton;
    private static JButton viewButton;
    private static MetroClass classSubject;
    public static ArrayList<Assignment> assignments;
    
	public AssignmentPanel(MetroClass subject)
	{
		super(null);
		this.classSubject = subject;
		assignments = new ArrayList<Assignment>();
		
		assListModel = new DefaultListModel<String>();
        assList = new JList<String>(assListModel);
        assList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        assList.setSelectedIndex(0);
        assList.addListSelectionListener(this);
        assList.setVisibleRowCount(JList.VERTICAL);
        assList.setFont(new Font("Arial", Font.BOLD, 24));
        JScrollPane listScrollPane = new JScrollPane(assList);
		
        loadAssignments();
        
        JButton addButton = new JButton(addString);
        AssignmentListener classListener = new AssignmentListener(subject, addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(classListener);
        addButton.setEnabled(true);
        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());
        JButton editButton = new JButton(editString);
        EditListener editListener = new EditListener(editButton);
        editButton.setActionCommand(editString);
        editButton.addActionListener(editListener);
        editButton.setEnabled(true);
        viewButton = new JButton(viewString);
        viewButton.setActionCommand(viewString);
        viewButton.addActionListener(new ViewListener());
        
        JPanel buttonPane = new JPanel(new GridLayout(1, 3));
        //buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(removeButton);
		buttonPane.add(addButton);
		buttonPane.add(editButton);
		buttonPane.add(viewButton);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		listScrollPane.setBounds(0, 0, 320, 305);
		buttonPane.setBounds(0, 300, 320, 50);
		add(listScrollPane);
		add(buttonPane);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		//System.out.println("'HomeWorkPanel' panel created under class '" + subject.getName() + "'");
	}
	class AssignmentListener implements ActionListener
	{
		private boolean alreadyEnabled = true;
        private JButton button;
        private MetroClass subject;
        public AssignmentListener(MetroClass subject, JButton button) 
        {
            this.button = button;
            this.subject = subject;
        }
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String assnName = (String)JOptionPane.showInputDialog(FrameCore.frame, "Creating a past assignment...\n" + "Name of assignment:", "Create an Assignment", JOptionPane.PLAIN_MESSAGE, null, null, "name");
			if(assnName.equals(null)) { return; }
			String assnClass = subject.getName();
			int dueMonth = (int)JOptionPane.showInputDialog(FrameCore.frame, "Month of Due Date", "Create an Assignment", JOptionPane.PLAIN_MESSAGE, null, Assignment.months, 1);
			int dueDay = (int)JOptionPane.showInputDialog(FrameCore.frame, "Date of Due Date", "Create an Assignment", JOptionPane.PLAIN_MESSAGE, null, Assignment.days, 1);
			int dueYear = (int)JOptionPane.showInputDialog(FrameCore.frame, "Year of Due Date", "Create an Assignment", JOptionPane.PLAIN_MESSAGE, null, Assignment.years, 2013);
			float pointsPossible = Float.parseFloat((String)JOptionPane.showInputDialog(FrameCore.frame, "Points possible", "Create an Assignment", JOptionPane.PLAIN_MESSAGE, null, null, 100));
			float pointsGained = Float.parseFloat((String)JOptionPane.showInputDialog(FrameCore.frame, "Points earned", "Create an Assignment", JOptionPane.PLAIN_MESSAGE, null, null, 0));
			MDate dueDate = new MDate(dueMonth, dueDay, dueYear);
			
			Assignment assn = new Assignment(assnName, assnClass, dueDate, pointsPossible, pointsGained, true);
			addAssignment(assn);
            for(int i = 0; i < assignments.size(); i++) { System.out.println(assignments.get(i).getName()); }
		}
	}
	class EditListener implements ActionListener
	{
		private JButton button;
		public EditListener(JButton button)
		{
			this.button = button;
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	}
	class RemoveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
            int index = assList.getSelectedIndex();
            for(int i = 0; i < assignments.size(); i++)
    		{
    			if(assignments.get(i).getName().equals(assList.getSelectedValue())) { assignments.remove(i); }
    		}
            assListModel.remove(index);
            int size = assListModel.getSize();
            if (size == 0) 
            {
                removeButton.setEnabled(false);
            } 
            else 
            {
                if (index == assListModel.getSize()) { index--; }
                assList.setSelectedIndex(index);
                assList.ensureIndexIsVisible(index);
            }
		}
	}
	class ViewListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Assignment assn = assignments.get(assList.getSelectedIndex());
			String assnName = "Name: " + assn.getName() + "\n";
			String assnDate = "Due Date: " + assn.getDate().getMonth() + " / " + assn.getDate().getDay() + " / " + assn.getDate().getYear() + "\n";
			String assnPoints = "Score: " + assn.getScore() + "/" + assn.getPoints() + " - " + (assn.getScore() / assn.getPoints()) * 100 + "% \n";
			JOptionPane.showMessageDialog(FrameCore.frame, assnName + assnDate + assnPoints, "Homework Information", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void addAssignment(Assignment assAssn)
	{	
		assListModel.addElement(assAssn.getName());
		assignments.add(assAssn);
	}
	public void loadAssignments()
	{
    	try
    	{
    		FileInputStream fileIn = new FileInputStream("data/assignments/" + classSubject.getName() + ".save");
    		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
    		assignments = (ArrayList<Assignment>)objectIn.readObject();
    		objectIn.close();
    	}
    	catch(java.io.FileNotFoundException fnfe) { System.err.println("'FileNotFoundException' caught on 'loadAssignments' for '" + classSubject.getName() + "'"); }
    	catch(java.io.EOFException eofe) { System.err.println("'EndOfFileException' caught on 'loadAssignments' for '" + classSubject.getName() + "'"); }
    	catch(Exception e) 
    	{ 
    		System.err.println("Exception caught on 'loadAssignments' in '" + this.classSubject.getName() + "': " + e); 
    		e.printStackTrace();
    	}
    	if(assignments != null)
    	{
	    	for(int i = 0; i < assignments.size(); i++)
	    	{
	    		assListModel.insertElementAt(assignments.get(i).getName(), i);
	    	}
    	}	
	}
	public static void saveAssignments()
	{
    	try
    	{
    		FileOutputStream fileOut = new FileOutputStream("data/assignments/" + classSubject.getName() + ".save");
    		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
    		if(assignments.size() > 0) { objectOut.writeObject(assignments); }
    		objectOut.close();
    	}
    	catch(NullPointerException npe)
    	{
			if(classSubject != null) { System.err.println("'NullPointerException' caught on 'saveAssignments' for '" + classSubject.getName() + "'"); } 
			else { System.err.println("'NullPointerException' caught on 'saveAssignments'"); }
    	}
    	catch(Exception e)
    	{ 
    		System.err.println("Exception caught on 'saveAssignments': " + e);
    		e.printStackTrace();
    	}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		
	}
}

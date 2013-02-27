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

public class HomeWorkPanel extends JPanel implements ListSelectionListener
{
	public static JList<String> hwList;
    private DefaultListModel<String> hwListModel;
    private static final String addString = "ADD";
    private static final String removeString = "REMOVE";
    private static final String editString = "EDIT";
    private static final String viewString = "VIEW";    
    private static JButton removeButton;
    private static JButton viewButton;
    private static MetroClass classSubject;
    public static ArrayList<Assignment> homework;
    
	public HomeWorkPanel(MetroClass subject)
	{
		super(null);
		this.classSubject = subject;
		homework = new ArrayList<Assignment>();
		
		hwListModel = new DefaultListModel<String>();
        hwList = new JList<String>(hwListModel);
        hwList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hwList.setSelectedIndex(0);
        hwList.addListSelectionListener(this);
        hwList.setVisibleRowCount(JList.VERTICAL);
        hwList.setFont(new Font("Arial", Font.BOLD, 24));
        JScrollPane listScrollPane = new JScrollPane(hwList);
		
        loadHomeWork();
        
        JButton addButton = new JButton(addString);
        HomeWorkListener classListener = new HomeWorkListener(subject, addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(classListener);
        addButton.setEnabled(true);
        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());
        JButton editButton = new JButton(editString);
        AssignmentListener editListener = new AssignmentListener(editButton);
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
		
		listScrollPane.setBounds(0, 0, 320, 300);
		buttonPane.setBounds(0, 300, 320, 50);
		add(listScrollPane);
		add(buttonPane);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		//System.out.println("'HomeWorkPanel' panel created under class '" + subject.getName() + "'");
	}
	class HomeWorkListener implements ActionListener
	{
		private boolean alreadyEnabled = true;
        private JButton button;
        private MetroClass subject;
        public HomeWorkListener(MetroClass subject, JButton button) 
        {
            this.button = button;
            this.subject = subject;
        }
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String assnName = (String)JOptionPane.showInputDialog(FrameCore.frame, "Creating an assignment due in the future...\n" + "Name of assignment:", "Create A Homework Assignment", JOptionPane.PLAIN_MESSAGE, null, null, "name");
			if(assnName.equals(null)) { return; }
			String assnClass = subject.getName();
			int dueMonth = (int)JOptionPane.showInputDialog(FrameCore.frame, "Month of Due Date", "Create A Homework Assignment", JOptionPane.PLAIN_MESSAGE, null, Assignment.months, 1);
			int dueDay = (int)JOptionPane.showInputDialog(FrameCore.frame, "Date of Due Date", "Create A Homework Assignment", JOptionPane.PLAIN_MESSAGE, null, Assignment.days, 1);
			int dueYear = (int)JOptionPane.showInputDialog(FrameCore.frame, "Year of Due Date", "Create A Homework Assignment", JOptionPane.PLAIN_MESSAGE, null, Assignment.years, 2013);
			MDate dueDate = new MDate(dueMonth, dueDay, dueYear);
			Assignment hwAssn = new Assignment(assnName, assnClass, dueDate, false);
			addHomework(hwAssn);
            for(int i = 0; i < homework.size(); i++) { System.out.println(homework.get(i).getName()); }
		}
	}
	class AssignmentListener implements ActionListener
	{
		private JButton button;
		public AssignmentListener(JButton button)
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
            int index = hwList.getSelectedIndex();
            for(int i = 0; i < homework.size(); i++)
    		{
    			if(homework.get(i).getName().equals(hwList.getSelectedValue())) { homework.remove(i); }
    		}
            hwListModel.remove(index);
            int size = hwListModel.getSize();
            if (size == 0) 
            {
                removeButton.setEnabled(false);
            } 
            else 
            {
                if (index == hwListModel.getSize()) { index--; }
                hwList.setSelectedIndex(index);
                hwList.ensureIndexIsVisible(index);
            }
		}
	}
	class ViewListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Assignment assn = homework.get(hwList.getSelectedIndex());
			String assnName = "Name: " + assn.getName() + "\n";
			String assnDate = "Due Date: " + assn.getDate().getMonth() + " / " + assn.getDate().getDay() + " / " + assn.getDate().getYear() + "\n";
			JOptionPane.showMessageDialog(FrameCore.frame, assnName + assnDate, "Homework Information", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void addHomework(Assignment hwAssn)
	{	
		hwListModel.addElement(hwAssn.getName());
		homework.add(hwAssn);
	}
	public void loadHomeWork()
	{
    	try
    	{
    		FileInputStream fileIn = new FileInputStream("data/homework/" + classSubject.getName() + ".save");
    		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
    		homework = (ArrayList<Assignment>)objectIn.readObject();
    		objectIn.close();
    	}
    	catch(java.io.FileNotFoundException fnfe) { System.err.println("'FileNotFoundException' caught on 'loadHomeWork' for '" + classSubject.getName() + "'"); }
    	catch(java.io.EOFException eofe) { System.err.println("'EndOfFileException' caught on 'loadHomeWork' for '" + classSubject.getName() + "'"); }
    	catch(Exception e) 
    	{ 
    		System.err.println("Exception caught on 'loadHomeWork': " + e); 
    		e.printStackTrace();
    	}
    	if(homework != null)
    	{
	    	for(int i = 0; i < homework.size(); i++)
	    	{
	    		hwListModel.insertElementAt(homework.get(i).getName(), i);
	    	}
    	}	
	}
	public static void saveHomeWork()
	{
    	try
    	{
    		FileOutputStream fileOut = new FileOutputStream("data/homework/" + classSubject.getName() + ".save");
    		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
    		if(homework.size() > 0) { objectOut.writeObject(homework); }
    		objectOut.close();
    	} 
    	catch(NullPointerException npe) 
    	{
    		if(classSubject != null) { System.err.println("'NullPointerException' caught on 'saveHomeWork' for '" + classSubject.getName() + "'"); } 
    		else { System.err.println("'NullPointerException' caught on 'saveHomeWork'"); }
    	}
    	catch(Exception e) 
    	{ 
    		System.err.println("Exception caught on 'saveHomeWork': " + e);
    		e.printStackTrace();
    	}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		
	}
}

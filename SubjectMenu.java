import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class SubjectMenu extends JPanel implements ListSelectionListener
{
    private static JList<String> list;
    private DefaultListModel<String> listModel;
    private static final String addString = "ADD";
    private static final String removeString = "REMOVE";
    private static JButton removeButton;
    private static JTextField classField;
    public static ArrayList<String> classes = new ArrayList<String>();
    
    public SubjectMenu()
    {
    	super(null);
		
        listModel = new DefaultListModel<String>();
        listModel.addElement("Overview");
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(JList.VERTICAL);
        list.setFont(new Font("Arial", Font.BOLD, 24));
        JScrollPane listScrollPane = new JScrollPane(list);

		loadClasses();

        JButton addButton = new JButton(addString);
        ClassListener classListener = new ClassListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(classListener);
        addButton.setEnabled(false);
        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        classField = new JTextField();
        classField.addActionListener(classListener);
        classField.getDocument().addDocumentListener(classListener);
        String name = listModel.getElementAt(list.getSelectedIndex()).toString();
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(removeButton);
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(classField);
		buttonPane.add(addButton);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		listScrollPane.setBounds(0, 0, 320, 665);
		buttonPane.setBounds(0, 665, 320, 35);
		add(listScrollPane);
		add(buttonPane);
    }
    class RemoveListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            int index = list.getSelectedIndex();
            if(index == 0) { return; }
            classes.remove((String)list.getSelectedValue());
            for(int i = 0; i < classes.size(); i++) { System.out.println(classes.get(i)); }
            //System.out.println("Selected Value: " + list.getSelectedValue());
            listModel.remove(index);
            int size = listModel.getSize();
            if (size == 0) 
            {
                removeButton.setEnabled(false);
            } 
            else 
            {
                if (index == listModel.getSize()) { index--; }
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }
    class ClassListener implements ActionListener, DocumentListener 
    {
        private boolean alreadyEnabled = false;
        private JButton button;
        public ClassListener(JButton button) 
        {
            this.button = button;
        }
        public void actionPerformed(ActionEvent e) 
        {
            String name = classField.getText();
            if (name.equals("") || alreadyInList(name)) 
            {
                Toolkit.getDefaultToolkit().beep();
                classField.requestFocusInWindow();
                classField.selectAll();
                return;
            }
            int index = list.getSelectedIndex(); 
            if(index == -1) { index = 0; } else { index++; }
            listModel.insertElementAt(classField.getText(), index);
            classes.add(classField.getText());
            for(int i = 0; i < classes.size(); i++) { System.out.println(classes.get(i)); }
            classField.requestFocusInWindow();
            classField.setText("");
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
        protected boolean alreadyInList(String name) { return listModel.contains(name); }
        public void insertUpdate(DocumentEvent e) { enableButton(); }
        public void removeUpdate(DocumentEvent e) { handleEmptyTextField(e); } 
        public void changedUpdate(DocumentEvent e) { if(!handleEmptyTextField(e)) { enableButton(); } }
        private void enableButton() { if (!alreadyEnabled) { button.setEnabled(true); } }
        private boolean handleEmptyTextField(DocumentEvent e) 
        {
            if (e.getDocument().getLength() <= 0) 
            {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }
    public void valueChanged(ListSelectionEvent e) 
    {
        if (e.getValueIsAdjusting() == false) 
        {
            if (list.getSelectedIndex() == -1) 
            {
                removeButton.setEnabled(false);
            } 
            else 
            {
            	FrameCore.menuHandler(1, list.getSelectedIndex());
                removeButton.setEnabled(true);
            }
        }
    }
    public static void initButtons()
    {
    	Robot robot;
    	try
    	{
    		robot = new Robot();
        	classField.requestFocusInWindow();
        	robot.keyPress(KeyEvent.VK_T);
        	robot.keyRelease(KeyEvent.VK_T);
        	robot.keyPress(KeyEvent.VK_BACK_SPACE);
        	robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        	removeButton.requestFocusInWindow();
        	classField.requestFocusInWindow();
    	} catch(Exception e) { System.err.println("Exception caught on Robot creation : " + e); }
    }
    private void loadClasses()
    {
    	try
    	{
    		FileInputStream fileIn = new FileInputStream("src/classes.save");
    		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
    		classes = (ArrayList<String>)objectIn.readObject();
    		objectIn.close();
    	} catch(Exception e) { System.err.println("Exception caught on 'loadClasses': " + e.getMessage()); }
    	for(int i = 0; i < classes.size(); i++)
    	{
    		listModel.insertElementAt(classes.get(i), i + 1);
    	}
    }
    public static void saveClasses()
    {
    	try
    	{
    		FileOutputStream fileOut = new FileOutputStream("src/classes.save");
    		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
    		objectOut.writeObject(classes);
    		objectOut.close();
    	} catch(Exception e) { System.err.println("Exception caught on 'saveClasses': " + e); }	
    }
}

import java.awt.Color;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	public static DataFinder input = new DataFinder();
	public static SubjectMenu subjectMenu;
	public static OverviewMenu overviewMenu;
	public static JPanel classMenu;
	public static JPanel[] topMenus = { overviewMenu };
	
	public MainFrame()
	{
    	setTitle("MetroAssistant");
    	setName("MetroAssistant");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//setIgnoreRepaint(true);
    	setResizable(false);
        setSize(1280, 723);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        addSubjectMenu();
        addOverviewMenu();
        addClassMenu();
        setVisible(true);
        createBufferStrategy(2);
	}
    public void addSubjectMenu()
    {
        subjectMenu = new SubjectMenu();
        subjectMenu.setBounds(0, 0, 320, 723);
        subjectMenu.setOpaque(true);
        getContentPane().add(subjectMenu);
    }
    public void addOverviewMenu()
    {
    	overviewMenu = new OverviewMenu();
    	overviewMenu.setBounds(320, 0, 960, 723);
    	overviewMenu.setOpaque(true);
    	getContentPane().add(overviewMenu);
    }
    public void addClassMenu()
    {
    	classMenu = new JPanel(null);
    	classMenu.setBounds(323, 0, 960, 723);
    	classMenu.setOpaque(true);
    	classMenu.setVisible(false);
    	getContentPane().add(classMenu);
    }
    
    /*
    public DisplayMode[] getCompatableDisplayModes() { return graphicsDevice.getDisplayModes(); }
    public DisplayMode getCurrentDisplayMode() { return graphicsDevice.getDisplayMode(); }
    public static DisplayMode findFirstCompatableMode(DisplayMode[] displayModes) 
    {
    	DisplayMode goodModes[] = graphicsDevice.getDisplayModes();
    	for( int i = 0; i < displayModes.length; i++)
    	{
    		for( int j = 0; j < goodModes.length; j++)
    		{
    			if(displayModesMatch(displayModes[i], goodModes[j]))
    			{
    				return displayModes[i];
    			}
    		}
    	}
    	return null;
    }
    public static boolean displayModesMatch(DisplayMode par1DM, DisplayMode par2DM)
    {
    	if(par1DM.getWidth() != par2DM.getWidth() || par1DM.getHeight() != par2DM.getHeight()) { return false; }
    	if(par1DM.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && par2DM.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && par1DM.getBitDepth() != par2DM.getBitDepth()) { return false; }
    	if(par1DM.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && par2DM.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && par1DM.getRefreshRate() != par2DM.getRefreshRate()) { return false; }
    	return true;
    } 
    public BufferedImage createCompatibleImage(int width, int height, int transparency)
    {
    	if(frame != null)
    	{
    		GraphicsConfiguration config = frame.getGraphicsConfiguration();
    		return config.createCompatibleImage(width, height, transparency);
    	}
    	return null;
    }
    public static final DisplayMode POSSIBLE_MODES[] = 
	{		
    	new DisplayMode(1280, 723, 32, 0),
		new DisplayMode(1280, 723, 24, 0),
		new DisplayMode(1280, 723, 16, 0),
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0)
	};
    */
}

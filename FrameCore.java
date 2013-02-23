import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class FrameCore
{
	private static MainFrame frame;
	private static GraphicsDevice graphicsDevice;
	public static DataFinder input = new DataFinder();
	public static void main(String[] args)
	{
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		establishFrame();
		initComponents();
		frame.addWindowListener(new java.awt.event.WindowAdapter() 
		{
        	@Override public void windowClosing(java.awt.event.WindowEvent evt) { saveComponents(); }
        });
	}
	private static void establishFrame()
	{
		frame = new MainFrame();
	}
	public static void initComponents()
	{
		SubjectMenu.initButtons();
	}
	public static void saveComponents()
	{
		SubjectMenu.saveClasses();
	}
    public static void menuHandler(int priority, int index)
    {
    	if(priority == 1)
    	{
    		//System.out.println("Priority #1, index - " + index);
    		removeMenus(index);
    	} 
    	else if(priority == 2)
    	{
    		//System.out.println("Priority #2, index - " + index);
    		
    	}
    	else if(priority == 3)
    	{
    		//System.out.println("Priority #3, index - " + index);

    	}
    	else System.err.println("Null priority on menu change: " + priority);
    }
    private static void removeMenus(int index)
    {
    	if(index == 0) 
    	{
    		MainFrame.overviewMenu.revalidate();
    		MainFrame.overviewMenu.repaint();
    		MainFrame.overviewMenu.setVisible(true);
    	}
    	else 
    	{
    		MainFrame.overviewMenu.setVisible(false); 
    		MainFrame.overviewMenu.removeAll();
    	}
    }
    public Graphics2D getGraphics()
    {
    	if(frame != null)
    	{
    		BufferStrategy buffer = frame.getBufferStrategy();
    		return (Graphics2D)buffer.getDrawGraphics();
    	} else { return null; }
    }
    public void update()
    {
    	if(frame != null)
    	{
    		BufferStrategy buffer = frame.getBufferStrategy();
    		if(!buffer.contentsLost())
    		{
    			buffer.show();
    		}
    	}
    	Toolkit.getDefaultToolkit().sync();
    }
    public Window getWindow() 
    {
        return frame;
    }
    public static int getWidth()
    {
    	if(frame != null)
    	{
    		return frame.getWidth();
    	} else { return 0; }
    }
    public static int getHeight()
    {
    	if(frame != null)
    	{
    		return frame.getHeight();
    	} else { return 0; }
    }
    public void restoreScreen() 
    {
        if (frame != null) 
        {
        	frame.dispose();
        }
        else 
        {
        	System.err.println("Frame not disposed - frame == null");
        }
    }
}

import java.awt.event.*;

public class DataFinder implements KeyListener, MouseListener
{
	public void keyTyped(KeyEvent e) 
	{

	}
	public void keyPressed(KeyEvent e) 
	{
		//Press D to receive window size
		if(e.getKeyCode() == KeyEvent.VK_D) { System.out.println("Resolution - " + FrameCore.getWidth() + ", " + FrameCore.getHeight()); }
		//Press L to get SubjectMenu contents
		if(e.getKeyCode() == KeyEvent.VK_L) 
		{ 
			for(int i = 0; i < SubjectMenu.classes.size(); i++)
			{
				System.out.println(SubjectMenu.classes.get(i)); 
			}
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		
	}
	public void mouseClicked(MouseEvent e) 
	{
		
	}
	public void mousePressed(MouseEvent e) 
	{
		System.out.println("Mouse Button - " + e.getButton());
	}
	public void mouseReleased(MouseEvent e) 
	{
		
	}
	public void mouseEntered(MouseEvent e) 
	{
		
	}
	public void mouseExited(MouseEvent e) 
	{
		
	}
}

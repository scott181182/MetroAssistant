
public class MenuHandler 
{
	public static MSubjectMenu mSubjectMenu;
	public MenuHandler()
	{
		
	}
	public static void removeMenus(int priority)
	{
		switch(priority)
		{
			case 1: 
				MainFrame.overviewMenu.setVisible(false);
				MainFrame.classMenu.setVisible(false);
				MSubjectMenu.saveComponents();
				break;
			case 2:
				break;
			case 3:
				break;
			default: return;
		}
	}
	public static void updateMenu(int priority, int index)
	{
		switch(priority)
		{
			case 1: switch(index)
					{
						case 0:
							MainFrame.overviewMenu.updateOverview();
							break;
						default: 
							updateClassMenu(index - 1); break;
					}
					break;
			case 2: switch(index)
					{
						case 1: System.out.println("Priotity #2, index - 1"); break;
						default: return;
					} break;
			case 3: switch(index)
					{
						case 1: System.out.println("Priotity #3, index - 1"); break;
						default: return;
					} break;	
			default: return;
		}
	}
	private static void updateClassMenu(int index)
	{
		MainFrame.classMenu.removeAll();
		mSubjectMenu = new MSubjectMenu(SubjectMenu.classes.get(index), index);
		mSubjectMenu.setBounds(0, 0, 960, 723);
		MainFrame.classMenu.add(mSubjectMenu);
		MainFrame.classMenu.revalidate();
		MainFrame.classMenu.repaint();
		MainFrame.classMenu.setVisible(true);
		System.out.println("'mSubjectMenu' created for class '" + SubjectMenu.classes.get(index).getName() + "'");
	}
}

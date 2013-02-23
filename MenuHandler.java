
public class MenuHandler 
{
	public MenuHandler()
	{
		
	}
	public static void removeMenus(int priority)
	{
		switch(priority)
		{
			case 1: 
				MainFrame.overviewMenu.setVisible(false);
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
							MainFrame.overviewMenu.updateOverview(); break;
						default: break;
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
}

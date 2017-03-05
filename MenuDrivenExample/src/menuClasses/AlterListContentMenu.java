package menuClasses;

import java.util.ArrayList;

public class AlterListContentMenu extends Menu {
	private static final AlterListContentMenu MM = new AlterListContentMenu(); 
	private AlterListContentMenu() { 
		super(); 
		String title; 
		ArrayList<Option> options = new ArrayList<Option>();  
		title = "Main Menu"; 
		options.add(new Option("Show all Lists", new ShowListsAction())); 
		options.add(new Option("Create New List", new CreateListAction()));
		options.add(new Option("Remove List", new RemoveListAction()));
		options.add(new Option("Operations on Lists", new OperateListAction())); 
		options.add(Option.EXIT); 

		super.InitializeMenu(title, options); 
	}
	
	public static AlterListContentMenu getMainMenu() { 
		return MM; 
	}
}

package menuClasses;

import dataManager.DMComponent;
import ioManagementClasses.IOComponent;

public class DeleteFromListAction implements Action {

	@Override
	public void execute(Object args) {
		IOComponent io = IOComponent.getComponent(); 
		DMComponent dm = (DMComponent) args; 
		String name = io.getInput("Enter the name of the list: ");
		int index = Integer.parseInt(io.getInput("Enter the index to be removed: ")); 
		//dm.addIntToList(name, index); 	
		dm.removeElementFromList(name, index);

	}

}

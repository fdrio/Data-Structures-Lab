package menuClasses;
import dataManager.DMComponent;
import ioManagementClasses.IOComponent;
public class AlterListContentMenuAction implements Action {

	@Override
	public void execute(Object args) {
		DMComponent dm = (DMComponent) args; 
		dm.getMenuStack().push(AlterListContentMenu.getMainMenu());

	}

}

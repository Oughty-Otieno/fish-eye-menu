package demo.menu.fisheye_menu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Fisheye;
import org.zkoss.zkex.zul.Fisheyebar;

public class FisheyeMenuController extends SelectorComposer<Component> {
	@Wire
	Fisheyebar fisheyebar;

	@Listen("onClick = fisheye")
	public void menuItemClicked(Event event) {
		Clients.showNotification(
				"Menuitem '" + ((Fisheye) event.getTarget()).getLabel() + "' clicked.", 
				"info", null, null, 1000);
	}
}

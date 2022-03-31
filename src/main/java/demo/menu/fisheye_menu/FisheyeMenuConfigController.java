package demo.menu.fisheye_menu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkex.zul.Fisheyebar;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;

public class FisheyeMenuConfigController extends SelectorComposer<Component> {
	
	private static final String HORIZONTAL = "horizontal";
	private static final String VERTICAL = "vertical";

	@Wire
	private Radiogroup orientation;
	
	@Wire
	private Radiogroup attachEdge;
	
	@Wire
	Fisheyebar fisheyebar;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		orientation.appendChild(new Radio(HORIZONTAL));
		orientation.appendChild(new Radio(VERTICAL));
		orientation.setSelectedItem((Radio)orientation.getChildren().get(0));
		updateView();
	}
	
	
	@Listen("onCheck = #orientation") 
	public void orientationChanged() {
		updateView();
	}

	@Listen("onCheck = #attachEdge") 
	public void attachEdgeChanged() {
		updateAttachEdge();
	}

	private void updateView() {
		fisheyebar.setOrient((String)orientation.getSelectedItem().getLabel());
		int selectedIndex = attachEdge.getSelectedIndex();
		attachEdge.getChildren().clear();
		if(isHorizontal()) {
			attachEdge.appendChild(new Radio("top"));
			attachEdge.appendChild(new Radio("center"));
			attachEdge.appendChild(new Radio("bottom"));
		} else {
			attachEdge.appendChild(new Radio("left"));
			attachEdge.appendChild(new Radio("center"));
			attachEdge.appendChild(new Radio("right"));
		}
		attachEdge.setSelectedItem((Radio)attachEdge.getChildren().get(0));
		updateAttachEdge();
	}

	private void updateAttachEdge() {
		String edge = (String)attachEdge.getSelectedItem().getLabel();
		if(isHorizontal()) {
			if(edge.equals("top")) {
				fisheyebar.setStyle("");
			} else if(edge.equals("center")) {
				fisheyebar.setStyle("top: 200px");
			} if(edge.equals("bottom")) {
				fisheyebar.setStyle("top: 370px");
			}
		} else {
			if(edge.equals("left")) {
				fisheyebar.setStyle("");
			} else if(edge.equals("center")) {
				fisheyebar.setStyle("left: 200px");
			} if(edge.equals("right")) {
				fisheyebar.setStyle("left: 450px");
			}
		}
		fisheyebar.setAttachEdge(edge);
	}
	
	private boolean isHorizontal() {
		return HORIZONTAL.equals(orientation.getSelectedItem().getLabel());
	}
}

package userinterface.item;

import java.awt.Component;

import javax.swing.JLabel;

import userinterface.page.Page;

public class GraphicalItem extends NoninteractiveItem {
	
	private final JLabel GRAPHIC = new JLabel();
	
	public GraphicalItem(Page page, int x, int y) {
		super(page, x, y);
	}
	
	@Override
	public Component getComponent() {
		return GRAPHIC;
	}
}

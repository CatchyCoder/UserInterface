package userinterface.item;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import userinterface.page.Page;

public class GraphicalItem extends NoninteractiveItem {
	
	private final JLabel GRAPHIC = new JLabel();
	
	public GraphicalItem(Page page, int x, int y, String path) {
		super(page, x, y);
		
		GRAPHIC.setIcon(new ImageIcon(getClass().getResource(page.getResourcePath() + path)));
		GRAPHIC.setBounds(x, y, GRAPHIC.getIcon().getIconWidth(), GRAPHIC.getIcon().getIconHeight());
		
		page.addItem(this);
	}
	
	@Override
	public Component getComponent() {
		return GRAPHIC;
	}
}

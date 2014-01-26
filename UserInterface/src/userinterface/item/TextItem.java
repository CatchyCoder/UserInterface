package userinterface.item;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import userinterface.page.Page;

public class TextItem extends NoninteractiveItem {
	
	private final JLabel TEXT = new JLabel();
	
	public TextItem(Page page, int xPos, int yPos, String text, Font font) {
		super(page, xPos, yPos);
		
		TEXT.setFont(font);
		TEXT.setText(text);
		TEXT.setForeground(Color.BLACK);
		this.setSizeAndLoc(TEXT);
		
		page.addItem(this);
	}

	@Override
	public JLabel getComponent() {
		return TEXT;
	}
}

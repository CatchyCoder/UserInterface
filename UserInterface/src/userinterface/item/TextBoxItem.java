package userinterface.item;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import userinterface.page.Page;

public class TextBoxItem extends InteractiveItem {
	
	private final JTextField TEXT_BOX = new JTextField();
	
	public TextBoxItem(Page page, int xPos, int yPos, int charLength, Font font) {
		super(page, xPos, yPos);
		
		// A dark green color CHANGE THIS --- ITS PROJECT SPECIFIC
		Color textColor = new Color(0, 170, 0);
		
		// Visual settings
		TEXT_BOX.setFont(font);
		TEXT_BOX.setBackground(Color.WHITE);
		TEXT_BOX.setForeground(textColor);
		TEXT_BOX.setSelectionColor(textColor);
		TEXT_BOX.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		TEXT_BOX.setCaretColor(textColor);
		this.setSizeAndLoc(TEXT_BOX, charLength);
				
		page.addItem(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {}

	@Override
	public void keyPressed(KeyEvent event, int key) {}
	
	@Override
	public JTextField getComponent() {
		return TEXT_BOX;
	}
}

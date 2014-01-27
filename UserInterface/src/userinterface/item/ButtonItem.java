package userinterface.item;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import userinterface.page.Page;

public class ButtonItem extends InteractiveItem {
	
	private final JLabel BUTTON = new JLabel();
	
	private ImageIcon enterState, exitState;
	
	private boolean imageUse;
	
	private Color color, hoverColor;
	
	public ButtonItem(Page page, int xPos, int yPos, String nonHoverName, String hoverName) {
		super(page, xPos, yPos);
		
		imageUse = true;
		
		try {
			// Loading images
			exitState = new ImageIcon(getClass().getResource(page.getResourcePath() + nonHoverName));
			enterState = new ImageIcon(getClass().getResource(page.getResourcePath() + hoverName));
			
			// Visual settings
			BUTTON.setIcon(exitState);
			BUTTON.setBounds(xPos, yPos, exitState.getIconWidth(), exitState.getIconHeight());
			BUTTON.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			page.addItem(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ButtonItem(Page page, int xPos, int yPos, String text, Font font, Color color, Color hoverColor) {
		super(page, xPos, yPos);
		
		imageUse = false;
		this.color = color;
		this.hoverColor = hoverColor;
		
		try {
			// Visual settings
			BUTTON.setFont(font);
			BUTTON.setText(text);
			BUTTON.setForeground(color);
			BUTTON.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.setSizeAndLoc(BUTTON);
			
			page.addItem(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent event) {
		if(imageUse) BUTTON.setIcon(enterState);
		else BUTTON.setForeground(hoverColor);
	}
	
	@Override
	public void mouseExited(MouseEvent event) {
		if(imageUse) BUTTON.setIcon(exitState);
		else BUTTON.setForeground(color);
	}
	
	@Override
	public JLabel getComponent() {
		return BUTTON;
	}
}

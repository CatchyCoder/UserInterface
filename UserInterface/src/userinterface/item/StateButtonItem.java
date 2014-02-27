package userinterface.item;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import userinterface.page.Page;

public class StateButtonItem extends InteractiveItem {
	
	private JLabel BUTTON = new JLabel();
	
	private ImageIcon[][] images;
	
	private int currentState = 0;
	
	public StateButtonItem(Page page, int x, int y, String[] buttonPaths, String[] hoverButtonPaths) {
		super(page, x, y);
		
		if(buttonPaths.length != hoverButtonPaths.length) throw new IllegalArgumentException(
				"Number of image paths does not equal number of hover image paths. These array lengths must be equal");
		
		images = new ImageIcon[buttonPaths.length][2];
		
		try {
			// Loading images
			for(int a = 0; a < buttonPaths.length; a++) {
				images[a][0] = new ImageIcon(getClass().getResource(page.getResourcePath() + buttonPaths[a]));
				images[a][1] = new ImageIcon(getClass().getResource(page.getResourcePath() + hoverButtonPaths[a]));
			}
			
			// Visual settings
			BUTTON.setIcon(images[currentState][0]);
			BUTTON.setBounds(xPos, yPos, images[currentState][0].getIconWidth(), images[currentState][0].getIconHeight());
			BUTTON.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		page.addItem(this);
	}
	
	private void showImage() {
		BUTTON.setIcon(images[currentState][0]);
		BUTTON.setBounds(xPos, yPos, images[currentState][0].getIconWidth(), images[currentState][0].getIconHeight());
	}
	
	private void showHoverImage() {
		BUTTON.setIcon(images[currentState][1]);
		BUTTON.setBounds(xPos, yPos, images[currentState][1].getIconWidth(), images[currentState][1].getIconHeight());
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		currentState++;
		if(currentState >= images.length) currentState = 0;
		
		// Chances are that when they click on the button they will still be hovering over
		// the image, so we will show the hover image first. If their mouse is not
		// hovering over the image, then mouseExited(MouseEvent) will be called automatically,
		// which will then call showImage().
		showHoverImage();
	}
	
	@Override
	public void mouseEntered(MouseEvent event) {
		showHoverImage();
	}
	
	@Override
	public void mouseExited(MouseEvent event) {
		showImage();
	}

	@Override
	public Component getComponent() {
		return BUTTON;
	}
	
	public int getState() {
		return currentState;
	}
}

package userinterface.item;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

import userinterface.page.Page;

public abstract class Item {
	
	protected final Page PAGE;
	protected int xPos, yPos;
	
	public Item(Page page, int xPos, int yPos) {
		this.PAGE = page;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setSizeAndLoc(JLabel textLabel) {
		// Calculating how wide the JLabel should be (in pixels) using FontMetrics, then doing the same for height
		int width = textLabel.getFontMetrics(textLabel.getFont()).stringWidth(textLabel.getText());
		int height = textLabel.getFontMetrics(textLabel.getFont()).getHeight();
		
		textLabel.setBounds(xPos, yPos, width, height);
	}
	
	public void setSizeAndLoc(JTextField textField, int charLength) {
		Font font = textField.getFont();
		// Finding out the width of the widest character in this specific font using FontMetrics
		// This is used to determine the width of the JTextField
		int widestCharWidth = textField.getFontMetrics(font).getMaxAdvance();
		
		int width = widestCharWidth * charLength;
		
		// Extra height padding is used so that text that hangs low is not cut off in the JTextField
		int extraHeightPadding = (int) (textField.getFont().getSize() * 0.15);
		int height = textField.getFontMetrics(textField.getFont()).getHeight() + extraHeightPadding;
		
		textField.setBounds(xPos, yPos, width, height);
	}
	
	public void setText(String text) {
		if(getComponent() instanceof JLabel) {
			// Setting the text
			((JLabel) getComponent()).setText(text);
			
			// Resizing the component
			setSizeAndLoc((JLabel) getComponent());
		}
	}
	
	public Page getPage() {
		return PAGE;
	}
	
	abstract public Component getComponent();
}

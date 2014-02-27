package userinterface.item;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import userinterface.InteractiveComponent;
import userinterface.page.Page;

public abstract class InteractiveItem extends Item implements InteractiveComponent {

	/*
	 * This class is simply used to classify an object as
	 * "interactive" so mouse/mouse motion/key events
	 * are used with these types of items. Therefore, you
	 * can interact with them with your mouse and keyboard.
	 */
	
	public InteractiveItem(Page page, int x, int y) {
		super(page, x, y);
	}
	
	
	/*
	 * Defining all input methods below, that way any class that inherits
	 * InteractiveItem can choose which methods it would like to implement,
	 * instead of having to implement them all. It acts as an "adapter" class,
	 * similar to MouseAdapter.
	 */
	
	@Override
	public void mousePressed(MouseEvent event) {}
	
	@Override
	public void mouseReleased(MouseEvent event) {}
	
	@Override
	public void mouseEntered(MouseEvent event) {}
	
	@Override
	public void mouseExited(MouseEvent event) {}
	
	@Override
	public void mouseDragged(MouseEvent event) {}
	
	@Override
	public void mouseMoved(MouseEvent event) {}
	
	@Override
	public void actionPerformed(ActionEvent event) {}
}

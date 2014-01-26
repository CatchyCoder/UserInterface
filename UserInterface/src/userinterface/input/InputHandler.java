package userinterface.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import userinterface.DraggableComponent;
import userinterface.item.InteractiveItem;
import userinterface.item.Item;
import userinterface.page.Page;
import userinterface.window.Window;

/**
 * All mouse/key action methods work the same. Each one checks three things:
 * 1.) If the Window (JFrame) was acted on, otherwise...
 * 2.) If the Pages (JPanel) in the Window were acted on, otherwise...
 * 3.) If the Items (JLabels) in the Pages were acted on, otherwise...
 * 4.) An error message is displayed on the object that could not be found.
 */
public final class InputHandler extends KeyAdapter implements MouseListener, MouseMotionListener, ActionListener {
	
	private Window window;
	
	public InputHandler(Window window) {
		this.window = window;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) {
			window.mousePressed(event);
			window.startDrag(event);
		}
		else if(source instanceof Page) {
			Page page = getSourcePage(source);
			page.mousePressed(event);
			page.startDrag(event);
		}
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) {
				InteractiveItem actItem = (InteractiveItem) item;
				actItem.mousePressed(event);
				actItem.getPage().handleMousePress(actItem);
			}
			else {
				item.getPage().mousePressed(event);
				item.getPage().startDragItem(event, item.getComponent().getLocation().x, item.getComponent().getLocation().y);
			}
		}
		
		event.consume();
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) {
			window.mouseReleased(event);
			window.stopDrag(event);
		}
		else if(source instanceof Page) {
			Page page = getSourcePage(source);
			page.mouseReleased(event);
			page.stopDrag(event);
		}
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseReleased(event);
			else {
				item.getPage().mouseReleased(event);
				item.getPage().stopDrag(event);
			}
		}
		
		event.consume();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {}
	
	@Override
	public void mouseEntered(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseEntered(event);
		else if(source instanceof Page) getSourcePage(source).mouseEntered(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseEntered(event);
			else item.getPage().mouseEntered(event);
		}
		
		event.consume();
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) {
			window.mouseDragged(event);
			window.drag(event);
		}
		else if(source instanceof Page) {
			Page page = getSourcePage(source);
			page.mouseDragged(event);
			page.drag(event);
		}
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseDragged(event);
			else {
				item.getPage().mouseDragged(event);
				item.getPage().drag(event);
			}
		}
		
		event.consume();
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseMoved(event);
		else if(source instanceof Page) getSourcePage(source).mouseMoved(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseMoved(event);
			else item.getPage().mouseMoved(event);
		}
		
		event.consume();
	}
	
	@Override
	public void mouseExited(MouseEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.mouseExited(event);
		else if(source instanceof Page) getSourcePage(source).mouseExited(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) ((InteractiveItem) item).mouseExited(event);
			else item.getPage().mouseExited(event);
		}
		
		event.consume();
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		Object source = event.getSource();
		int key = event.getKeyCode();
		
		if(source == window) window.keyPressed(event, key);
		else if(source instanceof Page) {
			getSourcePage(source).keyPressed(event, key);
		}
		else if(source instanceof InteractiveItem) {
			InteractiveItem item = (InteractiveItem) getSourceItem(source);
			item.keyPressed(event, key);
			item.getPage().handleKeyPress(item, key);
		}
		
		event.consume();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == window) window.actionPerformed(event);
		else if(source instanceof Page) getSourcePage(source).actionPerformed(event);
		else {
			Item item = getSourceItem(source);
			if(item instanceof InteractiveItem) {
				InteractiveItem actItem = (InteractiveItem) item;
				actItem.actionPerformed(event);
				item.getPage().handleActionEvent(actItem);
			}
			else item.getPage().actionPerformed(event);
		}
	}
	
	/**
	 * Finds out what Page was acted on in the current visible Window, and returns that Page.
	 * returns null if the Page was not acted on.
	 * @param source
	 * @return page
	 */
	private Page getSourcePage(Object source) {
		// Finding the object that was acted on in the Page
		for(Page page : getVisiblePages()) {
			if(source == page) return page;
		}
		// Page was not acted on
		return null;
	}
	
	/**
	 * Finds out what Item was acted on in the given Page, and returns that Item.
	 * returns null if the Item was not acted on.
	 * @param source
	 * @return item
	 */
	private Item getSourceItem(Object source) {
		// Looking through each page
		for(Page page : getVisiblePages()) {
			// Finding the object that was acted on in the Page
			for(Item item : page.getItems()) {
				if(source == item.getComponent()) return item;
			}
		}
		System.err.println("Item that was acted on was not found! Object = " + source);
		return null;
	}
	
	private ArrayList<Page> getVisiblePages() {
		return window.getVisiblePages();
	}
}

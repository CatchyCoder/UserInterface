package userinterface.window;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import userinterface.DraggableComponent;
import userinterface.InteractiveComponent;
import userinterface.input.InputHandler;
import userinterface.item.Item;
import userinterface.page.Page;

public class Window extends JFrame implements InteractiveComponent, DraggableComponent, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private InputHandler inputHandler = new InputHandler(this);
	private ArrayList<Page> pages = new ArrayList<Page>();
	
	private int lastX, lastY; // For dragging the program around
	
	public Window(int width, int height) {
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true); 
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.setVisible(false);
		
		super.addMouseListener(inputHandler);
		super.addMouseMotionListener(inputHandler);
		super.addKeyListener(inputHandler);
	}
	
	@Override
	public void addMouseListener(MouseListener listener) {
		// Adding the listener
		super.addMouseListener(listener);
				
		// Adding the listener to all the pages and their items
		for(Page page : pages) {
			page.addMouseListener(listener);
			for(Item item : page.getItems()) item.getComponent().addMouseListener(listener);
		}
		
	}
	
	@Override
	public void addMouseMotionListener(MouseMotionListener listener) {
		// Adding the listener to this window
		super.addMouseMotionListener(listener);
				
		// Adding the listener to all the pages and their items
		for(Page page : pages) {
			page.addMouseMotionListener(listener);
			for(Item item : page.getItems()) item.getComponent().addMouseMotionListener(listener);
		}
	}
	
	@Override
	public void addKeyListener(KeyListener listener) {
		// Adding the listener
		super.addKeyListener(listener);
		
		// Adding the listener to all the pages and their items
		for(Page page : pages) {
			page.addKeyListener(listener);
			for(Item item : page.getItems()) item.getComponent().addKeyListener(listener);
		}
	}
	
	public void addCurrentEventListeners(Component component) {
		if(!(component instanceof JTextField)) {
			// Adding key listeners
			for(KeyListener listener : this.getKeyListeners()) component.addKeyListener(listener);
		}
		else {
			// JTextField's don't use KeyListeners for the <enter> key, they use 
			// ActionListener, so we'll use that instead.
			((JTextField) component).addActionListener(inputHandler);
		}
		
		// Adding mouse listeners
		for(MouseListener listener : this.getMouseListeners()) component.addMouseListener(listener);
		
		// Adding mouse motion listeners
		for(MouseMotionListener listener : this.getMouseMotionListeners()) component.addMouseMotionListener(listener);
	}
	
	public void removeCurrentEventListeners(Component component) {
		// Removing mouse Listeners
		while(component.getMouseListeners().length > 0) component.removeMouseListener(component.getMouseListeners()[0]);
		
		// Removing mouse motion listeners
		while(component.getMouseMotionListeners().length > 0) component.removeMouseMotionListener(component.getMouseMotionListeners()[0]);
		
		// Removing key listeners
		while(component.getKeyListeners().length > 0) component.removeKeyListener(component.getKeyListeners()[0]);
	}
	
	public void addPage(Page page) {
		addCurrentEventListeners(page);
		this.add(page);
		pages.add(page);
		page.setVisible(true);
		
		refreshScreen();
		getFocus();
	}
	
	public void removePage(Page page) {
		if(page == null) return;
		
		removeCurrentEventListeners(page);
		this.remove(page);
		pages.remove(page);
		page = null;
		
		refreshScreen();
	}
	
	public void removeAllPages() {
		for(int x = 0; x < pages.size(); x++) removePage(pages.get(x));
	}
	
	public void setPage(Page page) {
		ArrayList<Page> pageHolder = new ArrayList<Page>();
		pageHolder.add(page);
		setPages(pageHolder);
	}
	
	public void setPages(ArrayList<Page> pages) {
		// Removing the old pages
		removeAllPages();
		
		// Adding the new pages
		for(Page page : pages) addPage(page);
		
		refreshScreen();
	}
	
	public void refreshScreen() {
		if(pages.size() <= 0) return;
		
		// Refreshing screen by repainting each page
		this.repaint();
		for(Page page : pages) page.repaint();
	}
	
	@Override
	public void setVisible(boolean value) {
		super.setVisible(value);
		if(value) {
			this.requestFocus();
			this.refreshScreen();
		}
	}
	
	public void getFocus() {
		// Getting focus
		this.requestFocus();
	}
	
	public ArrayList<Page> getPages() {
		return pages;
	}
	
	/* =======================
	 * = Input Methods Below =
	 * =======================
	 */
	
	@Override
	public void mousePressed(MouseEvent event) {}
	
	@Override
	public void startDrag(MouseEvent event) {
		// Only dragging if it is a left mouse click
		if(!SwingUtilities.isLeftMouseButton(event)) return;
				
		// Below is for dragging purposes
		lastX = event.getX();
		lastY = event.getY();
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseReleased(MouseEvent event) {}
	
	@Override
	public void stopDrag(MouseEvent event) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mouseDragged(MouseEvent event) {}
	
	@Override
	public void drag(MouseEvent event) {
		// Only dragging if it is a left mouse click
		if(!SwingUtilities.isLeftMouseButton(event)) return;
		
		// Below adds the ability to drag program around on screen
		int x = event.getXOnScreen();
		int y = event.getYOnScreen();
		this.setLocation(x - lastX, y - lastY);
		for(Page page : pages) page.resetItemStates(event);
	}

	@Override
	public void mouseMoved(MouseEvent event) {}

	@Override
	public void keyPressed(KeyEvent event) {}
	
	@Override
	public void keyReleased(KeyEvent event) {}

	@Override
	public void keyTyped(KeyEvent event) {}	

	@Override
	public void actionPerformed(ActionEvent event) {}	
}

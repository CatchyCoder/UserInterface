package userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InteractiveComponent {
	
	// For mouse events
	abstract public void mousePressed(MouseEvent event);
	abstract public void mouseReleased(MouseEvent event);
	abstract public void mouseEntered(MouseEvent event);
	abstract public void mouseExited(MouseEvent event);
	
	// for mouse motion events
	abstract public void mouseDragged(MouseEvent event);
	abstract public void mouseMoved(MouseEvent event);
	
	// For action events (These are used for JTextFields for when the <enter> key is pressed)
	abstract public void actionPerformed(ActionEvent event);
}

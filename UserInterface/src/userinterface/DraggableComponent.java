package userinterface;

import java.awt.event.MouseEvent;

public interface DraggableComponent {
	
	abstract public void startDrag(MouseEvent event);
	abstract public void drag(MouseEvent event);
	abstract public void stopDrag(MouseEvent event);
}

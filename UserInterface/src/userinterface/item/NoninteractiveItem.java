package userinterface.item;

import userinterface.page.Page;

public abstract class NoninteractiveItem extends Item {

	/*
	 * This class is simply used to classify an object as
	 * "non-interactive" so mouse/mouse motion/key events
	 * are not used with this type of Item.
	 */
	
	public NoninteractiveItem(Page page, int x, int y) {
		super(page, x, y);
	}
}

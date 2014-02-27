package userinterface.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import userinterface.item.ButtonItem;
import userinterface.item.Item;
import userinterface.item.TextItem;
import userinterface.page.Page;

public class TextList {
	
	private Page page;
	private int xPos, yPos, spacing;
	private Font font;
	private String nonHoverPath, hoverPath;
	private Color color;
	
	private final ArrayList<Item> LIST_ITEMS = new ArrayList<Item>();
	private final ArrayList<Item> LIST_BUTTONS = new ArrayList<Item>();
	
	public TextList(Page page, int xPos, int yPos, Font font, Color color, int spacing, String nonHoverPath, String hoverPath) {
		this.page = page;
		this.xPos = xPos;
		this.yPos = yPos;
		this.font = font;
		this.color = color;
		this.spacing = spacing;
		this.nonHoverPath = nonHoverPath;
		this.hoverPath = hoverPath;
	}
	
	public TextList(Page page, int xPos, int yPos, Font font, Color color, int spacing) {
		this(page, xPos, yPos, font, color, spacing, "", "");
	}
	
	public void addListItem(String text) {
		addListItem(text, color);
	}
	
	public void addListItem(String text, Color color) {
		int listSize = LIST_ITEMS.size();
		text = (listSize + 1) + ".) " + text;
		int yValue;
		
		// If first item
		if(listSize == 0) yValue = yPos;
		else yValue = yPos + (LIST_ITEMS.get(0).getComponent().getHeight() * listSize + spacing * listSize);
		
		// Creating the list item
		LIST_ITEMS.add(new TextItem(page, xPos, yValue, text, font, color));
		// Creating the list button, but only if they provided image names (meaning the list is editable)
		if(!(nonHoverPath.equals("") || hoverPath.equals(""))) LIST_BUTTONS.add(new ButtonItem(page, xPos - 40, yValue, "exit.jpg", "exithover.jpg"));
		
		// Need to refresh the page in order for the items to appear
		page.repaint();
	}
	
	public void removeListItem(int index) {
		// Removing the Items from the page
		page.removeItem(LIST_ITEMS.get(index));
		page.removeItem(LIST_BUTTONS.get(index));
		
		// Removing the Items from this list
		LIST_ITEMS.remove(index);
		LIST_BUTTONS.remove(index);
		
		for(int x = index; x < LIST_ITEMS.size(); x++) {
			// Shifting all the items below the deleted item back up in the list
			JLabel listItem = (JLabel) LIST_ITEMS.get(x).getComponent();
			Component listButton = LIST_BUTTONS.get(x).getComponent();
			
			int yValue = listItem.getLocation().y - listItem.getHeight() - spacing;
			listItem.setLocation(xPos, yValue);
			listButton.setLocation(listButton.getLocation().x, yValue);
			
			// Re-numbering the items in the list
			String text = listItem.getText();
			while(!text.startsWith(".")) text = text.substring(1, text.length());
			listItem.setText((x + 1) + text);
		}
		
		page.repaint();
	}
	
	public ArrayList<Item> getListItems(){
		return LIST_ITEMS;
	}
	
	public ArrayList<Item> getListButtons(){
		return LIST_BUTTONS;
	}
}

package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import userinterface.item.ButtonItem;
import userinterface.item.GraphicalItem;
import userinterface.item.InteractiveItem;
import userinterface.item.StateButtonItem;
import userinterface.item.TextItem;
import userinterface.page.Page;
import userinterface.window.Window;

public class MyPage extends Page {
	
	private ButtonItem button = new ButtonItem(this, 0, 0, "ai.png", "TrinityShip.png");
	private TextItem text = new TextItem(this, 0, 100, "Text", new Font("Arial", Font.BOLD, 32), Color.ORANGE);
	
	private String[] paths = {
			"SpaceShipSmall.png",
			"TrinityShip.png",
			"SpaceShipSmallNew.png"
	};
	
	private String[] hoverPaths = {
			"SpaceShipThrustSmall.png",
			"TrinityShipThrust.png",
			"SpaceShipThrustSmallNew.png"
	};
	
	private GraphicalItem g = new GraphicalItem(this, 100, 50, "TrinityShip.png");
	
	private StateButtonItem test = new StateButtonItem(this, 100, 20, paths, hoverPaths);

	public MyPage(Window window, int x, int y, int width, int height) {
		super(window, x, y, width, height,  "/spaceships/");
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void handleKeyPress(KeyEvent event) {
		System.out.println("Key Press in MYPAGE!");
	}
	
	@Override
	public void handleMousePress(InteractiveItem item) {
		System.out.println("Mouse Press in MYPAGE!");
	}
}

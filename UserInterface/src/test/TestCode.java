package test;

import userinterface.window.Window;

public class TestCode {

	public static Window window = new Window(200, 400);
	
	public static void main(String[] args) {
		window.setPage(new MyPage(window, 0, 0, 200, 200));
		window.setVisible(true);
	}
}

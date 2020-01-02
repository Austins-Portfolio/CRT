package crt.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener{

	public boolean KEYS[] = new boolean[255];
	
	@Override
	public void keyPressed(KeyEvent e) {
		KEYS[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KEYS[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean getKey(int keyNum) {
		return KEYS[keyNum];
	}
	
}

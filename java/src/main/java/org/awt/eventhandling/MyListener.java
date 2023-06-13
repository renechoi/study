package org.awt.eventhandling;

import java.awt.event.*;

//이벤트 리스너 구현
class MyListener implements WindowListener {
	public void windowClosing(WindowEvent ev) {
		System.exit(0);
	}

	public void windowActivated(WindowEvent ev) {
	}

	public void windowClosed(WindowEvent ev) {
	}

	public void windowDeactivated(WindowEvent ev) {
	}

	public void windowDeiconified(WindowEvent ev) {
	}

	public void windowIconified(WindowEvent ev) {
	}

	public void windowOpened(WindowEvent ev) {
	}
}
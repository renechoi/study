package org.awt.eventhandling.adapter;

import java.awt.event.*;

//어댑터를 상속
class MyListener extends WindowAdapter {
	public void windowClosing(WindowEvent ev) {
		System.exit(0);
	}
}
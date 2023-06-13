package org.awt.eventhandling2;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class MyListener implements ItemListener {
	public void itemStateChanged(ItemEvent ev) {
		String item = (String)ev.getItem();
		System.out.print(item + "\t");
		if (ev.getStateChange() == ItemEvent.SELECTED)
			System.out.println("SELECTED");
		else
			System.out.println("DESELECTED");
	}
}
package org.refactoring.handlenull.after;

public class Person {

	private Label name;
	private Label mail;

	public Person(Label name, Label mail) {
		this.name = name;
		this.mail = mail;
	}

	public Person(Label name) {
		this(name, new NullLabel());
	}

	public void display() {
		name.display();
		mail.display();
	}

	public String toString() {
		return "[ Person : name = " + name + " mail= " + mail + " ]";
	}
}

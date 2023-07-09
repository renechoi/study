package org.refactoring.compositionvsinheritance.before;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Subject {

	String subjectName;
	int subjectCode;
	int score;

	public Subject(String subjectName, int subjectCode) {
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
	}

	public String toString() {
		return subjectCode + ":" + subjectName;
	}
}

package org.refactoring.compositionvsinheritance.before;

import java.util.ArrayList;

public class Student {

	String studentName;
	String studentId;
	Subject majorSubject;

	ArrayList<Subject> subjectList = new ArrayList<>();

	public Student(String studentName, String studentId, String subjectName, int subjectCode) {
		majorSubject = new Subject(subjectName, subjectCode);

		this.studentName = studentName;
		this.studentId = studentId;
	}

	public void addSubject(String subjectName, int subjectCode) {
		Subject subject = new Subject(subjectName, subjectCode);
		subjectList.add(subject);
	}

	public void setSubjectScore(int subjectCode, int score) {
		for (Subject subject : subjectList) {
			if (subject.getSubjectCode() == subjectCode) {
				subject.setScore(score);
				return;
			}
		}
		System.out.println("no subject code error");
	}

	public void getReport() {
		int total = majorSubject.score;

		System.out.println(studentName + "님의 전공과목은 " + majorSubject.getSubjectName() + "입니다.");
		System.out.println(majorSubject.getSubjectName() + ":" + majorSubject.getScore());

		for (Subject subject : subjectList) {
			System.out.println(subject.getSubjectName() + ":" + subject.getScore());
			total += subject.getScore();
		}

		System.out.println("Total Score : " + total);
	}
}

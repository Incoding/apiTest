package com.javaapi.test.spring.spring.ioc.annotation.annotation.annotationSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherImp implements WorkerI {
	private String	name	= "wk";
	@Autowired
	private Student	student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void work() {
		String x = name + "正在上课,学生是:" + getStudent().getName();
		System.out.println(x);
	}

}

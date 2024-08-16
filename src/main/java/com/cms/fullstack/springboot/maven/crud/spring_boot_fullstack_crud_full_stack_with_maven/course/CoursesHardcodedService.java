package com.cms.fullstack.springboot.maven.crud.spring_boot_fullstack_crud_full_stack_with_maven.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class CoursesHardcodedService {
	
	private static List<Course>courses = new ArrayList<>();
	private static long idCounter = 0;
	
	static {
		courses.add(new Course(++idCounter, "CMS", "Learn full stack with Spring Boot and Angular"));
		courses.add(new Course(++idCounter, "CMS", "Learn full stack with Spring Boot and React"));
		courses.add(new Course(++idCounter, "CMS", "Deploy Spring Boot Microservices with Spring Boot and Spring Cloud"));
		courses.add(new Course(++idCounter, "CMS", "Master Spring Boot Microservices with Spring Boot and Spring Cloud"));
		courses.add(new Course(++idCounter, "CMS", "Master Spring Boot Microservices with Spring Boot and Spring Cloud 2"));
	}

	public List<Course> findAll() {
		return courses;
	}

	public Course findById(long id) {
		for( Course course: courses) {
			if(course.getId()==id) {
				return course;
			}
		}
		return null;
	}

	public Course save (Course course) {
		if(course.getId()== -1 || course.getId()==0) {
			course.setId(++idCounter);
			courses.add(course);
		} else {
			deleteById(course.getId());
			courses.add(course);
		}
		return course;
	}

	public Course deleteById(Long id) {
		Course course = findById(id);
		if(course ==null) {
			return null;
		}
		if(courses.remove(course)&& Objects.equals(course.getId(), id)) {
				return course;
		}
		return null;
	}
}

package com.cms.fullstack.springboot.maven.crud.spring_boot_fullstack_crud_full_stack_with_maven.course;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CoursesHardcodedService {

    private static List<Course> courses = new CopyOnWriteArrayList<>(); // Thread-safe collection
    private static AtomicLong idCounter = new AtomicLong(); // AtomicLong for thread-safe ID generation

    static {
        courses.add(new Course(idCounter.incrementAndGet(), "CMS", "Learn full stack with Spring Boot and Angular"));
        courses.add(new Course(idCounter.incrementAndGet(), "CMS", "Learn full stack with Spring Boot and React"));
        courses.add(new Course(idCounter.incrementAndGet(), "CMS", "Learn full stack with Spring Boot and Vue"));
        courses.add(new Course(idCounter.incrementAndGet(), "CMS", "Deploy Spring Boot Microservices with Spring Boot and Spring Cloud"));
        courses.add(new Course(idCounter.incrementAndGet(), "CMS", "Master Spring Boot Microservices with Spring Boot and Spring Cloud"));
        courses.add(new Course(idCounter.incrementAndGet(), "CMS", "Master Spring Boot Microservices with Spring Boot and Spring Cloud 2"));
    }

    public List<Course> findAll() {
        return Collections.unmodifiableList(courses); // Return an unmodifiable list to prevent external modifications
    }

    public Optional<Course> findById(long id) {
        return courses.stream()
                      .filter(course -> course.getId() == id)
                      .findFirst();
    }

    public Course save(Course course) {
        if (course.getId() <= 0) {
            course.setId(idCounter.incrementAndGet());
            courses.add(course);
        } else {
            deleteById(course.getId());
            courses.add(course);
        }
        return course;
    }

    public Optional<Course> deleteById(Long id) {
        Optional<Course> courseOptional = findById(id);
        courseOptional.ifPresent(courses::remove); // Remove if present
        return courseOptional;
    }
}

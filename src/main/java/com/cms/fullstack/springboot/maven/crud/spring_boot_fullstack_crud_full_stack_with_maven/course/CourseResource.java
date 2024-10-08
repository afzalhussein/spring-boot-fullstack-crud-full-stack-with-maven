package com.cms.fullstack.springboot.maven.crud.spring_boot_fullstack_crud_full_stack_with_maven.course;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = { "https://localhost:3000", "http://localhose:4200" })
@RestController
public class CourseResource {
	@Autowired
	private CoursesHardcodedService courseManagementService;

	@GetMapping("/instructors/{username}/courses")
	public List<Course> getAllCourses(@PathVariable String username) {
		return courseManagementService.findAll();
	}

	@GetMapping("/instructors/{username}/courses/{id}")
	public Optional<Course> getCourse(@PathVariable String username, @PathVariable long id) {
		return courseManagementService.findById(id);
	}

	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, long id) {
		Optional<Course> course = courseManagementService.deleteById(id);
		if (course.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
			@RequestBody Course course) {
		Course courseUpdated = courseManagementService.save(course);
		return new ResponseEntity<>(courseUpdated, HttpStatus.OK);
	}

	@PostMapping("/instructors/{username}/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {
		Course createdCourse = courseManagementService.save(course);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}

package com.champlain.courseservice.dataaccesslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    Course course1;
    Course course2;

    @BeforeEach
    public void setupDB() {
         course1 = buildCourse("courseId1", "courseName1");

         Publisher<Course> setUp1 = courseRepository.deleteAll().thenMany(courseRepository.save(course1));

         StepVerifier.create(setUp1).expectNextCount(1).verifyComplete();
         course2 = buildCourse("courseId2", "courseName2");
         Publisher<Course> setUp2 = courseRepository.save(course2);

         StepVerifier.create(setUp2).expectNextCount(1).verifyComplete();

    }
    @Test
    public void shouldSaveSingleCourse() {
        //arange & act
        Publisher<Course> setup = courseRepository.deleteAll().thenMany(courseRepository.save(buildCourse("courseId3", "courseName3")));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void shouldGetAllStudents(){
        //arrange
        Publisher<Course> setUp = courseRepository.findAll();
        //act & assert
        StepVerifier.create(setUp)
                .expectNextCount(2)
                .verifyComplete();
    }

    private Course buildCourse(String courseId, String courseName) {
        return Course.builder()
                .courseId(courseId)
                .courseNumber("courseNumber")
                .courseName(courseName)
                .build();
    }
}
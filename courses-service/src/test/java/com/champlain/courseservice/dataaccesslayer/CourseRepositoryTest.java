package com.champlain.courseservice.dataaccesslayer;

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


    @Test
    public void shouldSaveSingleCourse() {
        //arange & act
        Publisher<Course> setup = courseRepository.deleteAll().thenMany(courseRepository.save(buildCourse()));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Course buildCourse() {
        return Course.builder()
                .courseId("courseId")
                .courseNumber("courseNumber")
                .courseName("courseName")
                .numCredits(3.0)
                .numHours(3)
                .department("department")
                .build();
    }
}
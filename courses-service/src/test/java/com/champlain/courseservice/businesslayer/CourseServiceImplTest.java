package com.champlain.courseservice.businesslayer;

import com.champlain.courseservice.dataaccesslayer.Course;
import com.champlain.courseservice.dataaccesslayer.CourseRepository;
import com.champlain.courseservice.presentationlayer.CourseRequestDTO;
import com.champlain.courseservice.presentationlayer.CourseResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureWebTestClient
class CourseServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    void getCoursesByCourseId() {
        //arrange
        Course course = buildCourse();
        String courseId = course.getCourseId();

        when(courseRepository.findCourseByCourseId(courseId)).thenReturn(Mono.just(course));

        //act
        Mono<CourseResponseDTO> courseResponseDTOMono = courseService.getCourseById(courseId);

        //assert
        StepVerifier
                .create(courseResponseDTOMono)
                .consumeNextWith(foundCourse -> {
                    assertEquals(course.getCourseId(), foundCourse.getCourseId());
                    assertEquals(course.getCourseNumber(), foundCourse.getCourseNumber());
                    assertEquals(course.getCourseName(), foundCourse.getCourseName());
                    assertEquals(course.getNumCredits(), foundCourse.getNumCredits());
                    assertEquals(course.getNumHours(), foundCourse.getNumHours());
                    assertEquals(course.getDepartment(), foundCourse.getDepartment());

                }).verifyComplete();

    }

/*    @Test
    void addNewCourse() {
        //arange
        CourseRequestDTO courseRequestDTO = buildCourseRequestDTO();
        Course course = buildCourse();
        when(courseRepository.save(course)).thenReturn(Mono.just(course));

        //act
        Mono<CourseResponseDTO> courseResponseDTOMono = courseService.addCourse(courseRequestDTO); ;
    }*/
    private Course buildCourse() {
        return Course.builder()
                .courseId("4PQ2F047-0d1d-4d98-9152-13f1baf4e79a")
                .courseNumber("courseNumber")
                .courseName("courseName")
                .numCredits(3.0)
                .numHours(3)
                .department("department")
                .build();
    }

    private CourseRequestDTO buildCourseRequestDTO() {
        return CourseRequestDTO.builder().courseNumber("courseNumber").courseName("courseName").numHours(3).numCredits(3.0).department("department").build();
    }

    //


}
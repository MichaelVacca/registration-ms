package com.champlain.courseservice.businesslayer;

import static org.junit.jupiter.api.Assertions.*;

import com.champlain.courseservice.dataaccesslayer.Course;
import com.champlain.courseservice.dataaccesslayer.CourseRepository;
import com.champlain.courseservice.presentationlayer.CourseRequestDTO;
import com.champlain.courseservice.presentationlayer.CourseResponseDTO;
import com.champlain.courseservice.utils.exceptions.InvalidInputException;
import com.champlain.courseservice.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
class CourseServiceUnitTest {

    @Autowired
    CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    CourseResponseDTO courseResponseDTO = CourseResponseDTO.builder()
            .courseId("c2db7b50-26b5-43f0-ab03-8dc5dab937fb")
            .courseName("Web Services")
            .courseNumber("420-N45-LA")
            .department("Computer Science")
            .numCredits(2.0)
            .numHours(60)
            .build();

    Course course = Course.builder()
            .courseId("c2db7b50-26b5-43f0-ab03-8dc5dab937fb")
            .courseName("Web Services")
            .courseNumber("420-N45-LA")
            .department("Computer Science")
            .numCredits(2.0)
            .numHours(60)
            .build();

    @Test
    void getAllCourses_ValidId_shouldSucceed() {
        // Arrange
        when(courseRepository.findAll())
                .thenReturn(Flux.just(course));

        // Act
        Flux<CourseResponseDTO> courseResponseDTOFlux = courseService
                .getAllCourses();

        // Assert
        StepVerifier
                .create(courseResponseDTOFlux)
                .consumeNextWith(foundCourse -> {
                    assertNotNull(foundCourse);
                    assertEquals(course.getCourseId(), foundCourse.getCourseId());
                    assertEquals(course.getCourseName(), foundCourse.getCourseName());
                    assertEquals(course.getCourseNumber(), foundCourse.getCourseNumber());
                    assertEquals(course.getDepartment(), foundCourse.getDepartment());
                    assertEquals(course.getNumCredits(), foundCourse.getNumCredits());
                    assertEquals(course.getNumHours(), foundCourse.getNumHours());
                })
                .verifyComplete();
    }

    @Test
    void getCourseByCourseId_ValidId_shouldSucceed(){
        //arrange
        when(courseRepository.findCourseByCourseId(anyString()))
                .thenReturn(Mono.just(course));

        //act
        Mono<CourseResponseDTO> courseResponseDTOMono = courseService
                .getCourseById(course.getCourseId());

        //assert
        StepVerifier
                .create(courseResponseDTOMono)
                .consumeNextWith(foundCourse ->{
                    assertNotNull(foundCourse);
                    assertEquals(course.getCourseId(), foundCourse.getCourseId());
                    assertEquals(course.getCourseName(), foundCourse.getCourseName());
                    assertEquals(course.getCourseNumber(), foundCourse.getCourseNumber());
                    assertEquals(course.getDepartment(), foundCourse.getDepartment());
                    assertEquals(course.getNumCredits(), foundCourse.getNumCredits());
                    assertEquals(course.getNumHours(), foundCourse.getNumHours());
                })
                .verifyComplete();
    }

    @Test
    void deleteCourse_ValidCourseId_shouldSucceed() {
        // Arrange
        String courseIdToDelete = "c2db7b50-26b5-43f0-ab03-8dc5dab937fb";


        // Mock the behavior of the courseRepository.findCourseByCourseId method to return a non-null Mono
        when(courseRepository.findCourseByCourseId(courseIdToDelete))
                .thenReturn(Mono.just(new Course())); // Replace with a Course object

        // Mock the behavior of the courseRepository.deleteById method
        when(courseRepository.deleteById(anyString()))
                .thenReturn(Mono.empty()); // Empty Mono to simulate successful deletion

        // Act
        Mono<Void> deletionMono = courseService.deleteCourseById(courseIdToDelete);

        // Assert
        StepVerifier
                .create(deletionMono)
                .verifyComplete();
    }



    @Test
    void addCourse_ValidCourse_shouldSucceed() {
        // Arrange
        CourseRequestDTO courseRequestDTO = CourseRequestDTO.builder()
                .courseName("Web Services")
                .courseNumber("420-N45-LA")
                .department("Computer Science")
                .numCredits(2.0)
                .numHours(60)
                .build();

        Course courseEntity = Course.builder()
                .courseId("c2db7b50-26b5-43f0-ab03-8dc5dab937fb")
                .courseName("Web Services")
                .courseNumber("420-N45-LA")
                .department("Computer Science")
                .numCredits(2.0)
                .numHours(60)
                .build();

        when(courseRepository.insert(any(Course.class)))
                .thenReturn(Mono.just(courseEntity));

        // Act
        Mono<CourseResponseDTO> courseResponseDTOMono = courseService.addCourse(Mono.just(courseRequestDTO));

        // Assert
        StepVerifier
                .create(courseResponseDTOMono)
                .expectNextMatches(foundCourse -> {
                    assertNotNull(foundCourse);
                    assertEquals(course.getCourseId(), foundCourse.getCourseId());
                    assertEquals(course.getCourseName(), foundCourse.getCourseName());
                    assertEquals(course.getCourseNumber(), foundCourse.getCourseNumber());
                    assertEquals(course.getDepartment(), foundCourse.getDepartment());
                    assertEquals(course.getNumCredits(), foundCourse.getNumCredits());
                    assertEquals(course.getNumHours(), foundCourse.getNumHours());

                    return true;
                })
                .verifyComplete();
    }

    @Test
    void updateCourseById_InvalidCourseId_shouldThrowInvalidInputException() {
        // Arrange
        String invalidCourseId = "shortid"; // Not 36 characters
        CourseRequestDTO courseRequestDTO = CourseRequestDTO.builder().build(); // Just an empty object for this case

        // Act and Assert
        StepVerifier
                .create(courseService.updateStudentById(Mono.just(courseRequestDTO), invalidCourseId))
                .expectErrorMatches(exception -> exception instanceof InvalidInputException &&
                        exception.getMessage().contains("Invalid courseId, length must be 36 characters"))
                .verify();
    }

/*    @Test
    void updateCourseById_NonExistentCourseId_shouldThrowNotFoundException() {
        // Arrange
        String nonExistentCourseId = "c2db7b50-26b5-43f0-ab03-8dc5dab99988"; // Assuming this ID does not exist in database
        CourseRequestDTO courseRequestDTO = CourseRequestDTO.builder().build();

        when(courseRepository.findCourseByCourseId(nonExistentCourseId)).thenReturn(Mono.empty());

        // Act and Assert
        StepVerifier
                .create(courseService.updateStudentById(Mono.just(courseRequestDTO), nonExistentCourseId))
                .expectErrorMatches(exception -> exception instanceof NotFoundException &&
                        exception.getMessage().contains("No course with this courseId was found"))
                .verify();
    }*/

    @Test
    void updateCourseById_ValidCourseIdAndRequest_ShouldUpdateAndReturnCourse() {
        // Arrange
        String validCourseId = "c2db7b50-26b5-43f0-ab03-8dc5dab937fb";
        CourseRequestDTO courseRequestDTO = CourseRequestDTO.builder()
                // You can set other attributes if needed
                .build();

        Course existingCourse = Course.builder()
                .courseId(validCourseId)
                .id("123") // Some existing DB ID
                // Add other attributes if needed
                .build();

        Course updatedCourseEntity = Course.builder()
                // Updated attributes for the course entity
                .build();

        when(courseRepository.findCourseByCourseId(validCourseId)).thenReturn(Mono.just(existingCourse));
        when(courseRepository.save(any(Course.class))).thenReturn(Mono.just(updatedCourseEntity));

        // Act and Assert
        StepVerifier
                .create(courseService.updateStudentById(Mono.just(courseRequestDTO), validCourseId))
                .expectNextMatches(updatedCourse -> {
                    // Check if the returned updatedCourse is correct
                    // You can add more assertions if needed
                    return true;
                })
                .verifyComplete();
    }


    @Test
    void updateCourse_ValidCourse_shouldSucceed() {
        //Arrange
        String validCourseId = "c2db7b50-26b5-43f0-ab03-8dc5dab937fb";
        CourseRequestDTO courseRequestDTO = CourseRequestDTO.builder()
                .courseName("Web Services")
                .courseNumber("420-N45-LA")
                .department("Computer Science")
                .numCredits(2.0)
                .numHours(60)
                .build();

    }


}
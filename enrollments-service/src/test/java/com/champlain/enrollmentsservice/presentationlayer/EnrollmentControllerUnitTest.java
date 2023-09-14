package com.champlain.enrollmentsservice.presentationlayer;

import com.champlain.enrollmentsservice.businesslayer.EnrollmentService;
import com.champlain.enrollmentsservice.dataaccesslayer.Semester;
import com.champlain.enrollmentsservice.domainclientlayer.StudentResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@WebFluxTest(controllers = EnrollmentController.class)

class EnrollmentControllerUnitTest {

   /* @Autowired
    WebTestClient webTestClient;

    @MockBean
    EnrollmentService enrollmentService;


    @Test
    void getAllEnrollmentsByID_expected(){
            EnrollmentResponseDTO enrollmentResponseDTO = EnrollmentResponseDTO.builder()
                    .enrollmentId("enrollmentId")
                    .enrollmentYear(1)
                    .semester(Semester.FALL)
                    .studentId("studentId")
                    .studentFirstName("studentFirstName")
                    .studentLastName("studentLastName")
                    .courseId("courseId")
                    .courseNumber("courseNumber")
                    .courseName("courseName")
                    .build();

            when(enrollmentService.getEnrollmentById("enrollmentId")).thenReturn(Mono.just(enrollmentResponseDTO));

            webTestClient
                    .get()
                    .uri("/enrollments/{enrollmentId}", enrollmentResponseDTO.getEnrollmentId())
                    .accept(APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(APPLICATION_JSON)
                    .expectBody(EnrollmentResponseDTO.class)
                    .value(dto -> {
                        assertNotNull(dto);
                        assertEquals(enrollmentResponseDTO.getEnrollmentId(), dto.getEnrollmentId());
                        assertEquals(enrollmentResponseDTO.getEnrollmentYear(), dto.getEnrollmentYear());
                    });

            verify(enrollmentService, times(1))
                    .getEnrollmentById(enrollmentResponseDTO.getEnrollmentId());


    }*/
}
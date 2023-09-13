package com.champlain.enrollmentsservice.domainclientlayer;

import com.champlain.enrollmentsservice.utils.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@WebFluxTest(StudentClientTest.class)
class StudentClientTest {

    @MockBean
    private ConnectionFactoryInitializer initializer;

    @MockBean
    private StudentClient studentClient;

    private ObjectMapper objectMapper = new ObjectMapper();



    private static MockWebServer webServer;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();

    }
    @BeforeEach
    void initialize() {
        studentClient = new StudentClient("localhost",String.valueOf(webServer.getPort()));

    }

    @Test
    void getCourseByCourseId() throws IOException {

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO("studentId", "studentFirstName", "studentLastName", "studentProgram");
        webServer.enqueue(new MockResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(objectMapper.writeValueAsString(studentResponseDTO))
                .addHeader("Content-type", "application/json"));

        Mono<StudentResponseDTO> studentResponseDTOMono = studentClient.getStudentByStudentId("studentId");
        StepVerifier.create(studentResponseDTOMono)
                .expectNextMatches(studentResponseDTO1 ->
                        studentResponseDTO1.getStudentId().equals("studentId")
                ).verifyComplete();
    }
        @Test
        void getCourseByCourseIdNotFound() throws IOException {
            // 1. Simulate a 404 response from your server
            webServer.enqueue(new MockResponse().setResponseCode(404));

            Mono<StudentResponseDTO>  studentResponseDTOMono = studentClient.getStudentByStudentId("studentId");

            // Verify that the response is an error of type NotFoundException
            StepVerifier.create(studentResponseDTOMono)
                    .expectErrorMatches(throwable -> throwable instanceof NotFoundException
                            && throwable.getMessage().equals("StudentId not found: studentId"))
                    .verify();
        }


        //InvalidInput
        @Test
        void getStudentByStudentIdBadRequest() throws IOException {
            // 2. Simulate a 400 Bad Request or any other 4xx response from your server
            webServer.enqueue(new MockResponse().setResponseCode(400));

            Mono<StudentResponseDTO> studentResponseDTOMono1 = studentClient.getStudentByStudentId("studentId");

            // Verify that the response is an error of type IllegalArgumentException
            StepVerifier.create(studentResponseDTOMono1)
                    .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException
                            && throwable.getMessage().equals("Something went wrong"))
                    .verify();
        }
}
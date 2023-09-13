package com.champlain.enrollmentsservice.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@WebFluxTest(CourseClientTest.class)
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

}
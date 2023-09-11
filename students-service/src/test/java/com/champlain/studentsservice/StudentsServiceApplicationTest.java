package com.champlain.studentsservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentsServiceApplicationTest {

    @Autowired
    private StudentsServiceApplication studentsServiceApplication;

    @Test
    void contextLoads() {
        assertNotNull(studentsServiceApplication);
    }

    @Test
    void main() {
        StudentsServiceApplication.main(new String[]{});
    }
}
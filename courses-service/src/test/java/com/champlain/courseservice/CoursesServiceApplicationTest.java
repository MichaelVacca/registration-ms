package com.champlain.courseservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoursesServiceApplicationTest {

    @Autowired
    private CoursesServiceApplication coursesServiceApplication;

    @Test
    void contextLoads() {

        assertNotNull(coursesServiceApplication);
    }

    @Test
    void main() {

        coursesServiceApplication.main(new String[]{});
    }

}
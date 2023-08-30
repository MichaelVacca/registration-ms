package com.champlain.courseservice.dataaccesslayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {

    Mono<Course> findCourseByCourseId(String courseId);

}

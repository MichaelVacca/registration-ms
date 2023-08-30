package com.champlain.courseservice.businesslayer;

import com.champlain.courseservice.dataaccesslayer.CourseRepository;
import com.champlain.courseservice.presentationlayer.CourseRequestDTO;
import com.champlain.courseservice.presentationlayer.CourseResponseDTO;
import com.champlain.courseservice.utils.EntityDTOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Override
    public Flux<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll()
                .map(EntityDTOUtils::toCourseResponseDTO);
    }

    @Override
    public Mono<CourseResponseDTO> getCourseById(String courseId) {
        return courseRepository.findCourseByCourseId(courseId)
                .map(EntityDTOUtils::toCourseResponseDTO);
    }

    @Override
    public Mono<CourseResponseDTO> addCourse(Mono<CourseRequestDTO> courseRequestDTO) {
        return courseRequestDTO
                .map(EntityDTOUtils::toCourseEntity)
                .doOnNext(e -> e.setCourseId(EntityDTOUtils.generateUUIDString()))
                .flatMap(courseRepository::insert)
                .map(EntityDTOUtils::toCourseResponseDTO);
    }

    @Override
    public Mono<CourseResponseDTO> updateStudentById(Mono<CourseRequestDTO> courseRequestDTO, String courseId) {
        return courseRepository.findCourseByCourseId(courseId).flatMap(course ->
            courseRequestDTO
                    .map(EntityDTOUtils::toCourseEntity)
                    .doOnNext(e -> {
                        e.setCourseId(course.getCourseId());
                        e.setId(course.getId());

        }))
                .flatMap(courseRepository::save)
                .map(EntityDTOUtils::toCourseResponseDTO);
    }



    @Override
    public Mono<Void> deleteCourseById(String courseId) {
        return courseRepository.findCourseByCourseId(courseId)
                .flatMap(courseRepository::delete);
    }

}


package com.champlain.courseservice.utils;


import com.champlain.courseservice.dataaccesslayer.Course;
import com.champlain.courseservice.presentationlayer.CourseRequestDTO;
import com.champlain.courseservice.presentationlayer.CourseResponseDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class EntityDTOUtils {

    public static CourseResponseDTO toCourseResponseDTO(Course course){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        BeanUtils.copyProperties(course, courseResponseDTO);
        return courseResponseDTO;

    }

    public static Course toCourseEntity(CourseRequestDTO courseRequestDTO){

        Course course = new Course();
        BeanUtils.copyProperties(courseRequestDTO, course);
        return course;
    }

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }


}

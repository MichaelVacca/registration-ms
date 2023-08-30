package com.champlain.enrollmentsservice.utils;

import com.champlain.enrollmentsservice.businesslayer.RequestContextAdd;
import com.champlain.enrollmentsservice.dataaccesslayer.Enrollment;
import com.champlain.enrollmentsservice.presentationlayer.EnrollmentResponseDTO;

import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class EntityDTOUtils {


        public static EnrollmentResponseDTO toEnrollmentResponseDTO(Enrollment enrollment){
            EnrollmentResponseDTO enrollmentResponseDTO = new EnrollmentResponseDTO();
            BeanUtils.copyProperties(enrollment, enrollmentResponseDTO);
            return enrollmentResponseDTO;

        }

        public static Enrollment toEnrollmentEntity(RequestContextAdd rc){
            return Enrollment.builder()
                    .enrollmentId(generateUUIDString())
                    .courseId(rc.getCourseResponseDTO().getCourseId())
                    .enrollmentYear(rc.getEnrollmentRequestDTO().getEnrollmentYear())
                    .courseName(rc.getCourseResponseDTO().getCourseName())
                    .courseNumber(rc.getCourseResponseDTO().getCourseNumber())
                    .semester(rc.getEnrollmentRequestDTO().getSemester())
                    .studentFirstName(rc.getStudentResponseDTO().getFirstName())
                    .studentLastName(rc.getStudentResponseDTO().getLastName())
                    .studentId(rc.getStudentResponseDTO().getStudentId())
                    .build();
        }

        public static String generateUUIDString(){
            return UUID.randomUUID().toString();
        }



}

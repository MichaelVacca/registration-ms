package com.champlain.studentsservice.utils;

import com.champlain.studentsservice.dataaccesslayer.Student;
import com.champlain.studentsservice.presentationlayer.StudentRequestDTO;
import com.champlain.studentsservice.presentationlayer.StudentResponseDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class EntityDTOUtils {

    public static StudentResponseDTO toStudentResponseDTO(Student student){
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        BeanUtils.copyProperties(student, studentResponseDTO);
        return studentResponseDTO;

    }

    public static Student toStudentEntity(StudentRequestDTO studentRequestDTO){

        Student student = new Student();
        BeanUtils.copyProperties(studentRequestDTO, student);
        return student;
    }

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }


}

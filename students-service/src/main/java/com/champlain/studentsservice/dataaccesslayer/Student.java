package com.champlain.studentsservice.dataaccesslayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {


    @Id
    private String id;

    private String studentId;
    private String firstName;
    private String lastName;
    private String program;


}

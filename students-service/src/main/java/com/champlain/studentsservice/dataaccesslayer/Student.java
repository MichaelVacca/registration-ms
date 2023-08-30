package com.champlain.studentsservice.dataaccesslayer;

import com.champlain.studentsservice.presentationlayer.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Mono;

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

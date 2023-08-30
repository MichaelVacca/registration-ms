package com.champlain.enrollmentsservice.dataaccesslayer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("enrollments")
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Id
    private Integer id;

    @Column("enrollmentId")
    private String enrollmentId;

    @Column("enrollmentYear")
    private Integer enrollmentYear;

    private Semester semester;

    @Column("studentId")
    private String studentId;

    @Column("studentFirstName")
    private String studentFirstName;

    @Column("studentLastName")
    private String studentLastName;

    @Column("courseId")
    private String courseId;

    @Column("courseNumber")
    private String courseNumber;

    @Column("courseName")
    private String courseName;
}


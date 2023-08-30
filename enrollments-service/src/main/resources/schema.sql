DROP TABLE IF EXISTS enrollments;

CREATE TABLE IF NOT EXISTS enrollments
(
    id               SERIAL,
    enrollmentId     VARCHAR(36),
    enrollmentYear   SMALLINT,
    semester        VARCHAR(50),
    studentId        VARCHAR(36),
    studentFirstName VARCHAR(50),
    studentLastName  VARCHAR(50),
    courseId         VARCHAR(36),
    courseNumber     VARCHAR(50),
    courseName       VARCHAR(50),
    PRIMARY KEY (id)
    );
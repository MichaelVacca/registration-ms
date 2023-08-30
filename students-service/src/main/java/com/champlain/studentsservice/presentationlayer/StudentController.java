package com.champlain.studentsservice.presentationlayer;

import com.champlain.studentsservice.businesslayer.StudentService;
import com.champlain.studentsservice.dataaccesslayer.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

@RestController
@RequestMapping("students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Mono<ResponseEntity<StudentResponseDTO>> getStudentById(@PathVariable String studentId){
        return studentService.getStudentById(studentId)
                .map(s -> ResponseEntity.ok().body(s))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<ResponseEntity<StudentResponseDTO>> addStudent(@RequestBody Mono<StudentRequestDTO> studentRequestBody) throws URISyntaxException {
        return studentService.addStudent(studentRequestBody).map(s -> ResponseEntity.status(HttpStatus.CREATED).body(s))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{studentId}")
    public Mono<ResponseEntity<StudentResponseDTO>> updateStudent(@PathVariable String studentId,@RequestBody Mono<StudentRequestDTO> studentRequestBody){
        return this.studentService.updateStudentById(studentRequestBody,studentId)
                .map(student -> ResponseEntity.ok().body(student))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{studentId}")
    public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable String studentId){
        return studentService.deleteStudentById(studentId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}

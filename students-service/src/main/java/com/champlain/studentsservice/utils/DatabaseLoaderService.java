package com.champlain.studentsservice.utils;

import com.champlain.studentsservice.dataaccesslayer.Student;
import com.champlain.studentsservice.dataaccesslayer.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DatabaseLoaderService implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception{
        Student student1 = Student
                .builder().
                firstName("Micheal")
                .lastName("Jordan")
                .studentId(UUID.randomUUID().toString())
                .program("Computer Science")
                .build();


        Student student2 = Student
                .builder().
                firstName("Mohammed")
                .lastName("Ali")
                .studentId(UUID.randomUUID().toString())
                .program("Pure and Applied")
                .build();

        Student student3 = Student
                .builder().
                firstName("Cristiano")
                .lastName("Ronaldo")
                .studentId(UUID.randomUUID().toString())
                .program("Graphics and Media")
                .build();

        Student student4 = Student
                .builder().
                firstName("Tom")
                .lastName("Brady")
                .studentId(UUID.randomUUID().toString())
                .program("Math and Computer Science")
                .build();


        Flux.just(student1, student2, student3, student4)
                .flatMap(s -> studentRepository.insert(Mono.just(s))
                .log(s.toString())).subscribe();


    }

}

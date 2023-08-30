package com.champlain.enrollmentsservice.presentationlayer;

import com.champlain.enrollmentsservice.businesslayer.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("enrollments")
@Slf4j
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping()
    public Mono<ResponseEntity<EnrollmentResponseDTO>> addEnrollment(@RequestBody Mono<EnrollmentRequestDTO> enrollmentRequestDTOMono){
        return enrollmentService.addEnrollment(enrollmentRequestDTOMono)
                .map(e -> ResponseEntity.status(HttpStatus.CREATED).body(e))
                .doOnNext(i -> System.out.println(("Created new enrollment")))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}





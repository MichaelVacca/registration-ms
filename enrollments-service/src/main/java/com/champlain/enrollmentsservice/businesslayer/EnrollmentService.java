package com.champlain.enrollmentsservice.businesslayer;

import com.champlain.enrollmentsservice.presentationlayer.EnrollmentResponseDTO;
import com.champlain.enrollmentsservice.presentationlayer.EnrollmentRequestDTO;
import reactor.core.publisher.Mono;

public interface EnrollmentService {


    Mono<EnrollmentResponseDTO> addEnrollment(Mono<EnrollmentRequestDTO> sectionRequestDTO);


}

package com.champlain.enrollmentsservice.businesslayer;

import com.champlain.enrollmentsservice.dataaccesslayer.EnrollmentRepository;
import com.champlain.enrollmentsservice.domainclientlayer.CourseClient;
import com.champlain.enrollmentsservice.domainclientlayer.StudentClient;
import com.champlain.enrollmentsservice.presentationlayer.EnrollmentRequestDTO;
import com.champlain.enrollmentsservice.presentationlayer.EnrollmentResponseDTO;
import com.champlain.enrollmentsservice.utils.EntityDTOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentClient studentClient;
    private final CourseClient courseClient;

    @Override
    public Mono<EnrollmentResponseDTO> addEnrollment(Mono<EnrollmentRequestDTO> requestDTO) {
        return requestDTO
                .map(RequestContextAdd::new)
                .flatMap(this::studentRequestResponse)
                .flatMap(this::courseRequestResponse)
                .map(EntityDTOUtils::toEnrollmentEntity)
                .map(enrollmentRepository::save)
                .flatMap(entity -> entity)
                .map(EntityDTOUtils::toEnrollmentResponseDTO);

    }

    private Mono<RequestContextAdd> courseRequestResponse(RequestContextAdd rc) {
        return this.courseClient.getCourseByCourseId(rc.getEnrollmentRequestDTO().getCourseId())
                .doOnNext(rc::setCourseResponseDTO)
                .thenReturn(rc);
    }

    private Mono<RequestContextAdd> studentRequestResponse(RequestContextAdd rc){
        return this.studentClient.getStudentByStudentId(rc.getEnrollmentRequestDTO().getStudentId())
                .doOnNext(rc::setStudentResponseDTO)
                .thenReturn(rc);
    }

}
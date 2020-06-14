package org.medihub.persistence.appointment_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAllAppointmentRequestsPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAppointmentRequestForClinicPort;
import org.medihub.application.ports.outgoing.appointment_request.LoadAppointmentRequestPort;
import org.medihub.domain.appointment.AppointmentRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppointmentRequestAdapter implements
        SaveAppointmentRequestPort,
        GetAppointmentRequestForClinicPort,
        DeleteAppointmentRequestPort,
        GetAllAppointmentRequestsPort,
        LoadAppointmentRequestPort {
    private final AppointmentRequestRepository appointmentRequestRepository;
    private final AppointmentRequestMapper mapper;

    @Override
    public void saveAppointmentRequest(AppointmentRequest request) {
        AppointmentRequestJpaEntity entity = mapper.mapToJpaEntity(request);
        appointmentRequestRepository.save(entity);
    }

    @Override
    public List<AppointmentRequest> loadAll(Long clinicId) {
        return mapper.mapToDomainList(appointmentRequestRepository.findAllByDoctorClinicIdAndDoctorArchivedFalse(clinicId));
    }

    @Override
    public void deleteAppointmentRequest(Long id) throws ForbiddenException {
        Optional<AppointmentRequestJpaEntity> appointmentRequestJpaEntity = appointmentRequestRepository.findById(id);
        if(!appointmentRequestJpaEntity.isPresent())
            throw new ForbiddenException();
        appointmentRequestRepository.deleteById(id);
    }

    @Override
    public List<AppointmentRequest> getAll() {
        return mapper.mapToDomainList(appointmentRequestRepository.findAllByDoctorArchivedFalse());
    }

    @Override
    public AppointmentRequest loadById(Long id) throws NotFoundException {
        AppointmentRequestJpaEntity appointmentRequestJpaEntity =
                appointmentRequestRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.mapToDomainEntity(appointmentRequestJpaEntity);
    }

    @Override
    public AppointmentRequest loadByIdWithLock(Long id) throws NotFoundException {
        AppointmentRequestJpaEntity appointmentRequestJpaEntity;
        appointmentRequestJpaEntity =
                appointmentRequestRepository.findByIdWithLock(id).orElseThrow(NotFoundException::new);

        return mapper.mapToDomainEntity(appointmentRequestJpaEntity);
    }
}
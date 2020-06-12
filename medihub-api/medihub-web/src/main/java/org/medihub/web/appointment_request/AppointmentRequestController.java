package org.medihub.web.appointment_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.AppointmentRequestResponse;
import org.medihub.application.ports.incoming.appointment_request.DeleteAppointmentRequestUseCase;
import org.medihub.application.ports.incoming.appointment_request.GetAppointmentRequestForClinicUseCase;
import org.medihub.application.ports.incoming.scheduling.ScheduleAppointmentUseCase;
import org.medihub.application.ports.incoming.scheduling.ScheduleAppointmentUseCase.ScheduleAppointmentCommand;
import org.medihub.application.ports.incoming.scheduling.ScheduleDoctorsAppointmentUseCase;
import org.medihub.application.ports.incoming.scheduling.ScheduleDoctorsAppointmentUseCase.ScheduleDoctorsAppointmentCommand;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/appointment-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentRequestController {
    private final ScheduleAppointmentUseCase scheduleAppointmentUseCase;
    private final ScheduleDoctorsAppointmentUseCase scheduleDoctorsAppointmentUseCase;
    private final GetAppointmentRequestForClinicUseCase getAppointmentRequestUseCase;
    private final DeleteAppointmentRequestUseCase deleteAppointmentRequestUseCase;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public void schedule(@RequestBody ScheduleAppointmentRequest request) {
        ScheduleAppointmentCommand command = createCommand(request);
        scheduleAppointmentUseCase.scheduleAppointment(command);
    }

    @PostMapping("/addForDoctor")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public void scheduleForDoctor(@RequestBody AddDoctorsAppointmentRequest addDoctorsAppointmentRequest) {
        ScheduleDoctorsAppointmentCommand command = createDoctorsCommand(addDoctorsAppointmentRequest);
        scheduleDoctorsAppointmentUseCase.scheduleAppointment(command);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public List<AppointmentRequestResponse> getAll() {
        return getAppointmentRequestUseCase.getAll();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public List<AppointmentRequestResponse> delete(@RequestBody Long id) {
        deleteAppointmentRequestUseCase.deleteAppointmentRequest(id);
        return getAppointmentRequestUseCase.getAll();
    }

    private ScheduleAppointmentCommand createCommand(ScheduleAppointmentRequest request) {
        return new ScheduleAppointmentCommand(
                request.getDoctorId(),
                request.getDate(),
                request.getTime(),
                request.getType());
    }

    private ScheduleDoctorsAppointmentCommand createDoctorsCommand(AddDoctorsAppointmentRequest request) {
        return new ScheduleDoctorsAppointmentCommand(
                request.getPatientId(),
                request.getDate(),
                request.getTime(),
                request.getType());
    }
}

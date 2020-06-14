package org.medihub.application.ports.outgoing.clinic_room;

public interface GetClinicRoomFutureScheduleCountPort {
    Long countClinicRoomSchedule(Long clinicRoomId);
}

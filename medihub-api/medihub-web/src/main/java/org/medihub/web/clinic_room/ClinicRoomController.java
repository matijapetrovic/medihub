package org.medihub.web.clinic_room;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.clinic.SearchClinicsOutput;
import org.medihub.application.ports.incoming.clinic_room.*;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase.AddClinicRoomCommand;
import org.medihub.application.ports.incoming.clinic_room.DeleteClinicRoomUseCase.DeleteClinicCommand;
import org.medihub.application.ports.outgoing.clinic_room.SearchClinicRoomsPort;
import org.medihub.web.security.TokenUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/clinic-room", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicRoomController {
    private final TokenUtil tokenUtil;
    private final AddClinicRoomUseCase addClinicRoomUseCase;
    private final DeleteClinicRoomUseCase deleteClinicRoomUseCase;
    private final GetClinicRoomsQuery getClinicRoomsQuery;
    private final SearchClinicRoomsQuery searchClinicRoomsQuery;

//    @GetMapping("")
//    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    ResponseEntity<List<GetClinicRoomsOutput>> get() {
        Long clinicId = getAuthenticatedClinicId();
        return ResponseEntity.ok(getClinicRoomsQuery.getClinicRooms(clinicId));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    ResponseEntity<List<SearchClinicRoomsOutput>> searchClinics(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer number,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date)  {
        return ResponseEntity.ok(searchClinicRoomsQuery.searchClinicRooms(name, number, date));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody ClinicRoomRequest request) {
        Long clinicId = getAuthenticatedClinicId();
        AddClinicRoomCommand command = new AddClinicRoomCommand(clinicId, request.getName(), request.getNumber());
        addClinicRoomUseCase.addClinicRoom(command);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void delete(@RequestBody Long id) throws ForbiddenException {
        Long clinicId = getAuthenticatedClinicId();
        DeleteClinicCommand command = new DeleteClinicCommand(clinicId, id);
        deleteClinicRoomUseCase.deleteClinicRoom(command);
    }

    private String getToken() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return (String) currentUser.getCredentials();
    }

    private Long getAuthenticatedClinicId() {
        String authToken = getToken();
        Claims claims = tokenUtil.getAllClaimsFromToken(authToken);
        return Long.valueOf((Integer) claims.get("clinicId"));
    }
}

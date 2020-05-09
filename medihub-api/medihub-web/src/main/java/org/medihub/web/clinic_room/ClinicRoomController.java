package org.medihub.web.clinic_room;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase.AddClinicRoomCommand;
import org.medihub.application.ports.incoming.clinic_room.DeleteClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsOutput;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsQuery;
import org.medihub.domain.ClinicRoom;
import org.medihub.web.security.TokenUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    ResponseEntity<List<GetClinicRoomsOutput>> get() {
        return ResponseEntity.ok(getClinicRoomsQuery.getClinicRooms());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody ClinicRoomRequest request) {
        String authToken = getToken();
        Long clinicId = getClinicIdFromToken(authToken);
        AddClinicRoomCommand command = new AddClinicRoomCommand(clinicId, request.getName());
        addClinicRoomUseCase.addClinicRoom(command);
    }

    private String getToken() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return (String) currentUser.getCredentials();
    }

    private Long getClinicIdFromToken(String token) {
        Claims claims = tokenUtil.getAllClaimsFromToken(token);
        return Long.valueOf((Integer) claims.get("clinicId"));
    }

    @PostMapping("/delete")
    void delete(@RequestBody ClinicRoomRequest request){
        deleteClinicRoomUseCase.deleteClinicRoom(request.getName());
    }
}

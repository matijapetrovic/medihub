package org.medihub.web.profile;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.account.profile.GetProfileOutput;
import org.medihub.application.ports.incoming.account.profile.GetProfileQuery;
import org.medihub.application.ports.incoming.account.profile.UpdateProfileUseCase;
import org.medihub.application.ports.incoming.account.profile.UpdateProfileUseCase.UpdateProfileCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileController {
    private final UpdateProfileUseCase updateProfileUseCase;
    private final GetProfileQuery getProfileQuery;

    @GetMapping("")
    public ResponseEntity<GetProfileOutput> getProfile() throws AccountNotFoundException {
        GetProfileOutput response =
                getProfileQuery
                        .getProfile(getCurrentUserEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public void updateProfile(@RequestBody UpdateProfileRequest request)
            throws AccountNotFoundException {
        UpdateProfileCommand command =
                new UpdateProfileUseCase.UpdateProfileCommand(
                        getCurrentUserEmail(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getAddress(),
                        request.getCity(),
                        request.getCountry(),
                        request.getTelephoneNum());

        updateProfileUseCase.updateProfile(command);
    }

    public String getCurrentUserEmail() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return currentUser.getName();
    }
}

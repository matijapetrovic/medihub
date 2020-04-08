package org.medihub.web;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.LoginUseCase;
import org.medihub.application.ports.incoming.LoginUseCase.LoginCommand;
import org.medihub.web.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@Component
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping(path="/api/login")
    ResponseEntity<Boolean> login(@RequestBody LoginDTO dto) {
        LoginCommand command = null;
        try {
            command = new LoginCommand(dto.getUsername(), dto.getPassword());
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loginUseCase.login(command), HttpStatus.OK);
    }
}

package br.com.savepass.savepass.controller;

import br.com.savepass.savepass.authentication.JwtAuthenticationResponse;
import br.com.savepass.savepass.model.vo.UserVO;
import br.com.savepass.savepass.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "AuthenticationController", tags = "AuthenticationController")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("signin")
    @ApiOperation(value = "Create a token to your user")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody UserVO loginRequest) {
        String token = authenticationService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}

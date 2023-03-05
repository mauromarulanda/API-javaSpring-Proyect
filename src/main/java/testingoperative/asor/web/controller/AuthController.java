package testingoperative.asor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testingoperative.asor.domain.dto.AuthenticationRequest;
import testingoperative.asor.domain.dto.AuthenticationResponse;
import testingoperative.asor.domain.service.AsorUserDatailsService;
import testingoperative.asor.web.security.JWTUtil;

import java.net.Authenticator;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // esto es propio de spring
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AsorUserDatailsService asorUserDatailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
            UserDetails userDetails = asorUserDatailsService.loadUserByUsername(request.getUser());
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}

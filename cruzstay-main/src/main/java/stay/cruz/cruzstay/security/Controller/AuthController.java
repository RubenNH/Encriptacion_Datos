package stay.cruz.cruzstay.security.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import stay.cruz.cruzstay.security.Controller.dto.LoginDto;
import stay.cruz.cruzstay.services.auth.AuthService;
import stay.cruz.cruzstay.security.jwt.JwtProvider;
import stay.cruz.cruzstay.utils.Response;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signin")
    public ResponseEntity<Response<Object>> login( @Valid @RequestBody LoginDto login ) {
        return service.signIn(login.getUsername(), login.getPassword());
    }
}

package stay.cruz.cruzstay.services.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stay.cruz.cruzstay.models.user.User;
import stay.cruz.cruzstay.security.jwt.JwtProvider;
import stay.cruz.cruzstay.services.user.UserService;
import stay.cruz.cruzstay.utils.Response;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    private final UserService service;
    private final AuthenticationManager manager;
    private final JwtProvider jwtProvider;

    public AuthService(UserService service, AuthenticationManager manager, JwtProvider jwtProvider) {
        this.service = service;
        this.manager = manager;
        this.jwtProvider = jwtProvider;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Response<Object>> signIn(String username, String password) {
        try {
            Optional<User> foundUser = service.findUserByUsername(username);
            String rawPassword = "123456";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String hashedPassword = passwordEncoder.encode(rawPassword);
            System.out.println("Raw Password: " + rawPassword);
            System.out.println("Hashed Password: " + hashedPassword);

            // Check if a password matches the hashed value
            boolean matches = passwordEncoder.matches(rawPassword, hashedPassword);
            System.out.println("Matches: " + matches);

            if (foundUser.isEmpty())
                return new ResponseEntity<>(
                        new Response<>(null, true, 400, "UserNotFound"),
                        HttpStatus.BAD_REQUEST);
            User user = foundUser.get();
            if (!user.getStatus())
                return new ResponseEntity<>(
                        new Response<>(null, true, 400, "Inactive"),
                        HttpStatus.BAD_REQUEST);
            if (!user.getBlocked())
                return new ResponseEntity<>(
                        new Response<>(null, true, 400, "Blocked"),
                        HttpStatus.BAD_REQUEST);

            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            System.out.println("PASSATUH");
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtProvider.generatedToken(auth);
            //SignedDto (token, username, id, fullname, role)
            return new ResponseEntity<>(
                    new Response<>(token, false, 200, "OK"),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            String message = "CredentialsMismatch";
            if (e instanceof DisabledException)
                message = "UserDisabled";
            e.printStackTrace();

            return new ResponseEntity<>(
                    new Response<>(null, true, 400, message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}

package media.web.mediaweb.controller;

import media.web.mediaweb.model.Account;
import media.web.mediaweb.model.Role;
import media.web.mediaweb.model.UserPrinciple;
import media.web.mediaweb.repository.IAccountRepository;
import media.web.mediaweb.security.jwt.JwtResponse;
import media.web.mediaweb.security.jwt.JwtService;
import media.web.mediaweb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private IAccountRepository iAccountRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentUser = accountService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getUsername(), userDetails.getAuthorities()));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody Account account) {
        if (iAccountRepository.existsByUsername(account.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        Set<Role> role = new HashSet<>();
        role.add(new Role(2L , "USER"));
        account.setRoles(role);

        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

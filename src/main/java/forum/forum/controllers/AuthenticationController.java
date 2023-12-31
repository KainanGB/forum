package forum.forum.controllers;


import forum.forum.Logger.Log;
import forum.forum.dtos.request.AuthenticateUser;
import forum.forum.dtos.response.JwtResponseDTO;
import forum.forum.entities.UsersEntity;
import forum.forum.services.JwtTokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Authentication", description = "Handle with USERS authentication")
public class AuthenticationController {

    private AuthenticationManager manager;

    private JwtTokenService jwtTokenService;

    @Log
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public JwtResponseDTO authenticate (@RequestBody @Valid AuthenticateUser data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(authenticationToken);
        return new JwtResponseDTO(jwtTokenService.generateToken((UsersEntity) authentication.getPrincipal()));
    }
}

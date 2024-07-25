package com.Ambalaj.Ambalaj.auth;

import com.Ambalaj.Ambalaj.exception.EntityAlreadyExistsException;
import com.Ambalaj.Ambalaj.exception.ExceptionResponseDTO;
import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.utils.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "Signup a new user", description = "Creates a new user account and sends a confirmation email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ExceptionResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content(schema = @Schema(implementation = EntityAlreadyExistsException.class)))})
    public ResponseEntity<ResponseDTO> signup(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        authService.signup(signupRequestDTO);
        var response = ResponseDTO.builder().message(
                        "Your account has been created successfully. Please check your email to verify and activate your account.")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Logs in a user and returns an access token and user details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ExceptionResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Invalid email or password", content = @Content(schema = @Schema(implementation = NotFoundException.class)))})
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponse = authService.login(loginRequestDTO);
        ResponseDTO response = ResponseDTO.builder().data(loginResponse).build();
        return ResponseEntity.ok(response);
    }
}
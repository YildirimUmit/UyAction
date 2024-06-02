package com.web.backend.controller;

import com.web.backend.dto.*;
import com.web.backend.enums.*;
import com.web.backend.exception.enums.*;
import com.web.backend.exception.exceptions.*;
import com.web.backend.exception.utils.*;
import com.web.backend.model.*;
import com.web.backend.payload.request.LoginRequest;
import com.web.backend.payload.request.SignupRequest;
import com.web.backend.payload.response.*;
import com.web.backend.repository.RoleRepository;
import com.web.backend.repository.UserRepository;
import com.web.backend.security.jwt.JwtProvider;
import com.web.backend.security.services.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.rmi.server.*;
import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Tutorial", description = "Tutorial management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    MaileSendService maileSendService;

    ForgetPassword forgetPassword=new ForgetPassword();

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);


        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping("/forgetpasswordverify")
    public InternalApiResponse<String> getForgetpasswordverify ( @Valid @RequestBody ForgetPasswordVerify forgetPasswordVerify, @RequestHeader("Content-Language") Language language){

        UUID uid=UUID.fromString(forgetPasswordVerify.getUuid());
        boolean isVerify=userRepository.findByEmailAndUid(forgetPasswordVerify.getMaile(),uid);
        if (!isVerify){
            throw new NotFoundException(language, FriendlyMessageCodes.NOT_FOUND_EXCEPTION, "Maile not found for send " + forgetPasswordVerify.getMaile());
        }

       int update= userRepository.updatePasswordByEmailAndUid(passwordEncoder.encode(forgetPasswordVerify.getPassword()),forgetPasswordVerify.getMaile(),uid);

        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload("OK")
                .build();
    }

    @PostMapping("/forgetpassword/{maile}")
    public InternalApiResponse<ForgetPassword> forgetPassword( @Valid @PathVariable String maile, @RequestHeader("Content-Language") Language language){
        Optional<Message> msg = null;
        if (Objects.isNull(maile)) {
            throw new EmptyException(language, FriendlyMessageCodes.NOT_EMPTY, "Maile cannot be empty " + maile);
        }

        boolean isMaile=userRepository.existsByEmail(maile);
        if (!isMaile){
            throw new NotFoundException(language, FriendlyMessageCodes.NOT_FOUND_EXCEPTION, "Maile not found for send " + maile);
        }

        UUID uid=UUID.randomUUID();

        int update=userRepository.updateUidByEmail(uid,maile);

        if (update==1){
            msg=Optional.ofNullable(maileSendService.getSendMaileForgetPassword(maile,uid.toString()));
        }
        if (!msg.isEmpty()){
            forgetPassword.setMaileSendInfoJson(msg.toString());
            forgetPassword.setMaileSendInfoEmpty(false);
        }else{
            forgetPassword.setMaileSendInfoJson("OK");
            forgetPassword.setMaileSendInfoEmpty(true);
        }

        return InternalApiResponse.<ForgetPassword>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(forgetPassword)
                .build();

    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),signUpRequest.getName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "pm":
                        Role modRole = roleRepository.findByName(RoleName.ROLE_PM)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}

package com.example.StockWise.Controller;

import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.Credential;
import com.example.StockWise.Model.dto.ResetDTO;
import com.example.StockWise.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
@Tag(name = "User Management", description = "Manage user accounts securely, including user registration, login, logout, password reset, and account verification. Simplify user-related operations with this API..")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    @Operation(
            summary = "User Registration",
            description = "Register a new user securely. Create a user account to access stock-related services and features.",
            tags = {"User Management"}
    )

    private String registerUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.registerUser(user);
    }

    @PostMapping("/addFund")
    @Operation(
            summary = "Add Funds",
            description = "Add funds to your user account securely. Top up your account balance to participate in stock transactions and investments.",
            tags = {"User Management"}
    )
    private String addFund(@RequestParam String email,double amount) throws NoSuchAlgorithmException {
        return userService.addFund(email,amount);
    }

    @GetMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Log in to your user account securely. Access stock-related features and manage your investments with ease.",
            tags = {"User Management"}
    )
    private String loginUser(@RequestParam String email,String password) throws NoSuchAlgorithmException {
        return userService.loginUser(email,password);
    }

    @GetMapping("/logout")
    @Operation(
            summary = "User Logout",
            description = "Log out of your user account securely. Ensure account security after using stock-related services.",
            tags = {"User Management"}
    )
    private String signoutUser(@RequestParam String email) throws NoSuchAlgorithmException {
        return userService.signoutUser(email);
    }

    @PostMapping("/resetPassword")
    @Operation(
            summary = "Reset Password",
            description = "Reset your account password securely. Regain access to your user account in case of forgotten or compromised passwords.",
            tags = {"User Management"}
    )
    private String resetPassword(@RequestParam String email){
        return userService.resetPassword(email);
    }

    @PostMapping("/verifyOTPRestPassword")
    @Operation(
            summary = "Verify OTP for Password Reset",
            description = "Verify your identity with a one-time password (OTP) during the password reset process. Ensure the security and authenticity of your user account.",
            tags = {"User Management"}
    )
    private String verifyOtp(@RequestBody ResetDTO user) throws NoSuchAlgorithmException {
        return userService.verifyOtp(user);
    }

}
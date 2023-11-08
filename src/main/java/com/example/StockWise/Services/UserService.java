package com.example.StockWise.Services;

import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.Credential;
import com.example.StockWise.Model.dto.ResetDTO;
import com.example.StockWise.Repository.UserRepo;
import com.example.StockWise.Services.Utility.OTPGenerator;
import com.example.StockWise.Services.Utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmailService emailService;

    public String registerUser(User user) throws NoSuchAlgorithmException {

        if (userRepo.existsByuserEmail(user.getUserEmail())) {
            return "Already Register";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(user.getUserPassword());
        user.setUserPassword(hashPass);
        userRepo.save(user);
        return "Register Successfully";
    }

    public String loginUser( String email,String password) throws NoSuchAlgorithmException {
        if (!userRepo.existsByuserEmail(email)) {
            return "Please Create a Account";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(password);

        User user = userRepo.findByUserEmail(email);


        if (hashPass.equals(user.getUserPassword())) {
            user.setStatus("logged in");
            userRepo.save(user);
            return "login successfully";
        } else {
            return "Credential MisMatch";
        }
    }

    public String signoutUser(String email) {
        User user = userRepo.findByUserEmail(email);

        if (user.getStatus().equals("logged in")) {
            user.setStatus("log out");
            userRepo.save(user);
            return "logout successfully";
        } else {
            return "plz login first then click on logout ";
        }

    }

    public String addFund(String email, double amount) {
        User user = userRepo.findByUserEmail(email);
        if (user.getStatus().equals("logged in")){

        user.setFund(user.getFund() + amount);
        userRepo.save(user);
        return amount + "Fund Successfully Added : ";
        }else {
            return "Plz login first and then add Fund";
        }
    }

    public String resetPassword(String email) {
        if (!userRepo.existsByuserEmail(email)) {
            return "User not Register";
        }
        User user = userRepo.findByUserEmail(email);
        String otp = OTPGenerator.generateOTP();

        user.setOtp(otp);
        userRepo.save(user);
        emailService.sendOtpEmail(email, otp);
        return "Otp Sent Successfully";
    }

    public String verifyOtp(ResetDTO user) throws NoSuchAlgorithmException {
        User existinguser = userRepo.findByUserEmail(user.getEmail());
        if (user.getOtp().equals(user.getOtp())) {
            String newHashPass = PasswordEncrypter.hashPasswordWithStaticSecret(user.getNewPass());
            existinguser.setUserPassword(newHashPass);
            userRepo.save(existinguser);
            return "Your Password Reset";
        } else {
            return "Enter Your Valid Otp";
        }
    }
}

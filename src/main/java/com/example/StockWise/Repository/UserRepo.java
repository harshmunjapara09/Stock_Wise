package com.example.StockWise.Repository;

import com.example.StockWise.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByuserEmail(String userEmail);

    User findByUserEmail(String email);
}

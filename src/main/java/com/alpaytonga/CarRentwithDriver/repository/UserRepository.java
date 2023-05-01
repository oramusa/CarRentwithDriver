package com.alpaytonga.CarRentwithDriver.repository;

import com.alpaytonga.CarRentwithDriver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//using for postmapping in usercontroller, if data exist


    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

}

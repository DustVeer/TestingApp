package TestingApp.TestingApp.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import TestingApp.TestingApp.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByFirstName(String firstName);
}



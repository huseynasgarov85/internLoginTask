package com.example.interntask.repo;

import com.example.interntask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UsersRepo extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.username = :username")
    Optional<User> findByEmailOrUsername(@Param("email") String email, @Param("username") String username);

    @Query("select u from User u where u.username = :name")
    Optional<User> findByUsername(@Param("name") String name);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findUserByUserName(String username);

    Optional<User> findUserById(Long id);


}

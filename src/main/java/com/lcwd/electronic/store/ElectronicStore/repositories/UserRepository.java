package com.lcwd.electronic.store.ElectronicStore.repositories;


import com.lcwd.electronic.store.ElectronicStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String keyword);
    User findByEmailAndPassword(String email, String password);
}

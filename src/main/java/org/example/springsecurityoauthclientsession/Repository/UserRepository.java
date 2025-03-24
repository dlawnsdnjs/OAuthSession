package org.example.springsecurityoauthclientsession.Repository;

import org.example.springsecurityoauthclientsession.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}

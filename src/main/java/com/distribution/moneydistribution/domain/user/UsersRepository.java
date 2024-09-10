package com.distribution.moneydistribution.domain.user;

import com.distribution.moneydistribution.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickName);
}

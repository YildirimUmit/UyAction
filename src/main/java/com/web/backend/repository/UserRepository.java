package com.web.backend.repository;

import com.web.backend.model.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    @Query("select (count(u) > 0) from User u where u.email = ?1")
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.uid = ?1 where u.email = ?2")
    int updateUidByEmail(UUID uid,String email);

    @Query("select (count(u) > 0) from User u where u.email = ?1 and u.uid = ?2")
    boolean findByEmailAndUid(String email, UUID uid);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.email = ?2 and u.uid = ?3")
    int updatePasswordByEmailAndUid(String password, String email, UUID uid);


}

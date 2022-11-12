package com.groupC.twitter.repository;

import com.groupC.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
      User getReferenceByUserName(String userName);
}

package com.groupC.twitter.repository;

import com.groupC.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
      User getReferenceByUserName(String userName);
      User getByUserName(String userName);
      List<User> findByIsVerified(Integer isVerified);
}

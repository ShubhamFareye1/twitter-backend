package com.groupC.twitter.repository;

import com.groupC.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
      User getReferenceByUserName(String userName);
      User getByUserName(String userName);
      List<User> findByIsVerified(Integer isVerified);

      @Query("select p from User p where p.name like :key")
      List<User> searchByName(@Param("key") String name);
}

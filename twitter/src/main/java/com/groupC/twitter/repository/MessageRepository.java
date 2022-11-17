package com.groupC.twitter.repository;

import com.groupC.twitter.model.Messages;
import com.groupC.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Messages,Long> {

    @Query("select p from Messages p where (recieverId = :sender and senderId = :reciever) or (recieverId = :reciever and senderId = :sender) order by messageDate")
    public List<Messages> findBySenderIdRecieverId(@Param("sender") Long senderId, @Param("reciever") Long recieverId);

    @Query("SELECT DISTINCT p.senderId FROM Messages p where recieverId=:key")
    public Set<Long> getSenderUsers(@Param("key") Long recieverId);

    @Query("SELECT DISTINCT p.recieverId FROM Messages p where senderId=:key")
    public Set<Long> getRecieverUsers(@Param("key") Long senderId);

}

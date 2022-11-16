package com.groupC.twitter.repository;

import com.groupC.twitter.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Messages,Long> {

    @Query("select p from Messages p where (recieverId = :sender and senderId = :reciever) or (recieverId = :reciever and senderId = :sender) order by messageDate")
    public List<Messages> findBySenderIdRecieverId(@Param("sender") Long senderId, @Param("reciever") Long recieverId);

}

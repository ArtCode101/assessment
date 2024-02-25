package com.kbtg.bootcamp.posttest.userTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {

    List<UserTicket> findByUserId(String userId);

    @Query(value = "SELECT * FROM user_ticket t WHERE t.user_id = ?1 AND t.ticket_id = ?2",nativeQuery = true)
    List<UserTicket> findByUserAndTicket(String user_id, String ticket_id);
}

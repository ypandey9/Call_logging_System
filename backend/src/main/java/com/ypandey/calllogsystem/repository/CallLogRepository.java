package com.ypandey.calllogsystem.repository;

import com.ypandey.calllogsystem.model.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {

    List<CallLog> findByUserUsername(String username);

}

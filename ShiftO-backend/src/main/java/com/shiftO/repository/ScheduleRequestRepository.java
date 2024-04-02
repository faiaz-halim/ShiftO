package com.shiftO.repository;

import com.shiftO.model.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRequestRepository extends JpaRepository<ScheduleRequest, Long> {
    // Additional custom methods if required
}
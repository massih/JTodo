package com.saidin.jtodo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface TaskLogRepository extends JpaRepository<TaskLog, UUID> {
  @Query(value = "SELECT count(*) FROM task_log WHERE jtask_id = ?1 AND date = ?2", nativeQuery = true)
  int getTaskLogsForDay(UUID taskId, LocalDate date);

  @Query(value = "SELECT count(*) FROM task_log WHERE jtask_id = ?1 AND date_part('week', date) = ?2", nativeQuery = true)
  int getTaskLogsForWeek(UUID taskId, int weekNumber);
//  @Query(
//      value = "SELECT * FROM task_log WHERE jtask_id = ?1 ORDER BY date LIMIT 1",
//      nativeQuery = true)
//  TaskLog getLatestTaskLog(UUID taskId);

  @Modifying
  @Transactional
  @Query(
      value =
          "DELETE FROM task_log WHERE id = (SELECT id FROM task_log WHERE jtask_id = ?1 ORDER BY date LIMIT 1)",
      nativeQuery = true)
  void deleteLatestTaskLog(UUID taskId);
}

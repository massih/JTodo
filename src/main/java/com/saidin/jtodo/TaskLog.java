package com.saidin.jtodo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "task_log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class TaskLog {
  @Id
  private UUID id;

  @Column(name = "jtask_id")
  private UUID jTaskId;
  private LocalDate date;


  public TaskLog(String jTaskId) {
    this.id = UUID.randomUUID();
    this.jTaskId = UUID.fromString(jTaskId);
    this.date = LocalDate.now();
  }

  public TaskLog setId() {
    this.id = UUID.randomUUID();
    return this;
  }

  public TaskLog setTaskId(UUID jTaskId) {
    this.jTaskId = jTaskId;
    return this;
  }

  public TaskLog setDate(LocalDate date) {
    this.date = date;
    return this;
  }
}

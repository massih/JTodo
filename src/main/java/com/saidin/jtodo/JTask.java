package com.saidin.jtodo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "jtask")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class JTask {
  @Id
  private UUID id;

  private String description;
  private String repeatType;
  private int repeatNumber;

  public JTask(String description, String repeatType, int repeatNumber) {
    this.id = UUID.randomUUID();
    this.description = description;
    this.repeatType = repeatType;
    this.repeatNumber = repeatNumber;
  }

  public JTask setId() {
    this.id = UUID.randomUUID();
    return this;
  }

  public JTask setDescription(String description) {
    this.description = description;
    return this;
  }

  public JTask setRepeatType(String repeatType) {
    this.repeatType = repeatType;
    return this;
  }

  public JTask setRepeatNumber(int repeatNumber) {
    this.repeatNumber = repeatNumber;
    return this;
  }
}

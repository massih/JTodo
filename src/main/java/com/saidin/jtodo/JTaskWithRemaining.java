package com.saidin.jtodo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@Getter
@ToString
public class JTaskWithRemaining {
  private UUID taskId;
  private String description;
  private String repeatType;
  private int repeatNumber;
  private int remaining;

  public JTaskWithRemaining(UUID taskId, String description, String repeatType, int repeatNumber, int numberOfDone) {
    this.taskId = taskId;
    this.description = description;
    this.repeatType = repeatType;
    this.repeatNumber = repeatNumber;
    this.remaining = repeatNumber - numberOfDone;
  }

  public JTaskWithRemaining setDescription(String description) {
    this.description = description;
    return this;
  }

  public JTaskWithRemaining setRepeatType(String repeatType) {
    this.repeatType = repeatType;
    return this;
  }

  public JTaskWithRemaining setRepeatNumber(int repeatNumber) {
    this.repeatNumber = repeatNumber;
    return this;
  }

  public JTaskWithRemaining setRemaining(int remaining) {
    this.remaining = remaining;
    return this;
  }
}

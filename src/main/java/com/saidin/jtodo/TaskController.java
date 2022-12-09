package com.saidin.jtodo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class TaskController {
  private final Logger logger = LoggerFactory.getLogger(TaskController.class);
  private final TaskRepository taskRepository;
  private final TaskLogRepository logRepository;

  public TaskController(TaskRepository taskRepository, TaskLogRepository logRepository) {
    this.taskRepository = taskRepository;
    this.logRepository = logRepository;
  }

  @GetMapping(path = "/")
  public ModelAndView today() {
    return new ModelAndView("today", getTasksWithRemaining());
  }

  @GetMapping(path = "/all")
  public ModelAndView tasks() {
    return new ModelAndView("task-all", getAllTasksForTemplate());
  }

  @GetMapping(path = "/new")
  public ModelAndView newTask() {
    return new ModelAndView("task-new");
  }

  @GetMapping(path = "/update/{taskId}")
  public ModelAndView updateTask(@PathVariable String taskId) {
    logger.info("Updating Task id {}", taskId);
    JTask task =
        taskRepository.findById(UUID.fromString(taskId)).orElseThrow(RuntimeException::new);
    logger.info("Task to be updated {}", task);
    return new ModelAndView("task-update", Map.of("task", task));
  }

  @PostMapping(path = "/api/new")
  public ModelAndView createTask(JTaskRequest request) {
    logger.info("Creating new task! {}", request);
    final JTask newTask =
        new JTask()
            .setId()
            .setDescription(request.description())
            .setRepeatType(request.repeatType())
            .setRepeatNumber(request.repeatNumber());
    taskRepository.save(newTask);
    return new ModelAndView("redirect:/all", getAllTasksForTemplate());
  }

  @PostMapping(path = "/api/update/{taskId}")
  public ModelAndView updateTask(@PathVariable String taskId, JTaskRequest request) {
    logger.info("Updating Task With {}", request);
    taskRepository.save(
        new JTask(
            UUID.fromString(taskId),
            request.description(),
            request.repeatType(),
            request.repeatNumber()));
    return new ModelAndView("redirect:/all", getAllTasksForTemplate());
  }

  @GetMapping(path = "/api/log/do/{taskId}")
  public ModelAndView doneTask(@PathVariable String taskId) {
    logger.info("Doing Task {}", taskId);
    logRepository.save(new TaskLog(taskId));
    return new ModelAndView("redirect:/", getTasksWithRemaining());
  }

  @GetMapping(path = "/api/log/undo/{taskId}")
  public ModelAndView undoTask(@PathVariable String taskId) {
    logger.info("UnDoing Task {}", taskId);
    logRepository.deleteLatestTaskLog(UUID.fromString(taskId));
    return new ModelAndView("redirect:/", getTasksWithRemaining());
  }

  private Map<String, Iterable<JTask>> getAllTasksForTemplate() {
    return Map.of("tasks", taskRepository.findAll());
  }

  private Map<String, List<JTaskWithRemaining>> getTasksWithRemaining() {
    List<JTask> allTasks = taskRepository.findAll();
    List<JTaskWithRemaining> tasks =
        allTasks.stream()
            .map(
                jTask -> {
                  return switch (jTask.getRepeatType()) {
                    case "Daily" -> getRemainingDaily(jTask);
                    case "Weekly" -> getRemainingWeekly(jTask);
                    case "Monthly" -> getRemainingMonthly(jTask);
                    case "Yearly" -> getRemainingWeekly(jTask);
                    default -> null;
                  };
                })
            .toList();
    return Map.of("tasks", tasks);
  }

  private JTaskWithRemaining getRemainingDaily(JTask task) {
    int numberOfDone = logRepository.getTaskLogsForDay(task.getId(), LocalDate.now());
    return new JTaskWithRemaining(
        task.getId(), task.getDescription(), task.getRepeatType(), task.getRepeatNumber(), numberOfDone);
  }

  private JTaskWithRemaining getRemainingWeekly(JTask task) {
    int weekOfYear = LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    int numberOfDone = logRepository.getTaskLogsForWeek(task.getId(), weekOfYear);
    return new JTaskWithRemaining(
            task.getId(), task.getDescription(), task.getRepeatType(), task.getRepeatNumber(), numberOfDone);
  }

  private JTaskWithRemaining getRemainingMonthly(JTask jTask) {
    return null;
  }
}

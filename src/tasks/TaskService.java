package tasks;

import exception.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<Integer, Task> tasksMap = new HashMap<>();
    private static ArrayList<Task> removedTasks = new ArrayList<>();

    public static void add(Task task) {
        this.tasksMap.put(task.getId(), task);
    }


    public void remove(Integer taskId) throws TaskNotFoundException {
        if (this.tasksMap.containsKey(taskId)) {
            removedTasks.add(tasksMap.remove(taskId));
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

    public Collection<Task> getAllByDate(LocalDate date) {
        Collection<Task> taskByDay = new ArrayList<>();
        Collection<Task> allTasks = tasksMap.values();
        for (Task task : allTasks) {
            LocalDateTime currentDateTime = task.getDateTime();
            if (currentDateTime.toLocalDate().equals(date)) {
                taskByDay.add(task);
                break;
            }
            LocalDateTime taskNextTime = currentDateTime;
            do {
                taskNextTime = task.getTaskNextTime(taskNextTime);
                if (taskNextTime == null) {
                    break;
                }
                if (taskNextTime.toLocalDate().equals(date)) {
                    taskByDay.add(task);
                    break;
                }

            }
            while (taskNextTime.toLocalDate().isBefore(date));
        }
        return taskByDay;

    }
}
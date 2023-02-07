package tasks;

import exception.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<Integer, Tasks> tasksMap = new HashMap<>();
    private static ArrayList<Tasks> removedTasks = new ArrayList<>();

    public void add(Tasks tasks) {
        this.tasksMap.put(tasks.getId(), tasks);
    }

    public void remove(Integer taskId) throws TaskNotFoundException {
        if (this.tasksMap.containsKey(taskId)) {
            removedTasks.add(tasksMap.get(taskId));
            this.tasksMap.remove(taskId);
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

    public Collection<Tasks> getAllByDate(LocalDate date) {
        Collection<Tasks> tasksByDay = new ArrayList<>();
        Collection<Tasks> allTasks = tasksMap.values();
        for (Tasks tasks : allTasks) {
            LocalDateTime currentDateTime = tasks.getDateTime();
            if (currentDateTime.toLocalDate().equals(date)) {
                tasksByDay.add(tasks);
                break;
            }
            LocalDateTime taskNextTime = currentDateTime;
            do {
                taskNextTime = tasks.getTaskNextTime(taskNextTime);
                if (taskNextTime == null) {
                    break;
                }
                if (taskNextTime.toLocalDate().equals(date)) {
                    tasksByDay.add(tasks);
                    break;
                }

            }
            while (taskNextTime.toLocalDate().isBefore(date));
        }
        return tasksByDay;

    }
}
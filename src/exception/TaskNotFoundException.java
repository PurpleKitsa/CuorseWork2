package exception;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(Integer taskId) {
        super(taskId + " задача не найдена!");
    }

}

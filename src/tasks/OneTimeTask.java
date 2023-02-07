package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class OneTimeTask extends Tasks {

    public OneTimeTask(String heading, String description, LocalDateTime dateTime, Type type) throws IncorrectArgumentException {
        super(heading, description, dateTime, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return null;
    }
}
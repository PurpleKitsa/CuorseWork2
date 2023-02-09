package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {

    public MonthlyTask(String heading, String description, LocalDateTime dateTime, Type type) throws IncorrectArgumentException {
        super(heading, description, dateTime, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return dateTime.plusMonths(1);
    }

    @Override
    public LocalDate getTaskNextTime(LocalDate localDate) {
        return localDate.plusMonths(1);
    }
}
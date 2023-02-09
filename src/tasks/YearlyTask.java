package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public  class YearlyTask extends Task {

    public YearlyTask(String heading, String description, LocalDateTime dateTime, Type type) throws IncorrectArgumentException {
        super(heading, description, dateTime, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return dateTime.plusYears(1);
    }

    @Override
    public LocalDate getTaskNextTime(LocalDate localDate) {
        return localDate.plusYears(1);
    }
}
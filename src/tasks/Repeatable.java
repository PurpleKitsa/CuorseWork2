package tasks;

import java.time.LocalDate;

public interface Repeatable {

    LocalDate getTaskNextTime(LocalDate localDate);
}

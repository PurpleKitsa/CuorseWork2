package tasks;

import java.time.LocalDateTime;

public interface Repeatable {
    LocalDateTime getTaskNextTime(LocalDateTime dateTime);
}

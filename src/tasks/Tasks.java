package tasks;

import exception.IncorrectArgumentException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Tasks implements Repeatable {

    private String heading;
    private String description;
    private int id;
    private static int idGenerator;
    private LocalDateTime dateTime;
    private Type type;

    public Tasks(String heading, String description, LocalDateTime dateTime, Type type) throws IncorrectArgumentException {
        setHeading(heading);
        setDescription(description);
        if (dateTime != null) {
            this.dateTime = dateTime;
        } else {
            throw new IncorrectArgumentException("дата или время");
        }
        if (type != null) {
            this.type = type;
        } else {
            throw new IncorrectArgumentException("тип задачи");
        }
        this.id = idGenerator;
        idGenerator++;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) throws IncorrectArgumentException {
        if (heading != null) {
            this.heading = heading;
        } else {
            throw new IncorrectArgumentException("заголовок задачи");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description != null) {
            this.description = description;
        } else {
            throw new IncorrectArgumentException("содержание задачи");
        }
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return id == tasks.id && heading.equals(tasks.heading) && description.equals(tasks.description) && dateTime.equals(tasks.dateTime) && type == tasks.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, description, id, dateTime, type);
    }

    @Override
    public String toString() {
        return "Задача: " + "Заголовок - " + heading + " Описание - " + description + " id - " + id + " Время и дата - " + dateTime + " Тип - " + type;
    }
}
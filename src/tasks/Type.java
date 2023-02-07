package tasks;

public enum Type {
    WORK("личная"),
    PERSONAL("рабочая");
    private final String typeTask;


    Type(String typeTask) {
        this.typeTask = typeTask;
    }

    public String getTypeTask() {
        return typeTask;
    }
}
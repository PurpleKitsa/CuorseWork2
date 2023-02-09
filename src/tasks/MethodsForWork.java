package tasks;

import exception.IncorrectArgumentException;
import exception.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MethodsForWork {
    private static final Pattern DATE_TAME_PATTERN = Pattern.compile("\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("DD.MM.YYYY HH:MM");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("DD.MM.YYYY");
    private static final TaskService taskService = new TaskService();

    public static void inputTask(Scanner scanner) throws IncorrectArgumentException {

        scanner.useDelimiter("\n");
        System.out.println("Введите название задачи: ");
        String title = scanner.next();
        System.out.println("Введите описание задачи: ");
        String description = scanner.next();
        System.out.println("Введите тип задачи (1 - личная, 2 - рабочая): ");
        Type type = null;
        int taskTypeChoice = scanner.nextInt();
        switch (taskTypeChoice) {
            case 1:
                type = Type.PERSONAL;
            case 2:
                type = Type.WORK;
            default:
                System.out.println("Тип задачи введён неверно!");

        }
        System.out.println("Введите дату и время в формате DD.MM.YYYY HH:MM");
        LocalDateTime taskTime = null;
        if (scanner.hasNext(DATE_TAME_PATTERN)) {
            String dateTame = scanner.next(DATE_TAME_PATTERN);
            taskTime = LocalDateTime.parse(dateTame, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Введите дату и время в формате DD.MM.yyyy HH:MM");
        }
        if (taskTime == null) {

            System.out.println("Введите дату и время в формате DD.MM.YYYY HH:MM");
        }
        System.out.println("Введите повторяемость задачи (1. однократно, 2. каждый день, 3. каждую неделю, 4. каждый месяц, 5. каждый год)");
        Task task = null;

        if (scanner.hasNextInt()) {
            int repeatability = scanner.nextInt();
            switch (repeatability) {
                case 1:
                    task = new OneTimeTask(title,description,taskTime,type);
                    break;
                case 2:
                    task = new DailyTask(title, description, taskTime, type);
                    break;
                case 3:
                    task = new WeeklyTask(title, description, taskTime, type);
                    break;
                case 4:
                    task = new MonthlyTask(title, description, taskTime, type);
                    break;
                case 5:
                    task = new YearlyTask(title, description, taskTime, type);
                    break;
                default:
                    System.out.println("Повторяемость введена неверно!");
            }
        }
        taskService.add(task);
        System.out.println("Задача добавлена");
    }

    public static void printMenu() {
        System.out.println("1. Добавить задачу \n 2. Удалить задачу \n 3. Получить задачу на указанный день \n 0.Выход");
    }

    public static void removeTask(Scanner scanner) {
        System.out.println("Введите id задачи для удаления");
        int id = scanner.nextInt();
        try {
            taskService.remove(id);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printTaskByDay(Scanner scanner) {

        System.out.println("Введите дату в формате DD.MM.YYYY");
        if (scanner.hasNext(DATE_PATTERN)) {
            String date = scanner.next(DATE_PATTERN);
            LocalDate inputDate = LocalDate.parse(date, DATE_FORMATTER);
            Collection<Task> taskByDay = taskService.getAllByDate(inputDate);
            for (Task task : taskByDay) {
                System.out.println(task);
            }
        } else {


            System.out.println("Введите дату в формате DD.MM.YYYY");

        }
    }
}
package ru.netology.todos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {

    @Test
    public void simpleTaskMatchesTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        Assertions.assertTrue(task.matches("Позвонить"));
    }

    @Test
    public void simpleTaskDoesNotMatchOtherText() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        Assertions.assertFalse(task.matches("Купить молоко"));
    }

    @Test
    public void epicMatchesAnySubtask() {
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertTrue(epic.matches("Молоко"));
        Assertions.assertTrue(epic.matches("Яйца"));
        Assertions.assertTrue(epic.matches("Хлеб"));
    }

    @Test
    public void epicDoesNotMatchUnknown() {
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertFalse(epic.matches("Сахар"));
    }

    @Test
    public void meetingMatchesTopic() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Assertions.assertTrue(meeting.matches("Выкатка"));
    }

    @Test
    public void meetingMatchesProject() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Assertions.assertTrue(meeting.matches("НетоБанка"));
    }

    @Test
    public void meetingDoesNotMatchStartOrOther() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Assertions.assertFalse(meeting.matches("вторник"));
    }
}
package ru.netology.todos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsSimpleTaskByTitle() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        todos.add(task);
        todos.add(new Epic(2, new String[]{"Молоко", "Хлеб"}));

        Task[] expected = {task};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsEpicBySubtask() {
        Todos todos = new Todos();
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб"});
        todos.add(epic);
        todos.add(new SimpleTask(1, "Позвонить родителям"));

        Task[] expected = {epic};
        Task[] actual = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsMeetingByProject() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(3, "Выкатка", "Приложение НетоБанка", "вторник");
        todos.add(meeting);
        todos.add(new SimpleTask(1, "Позвонить родителям"));

        Task[] expected = {meeting};
        Task[] actual = todos.search("НетоБанка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsMultipleTasks() {
        Todos todos = new Todos();
        SimpleTask simple = new SimpleTask(1, "Купить молоко");
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб"});
        Meeting meeting = new Meeting(3, "Обсудить молоко", "Проект", "среда");
        todos.add(simple);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simple, epic, meeting};
        Task[] actual = todos.search("молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsEmptyWhenNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить родителям"));
        todos.add(new Epic(2, new String[]{"Молоко", "Хлеб"}));

        Task[] expected = new Task[0];
        Task[] actual = todos.search("Сахар");
        Assertions.assertArrayEquals(expected, actual);
    }
}
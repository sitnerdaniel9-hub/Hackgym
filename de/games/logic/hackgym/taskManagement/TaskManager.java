package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.GameController;
import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.util.ArrayList;

/**
 * Diese Klasse ist für die Verwaltung aller Aufgaben zuständig.*/
public class TaskManager {
    private final Difficulty DIFFICULTY;
    private Task currentTask;
    private ArrayList<Task> tasks;
    private static TaskManager taskManager;
    private final GameController gameController;
    private TaskObserver taskObserver;

    private TaskManager(Difficulty difficulty, TaskObserver taskObserver) {
        gameController = GameController.getGameController();
        this.DIFFICULTY = difficulty;
        tasks = createTaskArray();
        addObserver(taskObserver);
        selectTask();
    }

    private ArrayList<Task> createTaskArray() {
        int numberOfTasks = 50;
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i++) {
            switch (DIFFICULTY) {
                case EASY -> {
                    taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.SORT));
                    taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.CLICK_NUMBER));

                }
                case MEDIUM -> {
                        taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.ADD_NUMBER_SEQUENCE));
                        taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.REVERSE_WORD));
                        taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.CLICK_NUMBER));
                        taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.PATTERN_ERROR));
                }

                case HARD -> {
                    taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.ADD_NUMBER_SEQUENCE));
                    taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.RHYTHM_BUTTON));
                }
            }
            taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.CALCULATION));
            taskArrayList.add(Task.createTask(DIFFICULTY, TaskType.COLOR_BUTTON));

        }


        return taskArrayList;
    }

    /**Erzeugt einen neuen Taskmanager, falls keiner Vorhanden
     * @param difficulty die Schwierigkeit der Aufgaben
     * @param taskObserver der Beobachter für die Aufgaben
     * @return der TaskManager*/
    public static TaskManager getTaskManager(Difficulty difficulty, TaskObserver taskObserver) {
        if (taskManager == null) {
            taskManager = new TaskManager(difficulty, taskObserver);
        }

        return taskManager;
    }

    /**Wählt eine Aufgabe aus.
     * @return die neue Aufgabe*/
    public Task selectTask() {
        if (tasks.isEmpty()) {
            tasks = createTaskArray();
        }
        currentTask = tasks.getFirst();
        notifyObserver();
        return currentTask;
    }

    /**Löscht die aktuelle Aufgabe aus der Liste*/
    public void removeTask() {
        tasks.remove(currentTask);
    }

    /**Fordert das Ändern des Scores an.
     * @param points die Punkte die zum Punktestand addiert werden sollen*/
    public void changeScore(int points) {
        gameController.setScore(points);
    }

    /**Löscht alle Tasks*/
    public void clearTasks() {
        taskManager = null;
    }

    /**Veranlasst die Prüfung einer Aufgabe.
     * @param solution die Lösung des Spielers*/
    public void checkSolution(String solution) {
        boolean bool = currentTask.checkSolution(solution);
        if (bool) {
            changeScore(currentTask.getPoints());
        } else {
            changeScore(calcNegativePoints(currentTask.getPoints()));
        }
        removeTask();
    }

    /**Überspringt die aktuelle Aufgabe*/
    public void skipTask() {
        changeScore(calcNegativePoints(currentTask.getPoints()));
        removeTask();
        selectTask();
    }

    private int calcNegativePoints(double p) {
        return (int)((-p)/2.0);
    }

    /**Fügt dem TaskManager einen Beobachter hinzu.
     * @param taskObserver der Beobachter*/
    public void addObserver(TaskObserver taskObserver) {
        this.taskObserver = taskObserver;
    }

    /**Informiert den Beobachter, falls sich die aktuelle Aufgabe geändert hat*/
    public void notifyObserver() {
        taskObserver.onTaskChanged(currentTask);
    }
}

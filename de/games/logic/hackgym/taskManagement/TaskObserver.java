package de.games.logic.hackgym.taskManagement;

/**
*Alle Klassen, die den Taskmanager beobachten möchten, müssen dieses Interface implementieren*/
public interface TaskObserver {
    void onTaskChanged(Task newTask);
}

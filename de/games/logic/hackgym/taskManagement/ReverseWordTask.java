package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.util.HashSet;
import java.util.Random;

/**
 * Diese Klasse gibt den Rahmen der ReverseWordTask vor.*/
public class ReverseWordTask extends Task {
    private final String[] SENTENCE_LIBRARY = {
            "Bleib neugierig",
            "Denke positiv",
            "Folge deinem Herzen",
            "Träume groß",
            "Zeit für Abenteuer",
            "Mut tut gut",
            "Vertraue dir selbst",
            "Du schaffst das",
            "Alles hat seinen Grund",
            "Carpe diem",
            "Genieße den Moment",
            "Glaube an dich",
            "Sei du selbst",
            "Beginne jetzt",
            "Finde deinen Weg",
            "Ein Schritt nach dem anderen",
            "Bleib stark",
            "Lebe deinen Traum",
            "Mach weiter",
            "Sei dankbar",
            "Mut",
            "Glück",
            "Hoffnung",
            "Liebe",
            "Frieden",
            "Freude",
            "Stärke",
            "Kraft",
            "Herz",
            "Ziel",
            "Weg",
            "Traum",
            "Mutig",
            "Freiheit",
            "Licht",
            "Zeit",
            "Wissen",
            "Leben",
            "Geduld",
            "Ruhe",
            "Hallo"
    };
    private String sentence, reversedSentence;
    private static HashSet<Integer> usedWords;

    /**Erzeugt eine ReverseWordTask*/
    public ReverseWordTask() {
        setDifficulty(Difficulty.MEDIUM);
        setPoints(100);
        setType(TaskType.REVERSE_WORD);
        setUpTask();
    }

    private void setUpTask() {
        Random rand = new Random();
        if (usedWords == null) {
            usedWords = new HashSet<>();
        }
        if (usedWords.size() == SENTENCE_LIBRARY.length) {
            usedWords.clear();
        }
        int senIndex = rand.nextInt(SENTENCE_LIBRARY.length);
        while (usedWords.contains(senIndex)) {
            senIndex = rand.nextInt(SENTENCE_LIBRARY.length);
        }
        sentence = SENTENCE_LIBRARY[senIndex];
        usedWords.add(senIndex);
        reversedSentence = reverseSentence();
    }

    private String reverseSentence() {
        StringBuilder ergebnis = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            ergebnis.append(sentence.charAt(sentence.length() - i - 1));
        }

        return ergebnis.toString();
    }

    /**Gibt den umgedrehten Satz zurück
     * @return der umgedrehte Satz, den der Spieler sieht.*/
    public String getReversedSentence() {
        return reversedSentence;
    }

    /**Prüft die Lösung des Spielers.
     * @return Gibt zurück, ob die Lösung richtig oder falsch war.*/
    @Override
    public boolean checkSolution(String solution) {
        return solution.equals(sentence);
    }
}

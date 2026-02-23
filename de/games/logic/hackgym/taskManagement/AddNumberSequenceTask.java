package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Rahmen für die AddNumberSequenceTask
 */
public class AddNumberSequenceTask extends Task {
    /**
     * Bibliothek von Zahlenfolgen für den Schwierigkeitsgrad MEDIUM.
     * Jede Folge besteht aus einer festen Regel (arithmetisch, alternierend,
     * multiplikativ etc.).
     */
    private final int[][] SEQUENCE_LIBRARY_MEDIUM = {
        // +2 konstant
        {2, 4, 6, 8, 10, 12, 14, 16, 18, 20},

        // +3 konstant
        {1, 4, 7, 10, 13, 16, 19, 22, 25, 28},

        // +5 konstant
        {3, 8, 13, 18, 23, 28, 33, 38, 43, 48},

        // -2 konstant
        {20, 18, 16, 14, 12, 10, 8, 6, 4, 2},

        // +2, +3 Wechsel
        {5, 7, 10, 12, 15, 17, 20, 22, 25, 27},

            // +4, -1 Wechsel
        {2, 6, 5, 9, 8, 12, 11, 15, 14, 18},

        // *2, *2 … aber klein gehalten
        {1, 2, 4, 8, 16, 32, 64, 128, 256, 512},

        // Verdoppeln +1
        {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023},

        // +1, +2, +3, +4, ...
        {1, 2, 4, 7, 11, 16, 22, 29, 37, 46},

        // Fibonacci-artig (vereinfachte)
        {1, 2, 3, 5, 8, 13, 21, 34, 55, 89},

        // +6, -2, +6, -2 …
        {10, 16, 14, 20, 18, 24, 22, 28, 26, 32},

        // +10, -5, +10, -5 …
        {5, 15, 10, 20, 15, 25, 20, 30, 25, 35},

        // Halbieren +2
        {40, 22, 13, 8, 6, 5, 4, 3, 3, 2},

        // Alternierend: *2 und /2
        {4, 8, 4, 8, 4, 8, 4, 8, 4, 8},

        // +3, *2, +3, *2 …
        {2, 5, 10, 13, 26, 29, 58, 61, 122, 125},

        // -3, *2, -3, *2 …
        {20, 17, 34, 31, 62, 59, 118, 115, 230, 227},

        // +4, +8, +4, +8 …
        {5, 9, 17, 21, 29, 33, 41, 45, 53, 57}
    };

    /**
     * Bibliothek von Zahlenfolgen für den Schwierigkeitsgrad HARD.
     * Enthält komplexere mathematische Muster wie Primzahlen,
     * Quadratzahlen oder rekursive Folgen.
     */
    public static final int[][] SEQUENCE_LIBRARY_HARD = {
            // Quadratzahlen
            {1, 4, 9, 16, 25, 36, 49, 64, 81, 100},

            // Primzahlen
            {2, 3, 5, 7, 11, 13, 17, 19, 23, 29},

            // Zunehmende Differenzen: +3, +5, +7, ...
            {2, 5, 10, 17, 26, 37, 50, 65, 82, 101},

            // Zunehmende Differenzen: +4, +6, +8, ...
            {3, 7, 13, 21, 31, 43, 57, 73, 91, 111},

            // Abwechselnd +2, *2
            {1, 3, 6, 8, 16, 18, 36, 38, 76, 78},

            // Abwechselnd +3, *2
            {2, 5, 10, 13, 26, 29, 58, 61, 122, 125},

            // Fallende Folge mit wachsenden negativen Differenzen (-5, -8, -11, ...)
            {50, 45, 37, 26, 12, -5, -25, -48, -74, -103},

            // Zwei ineinander verschachtelte arithm. Folgen (odd/even Positionen)
            {1, 4, 3, 7, 5, 10, 7, 13, 9, 16},

            // *3 - 1
            {1, 2, 5, 14, 41, 122, 365, 1094, 3281, 9842},

            // Fibonacci + 1 (a_n = a_{n-1} + a_{n-2} + 1)
            {1, 2, 4, 7, 12, 20, 33, 54, 88, 143},

            // Quadratzahlen - 1
            {0, 3, 8, 15, 24, 35, 48, 63, 80, 99},

            // n(n+1) (doppelte Dreieckszahlen)
            {2, 6, 12, 20, 30, 42, 56, 72, 90, 110}
    };

    private static HashSet<Integer> usedMediumSequences;
    private static HashSet<Integer> usedHardSequences;
    private int[] sequence;
    private int solution;

    /**
     * Erstellt eine neue AddNumberSequenceTask mit dem angegebenen Schwierigkeitsgrad.
     * Initialisiert Aufgabentyp, Punktzahl sowie die Zahlenfolge und deren Lösung.
     *
     * @param difficulty der Schwierigkeitsgrad der Aufgabe
     */
    public AddNumberSequenceTask(Difficulty difficulty) {
        setDifficulty(difficulty);
        setType(TaskType.ADD_NUMBER_SEQUENCE);
        setPoints(200);
        setUpTask(difficulty);
    }

    private void setUpTask(Difficulty difficulty) {

        switch (difficulty) {

            case MEDIUM -> {
                setUpSequence(usedMediumSequences, SEQUENCE_LIBRARY_MEDIUM);
            }
            case HARD -> {
                setUpSequence(usedHardSequences, SEQUENCE_LIBRARY_HARD);
            }
        }


    }

    private void setUpSequence(HashSet<Integer> usedSequences, int[][] sequenceLibrary) {
        Random rand = new Random();
        if (usedSequences == null) {
            usedSequences = new HashSet<>();
        }
        if (usedSequences.size() == sequenceLibrary.length) {
            usedSequences.clear();
        }
        int seqIndex = rand.nextInt(sequenceLibrary.length);
        while (usedSequences.contains(seqIndex)) {
            seqIndex = rand.nextInt(sequenceLibrary.length);
        }
        sequence = sequenceLibrary[seqIndex];
        solution = sequence[sequence.length -1];
        usedSequences.add(seqIndex);

    }

    /**
     * Liefert die darzustellende Zahlenfolge ohne das letzte Element.
     * Das fehlende letzte Element stellt die gesuchte Lösung dar.
     *
     * @return ein Array mit der unvollständigen Zahlenfolge
     */
    public int[] getSequence() {
        return Arrays.copyOfRange(sequence,0,sequence.length - 1);
    }

    /**
     * Prüft, ob die übergebene Lösung korrekt ist.
     * Die Lösung gilt als korrekt, wenn sie eine Ganzzahl ist und dem
     * letzten Element der zugrunde liegenden Zahlenfolge entspricht.
     *
     * @param solution die vom Benutzer eingegebene Lösung als String
     * @return {@code true}, wenn die Lösung korrekt ist, sonst {@code false}
     */
    @Override
    public boolean checkSolution(String solution) {
        try {
            int value = Integer.parseInt(solution);
            return value == this.solution;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * Liefert die Zahlenfolge in String-Form zur Anzeige.
     * Die einzelnen Zahlen sind durch Leerzeichen getrennt.
     *
     * @return die darzustellende Zahlenfolge als String
     */
    public String getExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j : getSequence()) {
            stringBuilder.append(j).append(" ");
        }
        return stringBuilder.toString();
    }
}

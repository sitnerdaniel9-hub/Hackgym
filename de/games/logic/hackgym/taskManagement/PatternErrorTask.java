package de.games.logic.hackgym.taskManagement;

import de.games.logic.hackgym.enums.Difficulty;
import de.games.logic.hackgym.enums.TaskType;
import java.util.HashSet;
import java.util.Random;

/**
 * Diese Klasse gibt den Rahmen der PatternErrorTask vor*/
public class PatternErrorTask extends Task {
    private final String[] PATTERN_LIBRARY = {
            "aaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaa",
            "oooooooooooooooooooo0ooooooooooooo",
            "#########################+########",
            "aaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaa",
            "aaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaa",
            "aaaaaaaaaaaaaaaaaaaadaaaaaaaaaaaaa",
            "bbbbbbbbbbbbbbbbbbbbAbbbbbbbbbbbbb",
            "bbbbbbbbbbbbbbbbbbb!bbbbbbbbbbbbbb",
            "cccccccccccccccccccc#ccccccccccccc",
            "cccccccccccccccccccc*ccccccccccccc",
            "dddddddddddddddddddd+ddddddddddddd",
            "ddddddddddddddddddddxddddddddddddd",
            "eeeeeeeeeeeeeeeeeeee0eeeeeeeeeeeee",
            "eeeeeeeeeeeeeeeeeeee@eeeeeeeeeeeee",
            "ffffffffffffffffffff$fffffffffffff",
            "ffffffffffffffffffff%fffffffffffff",
            "gggggggggggggggggggg^ggggggggggggg",
            "gggggggggggggggggggg*ggggggggggggg",
            "hhhhhhhhhhhhhhhhhhhh#hhhhhhhhhhhhh",
            "hhhhhhhhhhhhhhhhhhhh1hhhhhhhhhhhhh",
            "iiiiiiiiiiiiiiiiiiii?iiiiiiiiiiiii",
            "iiiiiiiiiiiiiiiiiiii!iiiiiiiiiiiii",
            "jjjjjjjjjjjjjjjjjjjjJjjjjjjjjjjjjj",
            "jjjjjjjjjjjjjjjjjjjj+jjjjjjjjjjjjj",
            "kkkkkkkkkkkkkkkkkkkk0kkkkkkkkkkkkk",
            "kkkkkkkkkkkkkkkkkkkk*kkkkkkkkkkkkk",
            "llllllllllllllllllll-lllllllllllll",
            "llllllllllllllllllll+lllllllllllll",
            "mmmmmmmmmmmmmmmmmmmmMmmmmmmmmmmmmm",
            "mmmmmmmmmmmmmmmmmmmm#mmmmmmmmmmmmm",
            "nnnnnnnnnnnnnnnnnnnn!nnnnnnnnnnnnn",
            "nnnnnnnnnnnnnnnnnnnnoonnnnnnnnnnnn",
            "oooooooooooooooooooo0ooooooooooooo",
            "oooooooooooooooooooo+ooooooooooooo",
            "ppppppppppppppppppppPppppppppppppp",
            "pppppppppppppppppppp!ppppppppppppp",
            "qqqqqqqqqqqqqqqqqqqq#qqqqqqqqqqqqq",
            "qqqqqqqqqqqqqqqqqqqq?qqqqqqqqqqqqq",
            "rrrrrrrrrrrrrrrrrrrrRrrrrrrrrrrrrr",
            "rrrrrrrrrrrrrrrrrrrr#rrrrrrrrrrrrr",
            "ssssssssssssssssssss$sssssssssssss",
            "ssssssssssssssssssss+sssssssssssss",
            "ttttttttttttttttttttTttttttttttttt",
            "tttttttttttttttttttt0ttttttttttttt",
            "uuuuuuuuuuuuuuuuuuuuUuuuuuuuuuuuuu",
            "uuuuuuuuuuuuuuuuuuuu+uuuuuuuuuuuuu",
            "vvvvvvvvvvvvvvvvvvvvVvvvvvvvvvvvvv",
            "vvvvvvvvvvvvvvvvvvvv#vvvvvvvvvvvvv",
            "wwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwww",
            "wwwwwwwwwwwwwwwwwwww?wwwwwwwwwwwww",
            "xxxxxxxxxxxxxxxxxxxxXxxxxxxxxxxxxx",
            "xxxxxxxxxxxxxxxxxxxx+xxxxxxxxxxxxx",
            "yyyyyyyyyyyyyyyyyyyyYyyyyyyyyyyyyy",
            "yyyyyyyyyyyyyyyyyyyy#yyyyyyyyyyyyy",
            "zzzzzzzzzzzzzzzzzzzzZzzzzzzzzzzzzz",
            "zzzzzzzzzzzzzzzzzzzz*zzzzzzzzzzzzz"

    };
    private String pattern;
    private String solution;
    private static HashSet<Integer> usedPatterns;

    /**Erzeugt eine Patternerror Task*/
    public PatternErrorTask() {
        setDifficulty(Difficulty.MEDIUM);
        setPoints(25);
        setType(TaskType.PATTERN_ERROR);
        setUpTask();
    }

    private void setUpTask() {
        Random rand = new Random();
        if (usedPatterns == null) {
            usedPatterns = new HashSet<>();
        }
        if (usedPatterns.size() == PATTERN_LIBRARY.length) {
            usedPatterns.clear();
        }
        int patIndex = rand.nextInt(PATTERN_LIBRARY.length);
        while (usedPatterns.contains(patIndex)) {
            patIndex = rand.nextInt(PATTERN_LIBRARY.length);
        }
        pattern = PATTERN_LIBRARY[patIndex];
        usedPatterns.add(patIndex);
        solution = findSolution(pattern);
    }

    private String findSolution(String pattern) {
        char start = pattern.charAt(0);
        int countStart = 1;
        char error = ' ';

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == start) {
                //start wurde mehrfach gesehen
                countStart++;
            } else {
                //was anderes wurde gesehen
                error = pattern.charAt(i);
            }
        }

        if (countStart == 1) {
            //wenn start nur einmal gesehen wurde ist start selbst der fehler
            error = start;
        }
        return Character.toString(error);
    }

    /**Gibt das Muster zurück.
     * @return das Muster, dass der Spieler sieht.*/
    public String getPattern() {
        return pattern;
    }

    /**Prüft die Lösung des Spielers.
     * @param solution die Lösung des Spielers
     * @return Prüft, ob die Lösung richtig oder falsch war.
     */
    @Override
    public boolean checkSolution(String solution) {
        return solution.equals(this.solution);
    }
}

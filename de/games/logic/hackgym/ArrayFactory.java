package de.games.logic.hackgym;

import java.util.Arrays;
import java.util.Random;

/**
 * Diese Klasse generiert Zahlen und Stringarrays für verschiedene Aufgaben.*/
public class ArrayFactory {
    private static final String[] COLOR_LIBRARY = {"Blau", "Grün", "Rot", "Lila", "Gelb"};

    /**Generiert einen zufälligen Array ohne Duplikate in einem bestimmten Bereich.
     * @param length Länge des Arrays
     * @param rangeMin Anfang des Bereichs, wo die Werte liegen (inklusive rangeMin).
     * @param rangeMax Ende des Bereichs, wo die Werte liegen (exklusive rangeMax).
     * @return der Array*/
    public static int[] generateArrayWithoutDuplicates(int length, int rangeMin, int rangeMax) {
        Random rand = new Random();
        int range = rangeMax - rangeMin;

        if (range < length) {
            throw new IllegalArgumentException("Wertebereich zu klein für eindeutige Werte.");
        }

        int[] pool = new int[range];
        for (int i = 0; i < range; i++) {
            pool[i] = rangeMin + i;
        }

        for (int i = 0; i < length; i++) {
            int j = i + rand.nextInt(range - i);
            int tmp = pool[i];
            pool[i] = pool[j];
            pool[j] = tmp;
        }

        return Arrays.copyOf(pool, length);
    }

    /**Generiert einen zufälligen Array in einem bestimmten Bereich
     * @param length Länge des Arrays
     * @param rangeMin Anfang des Bereichs, wo die Werte liegen (inklusive rangeMin).
     * @param rangeMax Ende des Bereichs, wo die Werte liegen (exklusive rangeMax)
     * @return der Array*/
    public static int[] generateArray(int length, int rangeMin, int rangeMax) {
        Random rand = new Random();
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = rand.nextInt(rangeMin, rangeMax);
        }

        return array;
    }

    /**Generiert einen zufälligen Array aus Farbstrings ohne Duplikate.
     * @return der Array*/
    public static String[] generateColorArrayWithoutDuplicates() {
        String[] result = Arrays.copyOf(COLOR_LIBRARY, COLOR_LIBRARY.length);
        Random rand = new Random();

        for (int i = 0; i < result.length; i++) {
            int j = i + rand.nextInt(result.length - i); // zufälliger Index zwischen i und Ende
            String temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }

        return Arrays.copyOf(result, COLOR_LIBRARY.length);
    }
}

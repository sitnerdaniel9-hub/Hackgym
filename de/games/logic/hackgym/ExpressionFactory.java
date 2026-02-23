package de.games.logic.hackgym;

import de.games.logic.hackgym.enums.Difficulty;
import java.util.Random;

/**
 * Diese Klasse generiert mathematische Ausdrücke zu einer Zahl.*/
public class ExpressionFactory {
    /**Erzeugt einen mathematischen Ausdruck zu einer Zahl abhängig von der übergebenen Schwierigkeit
     * @param difficulty die übergebene Schwierigkeit
     * @param num die Zahl aus der ein Ausdruck generiert werden soll
     * @return der Ausdruck der erstellt wurde*/
    public static String generateExpression(int num, Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                return generateEasyExpression(num);
            }
            case MEDIUM -> {
                return generateMediumExpression(num);
            }
            case HARD -> {
                return generateHardExpression(num);
            }
        }
        return null;

    }

    private static String generateHardExpression(int num) {
        Random rand = new Random();
        int erg = rand.nextInt(3);
        String ex = "";
        switch (erg) {
            case 0 -> {
                ex = getEasyEquation(num, rand);
                ex = "Bestimme x: " + ex;
            }
            case 1 -> {
                ex = getMultiplicationExpression(num, rand);
            }
            case 2 -> {
                ex = getDivisionExpression(num, rand.nextInt(20) + 1);

            }
        }
        return ex;

    }

    private static String getMultiplicationExpression(int num, Random rand) {
        String ex;
        // Einen zufälligen Teiler finden
        int factor1;
        do {
            factor1 = rand.nextInt(num) + 1;
        } while (num % factor1 != 0);

        int factor2 = num / factor1;

        ex = factor1 + " * " + factor2;
        return ex;
    }

    private static String generateMediumExpression(int num) {
        Random rand = new Random();
        int erg = rand.nextInt(4);
        String ex = "";
        switch (erg) {
            case 0 -> {
                int firstaddend = num - rand.nextInt(num/2, num);
                int secondaddend = num - rand.nextInt(num/2, num);
                int thirdaddend = num - firstaddend - secondaddend;
                ex = firstaddend + " + " + secondaddend + " + " + thirdaddend;
            }
            case 1 -> {
                int minuend = rand.nextInt(num + 1);
                int minuend2 = rand.nextInt(num + 1 - minuend);
                int subtrahend = num + minuend + minuend2;

                ex = subtrahend + " - " + minuend + " - " + minuend2;

            }
            case 2 -> {
                ex = getMultiplicationExpression(num, rand);
            }
            case 3 -> {
                ex = getDivisionExpression(num, rand.nextInt(10) + 1);

            }
        }
        return ex;
    }

    private static String getDivisionExpression(int num, int divisor) {
        String ex;
        int dividend = num * divisor;

        ex = dividend + " : " + divisor;
        return ex;
    }

    private static String generateEasyExpression(int num) {
        Random rand = new Random();
        int firstaddend = num - rand.nextInt(num+1);
        int secondaddend = num - firstaddend;
        return firstaddend + " + " + secondaddend;

    }

    private static String getEasyEquation(int num, Random rand) {
        int a = rand.nextInt(8) + 2; // Faktor 2–9
        int b = rand.nextInt(11) - 5; // -5 bis 5
        int c = a * num + b;

        return formatLinearEquation(a, b, c);
    }

    private static String formatLinearEquation(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        if (a == 1) sb.append("x");
        else if (a == -1) sb.append("-x");
        else sb.append(a).append("x");

        if (b > 0) sb.append(" + ").append(b);
        else if (b < 0) sb.append(" - ").append(-b);

        sb.append(" = ").append(c);

        return sb.toString();
    }




}

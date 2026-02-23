package de.games.gui.hackgym.sections;

import de.games.logic.hackgym.enums.TaskType;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * UI-Komponente zur Anzeige von Dialog- und Beschreibungstexten.
 */
public class DialogSection extends JPanel {
    private final HashMap<TaskType, String> easyTaskDialogs = new HashMap<>();
    private final HashMap<TaskType, String> mediumTaskDialogs = new HashMap<>();
    private final HashMap<TaskType, String> hardTaskDialogs = new HashMap<>();
    private final JTextArea dialogArea;

    /**
     * Erstellt eine neue DialogSection.
     */
    public DialogSection() {
        fillMaps();
        setLayout(new BorderLayout());
        dialogArea = new JTextArea();
        dialogArea.setEditable(false);
        dialogArea.setLineWrap(true);
        dialogArea.setBackground(new Color(200, 220,255));
        dialogArea.setWrapStyleWord(true);
        add(dialogArea, BorderLayout.CENTER);
    }

    private void fillMaps() {
        String DRUECKE_ENTER_ZUM_BESTAETIGEN = "Drücke Enter zum Bestätigen.";
        easyTaskDialogs.put(TaskType.CALCULATION,
                "Berechne die Kontrollsumme. "+ DRUECKE_ENTER_ZUM_BESTAETIGEN + " Das System ist stabil, die Werte sollten eindeutig sein.");

        easyTaskDialogs.put(TaskType.SORT,
                "Sortiere die Datenpakete der Größe nach. Drücke hier für auf die Zahlen, damit sie unten hinzugefügt werden. " +
                        "Drücke auf die Zahlen unten, um sie zu entfernen. " +
                        "Drücke auf Fertig um deine Eingabe zu bestätigen." +
                        " Keine Anomalien erwartet.");

        easyTaskDialogs.put(TaskType.CLICK_NUMBER,
                "Klicke die Zahlen in aufsteigender Reihenfolge. Standard-Integritätscheck.");

        easyTaskDialogs.put(TaskType.COLOR_BUTTON,
                "Wähle die angezeigte Farbe aus. Der Farbcode ist noch unverändert.");

        mediumTaskDialogs.put(TaskType.CALCULATION,
                "Die Kontrollsumme wurde manipuliert. Drücke Enter zum Bestätigen. Berechne den korrekten Wert zur Wiederherstellung.");

        mediumTaskDialogs.put(TaskType.ADD_NUMBER_SEQUENCE,
                "Die Zahlenfolge ist unvollständig übertragen worden. Ergänze das fehlende Muster. " + DRUECKE_ENTER_ZUM_BESTAETIGEN);

        mediumTaskDialogs.put(TaskType.COLOR_BUTTON,
                "Die Farbcodes wirken verfälscht. Identifiziere die richtige Farbe trotz Störmustern. Der Text ist das wichtige");

        mediumTaskDialogs.put(TaskType.REVERSE_WORD,
                "Der String wurde rückwärts verschlüsselt. Rekonstruiere die ursprüngliche Zeichenfolge. " + DRUECKE_ENTER_ZUM_BESTAETIGEN);

        mediumTaskDialogs.put(TaskType.PATTERN_ERROR,
                "Dieses Datenmuster enthält eine Anomalie. Finde das manipulierte Element. " + DRUECKE_ENTER_ZUM_BESTAETIGEN);
        mediumTaskDialogs.put(TaskType.CLICK_NUMBER,
                "Die Zahlenreihenfolge wurde durch Fremdzugriff gestört. Stelle die korrekte Abfolge wieder her, " +
                        "bevor der Datenstrom erneut überschrieben wird. " +
                        DRUECKE_ENTER_ZUM_BESTAETIGEN);

        hardTaskDialogs.put(TaskType.CALCULATION,
                "Die Checksummen sind stark kompromittiert. Berechne die echte Summe unter Zeitdruck, bevor der Angriff sich fortsetzt. " +
                        DRUECKE_ENTER_ZUM_BESTAETIGEN);

        hardTaskDialogs.put(TaskType.COLOR_BUTTON,
                "Die Farbkanäle sind massiv verzerrt. Identifiziere den korrekten Code trotz manipulierter Signale. Der Text ist das wichtige");

        hardTaskDialogs.put(TaskType.ADD_NUMBER_SEQUENCE,
                "Das Muster wurde von Schadcode fragmentiert. Rekonstruiere die vollständige Zahlenfolge aus den Restdaten. " +
                        DRUECKE_ENTER_ZUM_BESTAETIGEN);

        hardTaskDialogs.put(TaskType.RHYTHM_BUTTON,
                "Der Eingabecontroller steht unter Angriff. Triff den exakten Rhythmus, um die Sequenz zu stabilisieren.");
    }

    /**
     * Setzt den aktuell angezeigten Dialogtext.
     * @param text der darzustellende Text
     */
    public void setText(String text) {
        dialogArea.setText(text);
    }

    /**
     * Liefert die Dialogtexte für Aufgaben des Schwierigkeitsgrads EASY.
     * @return eine Map mit TaskType als Schlüssel und Dialogtexten als Werte
     */
    public HashMap<TaskType, String> getEasyTaskDialogs() {
        return easyTaskDialogs;
    }

    /**
     * Liefert die Dialogtexte für Aufgaben des Schwierigkeitsgrads MEDIUM.
     * @return eine Map mit TaskType als Schlüssel und Dialogtexten als Werte
     */
    public HashMap<TaskType, String> getMediumTaskDialogs() {
        return mediumTaskDialogs;
    }

    /**
     * Liefert die Dialogtexte für Aufgaben des Schwierigkeitsgrads HARD.
     * @return eine Map mit TaskType als Schlüssel und Dialogtexten als Werte
     */
    public HashMap<TaskType, String> getHardTaskDialogs() {
        return hardTaskDialogs;
    }

    /**
     * Liefert den Einführungstext des Spiels.
     * @return der Intro-Dialogtext
     */
    public String getIntroDialog() {
        return """
                Rückmeldung bestätigt. Rekrut eingetroffen.
                
                Dies ist das HackGym – unsere Testumgebung für digitale Krisensituationen.
                Hier lernst du, beschädigte Daten zu reparieren, manipulierte Abläufe zu erkennen
                und Systeme unter Angriff stabil zu halten.
                
                Scheitere nicht. Wir beobachten jeden deiner Schritte.""";
    }

    /**
     * Liefert den Dialogtext für den Erfolgsfall auf dem Endbildschirm,
     * wenn ein neuer Highscore erreicht wurde.
     * @return der Erfolgsdialogtext
     */
    public String getScoreBoardSuccessDialog() {
        return """
                Auswertung abgeschlossen.
                
                Beeindruckend, Rekrut. Du hast den bisherigen Highscore übertroffen.
                Deine Analysegeschwindigkeit und Fehlerkorrektur liegen über dem bisherigen Niveau.
                
                Weiter so – das System reagiert bereits auf deine Präsenz.""";
    }

    /**
     * Liefert den Dialogtext für den Misserfolgsfall auf dem Endbildschirm,
     * wenn der Highscore nicht erreicht wurde.
     * @return der Misserfolgsdialogtext
     */
    public String getScoreBoardFailDialog() {
        return """
                Auswertung abgeschlossen.
                
                Rekrut, du hast den Highscore diesmal nicht erreicht.
                Die Systemfehler waren schneller als deine Reaktionen.
                
                Trainiere weiter – echte Angriffe zeigen keine Nachsicht.""";
    }
}

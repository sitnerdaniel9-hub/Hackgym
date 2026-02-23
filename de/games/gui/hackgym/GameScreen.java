package de.games.gui.hackgym;

import de.games.gui.hackgym.sections.*;
import javax.swing.*;
import java.awt.*;

/**
 * Diese Klasse gibt vor wie der Bildschirm während einer Aufgabe aussieht
 */
public class GameScreen extends JPanel {
    private final DialogSection dialogSection;
    private final JPanel taskContainer;

    /**
     * Erstellt den Spielbildschirm und initialisiert alle UI-Komponenten.
     */
    public GameScreen() {
        setLayout(new BorderLayout(10, 10));

        //OBERE LEISTE: Zeit + Score
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.add(TimerSection.getTimerSection(), BorderLayout.WEST);
        topBar.add(ScoreSection.getScoreSection(), BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        //DIALOG + RECRUITER
        JPanel dialogRow = new JPanel(new BorderLayout(10, 10));
        dialogSection = new DialogSection();
        dialogRow.add(dialogSection, BorderLayout.CENTER);
        JLabel recruiterImage = new JLabel(ImageCache.getRecruiterIcon(), SwingConstants.CENTER);
        dialogRow.add(recruiterImage, BorderLayout.EAST);


        //TASK-BEREICH
        taskContainer = new JPanel(new BorderLayout());
        taskContainer.setBorder(BorderFactory.createTitledBorder("Aufgabe"));

        //CENTER-BEREICH: DialogRow + TaskContainer
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        dialogRow.setPreferredSize(new Dimension(0, 200)); // 20%
        taskContainer.setPreferredSize(new Dimension(0, 600)); // 80%
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        centerWrapper.add(dialogRow, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.8;
        centerWrapper.add(taskContainer, gbc);
        add(centerWrapper, BorderLayout.CENTER);

        //BOTTOM SECTION
        add(BottomSection.getBottomSection(), BorderLayout.SOUTH);
    }

    /**
     * Liefert den Container, in dem die aktuelle Aufgabe angezeigt wird.
     * @return das Panel für die Aufgabenanzeige
     */
    public JPanel getTaskContainer() {
        return taskContainer;
    }

    /**
     * Liefert den Dialogbereich des Spielbildschirms.
     * @return die DialogSection des GameScreens
     */
    public DialogSection getDialogSection() {
        return dialogSection;
    }
}

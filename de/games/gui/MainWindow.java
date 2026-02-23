package de.games.gui;

import de.games.gui.hackgym.GuiController;
import javax.swing.*;
import java.awt.*;

/**
 * Hauptfenster der Anwendung.
 * Implementiert ein Singleton und dient als zentraler Einstiegspunkt
 * zur Anzeige und Auswahl der verfügbaren Spiele.
 */
public class MainWindow extends JFrame {
    private static MainWindow instance;

    private MainWindow() {
        setTitle("Game Launcher");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        showGameCollection();
    }

    /**
     * Liefert die Singleton-Instanz des MainWindow.
     * Falls noch keine Instanz existiert, wird eine neue erzeugt.
     *
     * @return die Singleton-Instanz des MainWindow
     */
    public static MainWindow getMainWindow() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    /**
     * Zeigt die Spielauswahl im Hauptfenster an.
     * Das Fenster wird geleert und mit einer Sammlung von Buttons
     * für die verfügbaren Spiele neu aufgebaut.
     */
    public void showGameCollection() {
        getContentPane().removeAll();
        setSize(800, 600);
        // Layout für mehrere Buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 20, 20));
        // 2 Reihen, 3 Spalten, 20px Abstand

        JButton game1 = createButton(de.games.gui.hackgym.sections.ImageCache.getRecruiterIcon());
        JButton game2 = new JButton("Game2");
        JButton game3 = new JButton("Game3");
        JButton game4 = new JButton("Game4");
        JButton game5 = new JButton("Game5");
        JButton game6 = new JButton("Game6");

        game1.addActionListener(e -> startHackgym());
        /**
        game2.addActionListener(e -> startMemory());
        game3.addActionListener(e -> startMinesweeper());
        game4.addActionListener(e -> startQuiz());
        game5.addActionListener(e -> startSafetyProtokoll());
        game6.addActionListener(e -> startVirusGame());*/
        // Buttons zum Panel hinzufügen
        panel.add(game1);
        panel.add(game2);
        panel.add(game3);
        panel.add(game4);
        panel.add(game5);
        panel.add(game6);

        // Optional: Schriftgröße erhöhen
        Font font = new Font("Arial", Font.BOLD, 18);
        game1.setFont(font);
        game2.setFont(font);
        game3.setFont(font);
        game4.setFont(font);
        game5.setFont(font);
        game6.setFont(font);

        // Panel in die Mitte
        add(panel, BorderLayout.CENTER);

        // Layout aktualisieren
        revalidate();
        repaint();
    }
    /**Startet Game2
    public void startQuiz() {
        de.games.gui.quiz.GUIController.getInstance().startGame();
    }*/
    /**Startet Game3
    private void startSafetyProtokoll() {
        de.games.gui.safetyprotocol.GameStart.start();
    }*/
    /**Startet Hackgym*/
    private void startHackgym() {
        de.games.gui.hackgym.GuiController.getGuiController().startGame();
    }

    /**Startet Game4
    private void startMemory() {
        de.games.gui.memory.GuiController.getGuiController().startMemory();
    }*/

    /**Startet Game5
    public void startMinesweeper() {de.games.logic.minesweeper.GameController.getGameCon().startGame();}*/

    /**Startet Game6
    private void startVirusGame() {
        de.games.gui.virus.GuiController.getGuiController().start();
    }*/

    private JButton createButton(ImageIcon icon) {
        JButton gameButton = new JButton();
        // Button-Ränder und Hintergrund unsichtbar machen
        gameButton.setOpaque(false);              // Button-Hintergrund transparent
        gameButton.setContentAreaFilled(false);  // Hintergrundfüllung entfernen
        gameButton.setBorderPainted(false);      // Rahmen entfernen
        gameButton.setFocusPainted(false);       // Fokus-Rahmen entfernen
        // Icon zuerst setzen (falls Button schon eine Größe hat)
        gameButton.setIcon(ImageUtils.scaleIconToButton(gameButton, icon));
        // ComponentListener hinzufügen, damit das Icon immer angepasst wird, wenn der Button resized wird
        gameButton.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                gameButton.setIcon(ImageUtils.scaleIconToButton(gameButton, icon));
            }
        });
        return gameButton;
    }
}

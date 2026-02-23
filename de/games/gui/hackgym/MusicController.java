package de.games.gui.hackgym;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse verwaltet die Musik des Spiels.*/
public class MusicController {

    private final Map<String, Clip> clips = new HashMap<>();

    /**
     * Lädt eine WAV-Datei und speichert sie unter einem Tracknamen.
     */
    public void loadTrack(String name, String resourcePath) {
        try {
            InputStream is =
                    MusicController.class.getResourceAsStream(resourcePath);

            if (is == null) {
                throw new IllegalStateException(
                        "Sound resource not found: " + resourcePath);
            }

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(
                            new BufferedInputStream(is));

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clips.put(name, clip);

        } catch (UnsupportedAudioFileException |
                 IOException |
                 LineUnavailableException e) {

            System.err.println("Fehler beim Laden des Tracks '" + name + "': " + e.getMessage());
        }
    }


    /**
     * Startet einen Track und lässt ihn loopen.
     */
    public void playLoop(String name) {
        Clip clip = clips.get(name);
        if (clip == null) {
            System.err.println("Track '" + name + "' wurde nicht geladen!");
            return;
        }

        if (clip.isRunning()) {
            clip.stop();
        }

        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    /**
     * Stoppt alle Tracks.
     */
    public void stopAll() {
        for (Clip clip : clips.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }
}

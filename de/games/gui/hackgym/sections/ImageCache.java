package de.games.gui.hackgym.sections;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Hilfsklasse zum Laden von Bildern. Der ImageCache stellt sicher, dass Bilder nur einmal geladen
 * und anschließend wiederverwendet werden.
 */
public class ImageCache {
    private static ImageIcon recruiterIcon;

    /**
     * Liefert das Recruiter-Icon.
     * @return das skalierte Recruiter-Icon als ImageIcon
     * @throws IllegalStateException wenn die Bildressource nicht gefunden wird
     */
    public static ImageIcon getRecruiterIcon() {
        if (recruiterIcon == null) {
            URL imgUrl = ImageCache.class.getResource("/de/games/gui/hackgym/ressource/icon/rekruterImage.png");
            if (imgUrl == null) {
                throw new IllegalStateException("Recruiter image not found!");
            }

            ImageIcon raw = new ImageIcon(imgUrl);
            Image scaled = raw.getImage().getScaledInstance(90, 90, Image.SCALE_FAST);
            recruiterIcon = new ImageIcon(scaled);
        }
        return recruiterIcon;
    }
}

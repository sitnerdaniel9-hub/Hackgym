package de.games.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Klasse zum Bearbeiten von Bildern
 */
public class ImageUtils {

    /**
     * Skaliert ein ImageIcon proportional auf die Größe des Buttons, ohne Verzerrung.
     *
     * @param button Der JButton, dessen Größe genutzt wird.
     * @param icon   Das zu skalierende ImageIcon.
     * @return Ein neues, skaliertes ImageIcon.
     */
    public static ImageIcon scaleIconToButton(JButton button, ImageIcon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Wenn Button noch nicht sichtbar ist, auf preferredSize zurückgreifen
        if (buttonWidth == 0 || buttonHeight == 0) {
            Dimension size = button.getPreferredSize();
            buttonWidth = size.width;
            buttonHeight = size.height;
        }

        int imgWidth = icon.getIconWidth();
        int imgHeight = icon.getIconHeight();

        // Seitenverhältnis beibehalten
        double widthRatio = (double) buttonWidth / imgWidth;
        double heightRatio = (double) buttonHeight / imgHeight;
        double scale = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);

        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
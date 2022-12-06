package com.hugo.utilz;

import java.awt.*;



public class HelpMethods {
    public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public static double mapDouble(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public static double limit(double a, double min, double max) {
        return (a > max) ? max : (a < min ? min: a );
    }

    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.setColor(color);
        g.drawString(text, x, y);
        /*g.setColor(Color.BLACK);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);*/
        //g.drawLine(rect.x, rect.y + rect.height / 2, rect.x + rect.width, rect.y + rect.height / 2);
    }
}

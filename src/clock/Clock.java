package clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created:  13.07.2014 12:14.
 */
public class Clock extends JFrame {
    private final int size = 200;
    private final int updateFrequency = 1000;
    private final int radiusSeconds = size / 2;
    private final int radiusMinutes = size / 3;
    private final int radiusHours = size / 5;
    private final int radiusMarksSmall = (size / 2) / 15 * 13;
    private final int radiusMarksLong = size / 2;
    //    private static final double PI = 3.14159265358979323846;
    private static final double PI = 3.14159265;

    private Theme theme = Theme.DARK;

    private DrawCanvas canvas;

    private Thread updateThread;

    public Clock() {
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(size, size));

        Container cp = getContentPane();
        cp.add(canvas);
        // or
        // setContentPane(canvas);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        this.pack();              // Either pack() the components; or setSize()
        this.setTitle("Clock");  // this JFrame sets the title
        this.setVisible(true);    // this JFrame show
        this.setResizable(false);

        updateThread = new Thread(() -> updating());
        updateThread.start();
    }

    private void updating() {
        while (true) {
            try {
                Thread.sleep(updateFrequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            canvas.repaint();
        }
    }

    private static int getCurrentSeconds() {
        SimpleDateFormat time = new SimpleDateFormat("ss");
        return Integer.parseInt(time.format(new Date()));
    }

    private static int getCurrentMinutes() {
        SimpleDateFormat time = new SimpleDateFormat("mm");
        return Integer.parseInt(time.format(new Date()));
    }

    private static int getCurrentHours() {
        SimpleDateFormat time = new SimpleDateFormat("HH");
        return Integer.parseInt(time.format(new Date()));
    }

    private class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            setBackground(theme.getBackground());
            drawBackground(g);
            drawClock(g);
        }
    }

    private void drawClock(Graphics g) {
        drawHoursArrow(g);
        drawMinutesArrow(g);
        drawSecondsArrow(g);
    }

    private void drawSecondsArrow(Graphics g) {
        g.setColor(theme.getSecondsArrow());
        double angle = 2 * PI / 60 * getCurrentSeconds() + PI / 2;
        double x = Math.cos(angle) * radiusSeconds;
        double y = Math.sin(angle) * radiusSeconds;
        g.drawLine(size / 2, size / 2, size / 2 - (int) x, size / 2 - (int) y);
    }

    private void drawMinutesArrow(Graphics g) {
        g.setColor(theme.getMinutesArrow());
        double angle = 2 * PI / 60 * getCurrentMinutes() + PI / 2;
        double x = Math.cos(angle) * radiusMinutes;
        double y = Math.sin(angle) * radiusMinutes;
        g.drawLine(size / 2, size / 2, size / 2 - (int) x, size / 2 - (int) y);
    }

    private void drawHoursArrow(Graphics g) {
        g.setColor(theme.getHoursArrow());
        double angle = 2 * PI / 12 * getCurrentHours() + PI / 2;
        double x = Math.cos(angle) * radiusHours;
        double y = Math.sin(angle) * radiusHours;
        g.drawLine(size / 2, size / 2, size / 2 - (int) x, size / 2 - (int) y);
    }

    private void drawBackground(Graphics g) {
        drawMarks(g);
        drawDigitTime(g);
    }

    private void drawMarks(Graphics g) {
        g.setColor(theme.getMarkers());

        for (int i = 0; i < 60; i++) {
            int radiusSmall = radiusMarksSmall;
            if (i % 5 == 0) {
                g.setColor(theme.getMainMarkers());
                if (i % 15 == 0) {
                    radiusSmall = radiusMarksSmall - size/20;
                }
            } else {
                g.setColor(theme.getMarkers());
            }
            double angle = 2 * PI / 60 * i + PI / 2;
            double x = Math.cos(angle);
            double y = Math.sin(angle);
            g.drawLine((int) (size / 2 - x * radiusSmall), (int) (size / 2 - y * radiusSmall),
                    (int) (size / 2 - x * radiusMarksLong), (int) (size / 2 - y * radiusMarksLong));
        }
    }

    private void drawDigitTime(Graphics g) {
        g.setColor(theme.getDigits());
        g.setFont(new Font("Monospaced", Font.PLAIN, size/15));

        int hours = getCurrentHours();
        String hou = hours + "";
        if (hours < 10) {
            hou = "0" + hou;
        }

        int minutes = getCurrentMinutes();
        String min = minutes + "";
        if (minutes < 10) {
            min = "0" + min;
        }

        int seconds = getCurrentSeconds();
        String sec = seconds + "";
        if (seconds < 10) {
            sec = "0" + sec;
        }

        g.drawString(hou + ":" + min + ":" + sec, size / 2 - size/6, size / 2 + size/6);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Clock();
        });
    }
}

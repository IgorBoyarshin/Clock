package clock;

import java.awt.*;

/**
 * Created:  13.07.2014 15:41.
 */
public enum Theme {
    DARK(Color.red, Color.green, Color.blue, Color.black, Color.gray, Color.white, Color.yellow),
    LIGHT(Color.black, Color.black, Color.black, Color.white, Color.darkGray, Color.red, Color.blue);

    private final Color secondsArrow;
    private final Color minutesArrow;
    private final Color hoursArrow;

    private final Color background;
    private final Color markers;
    private final Color mainMarkers;

    private final Color digits;

    Theme(Color secondsArrow, Color minutesArrow, Color hoursArrow, Color background, Color markers, Color mainMarkers, Color digits) {
        this.secondsArrow = secondsArrow;
        this.minutesArrow = minutesArrow;
        this.hoursArrow = hoursArrow;
        this.background = background;
        this.markers = markers;
        this.mainMarkers = mainMarkers;
        this.digits = digits;
    }

    public Color getSecondsArrow() {
        return secondsArrow;
    }

    public Color getMinutesArrow() {
        return minutesArrow;
    }

    public Color getHoursArrow() {
        return hoursArrow;
    }

    public Color getBackground() {
        return background;
    }

    public Color getMarkers() {
        return markers;
    }

    public Color getMainMarkers() {
        return mainMarkers;
    }

    public Color getDigits() {
        return digits;
    }
}

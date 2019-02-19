package clock;

import java.time.LocalDateTime;

/**
 * Get the current time and set the angle of arrows.
 * 
 * @author joanna Czernicka
 *
 */
public class Logic {

	private static int hour;
	private static int minute;
	private static int second;

	Logic() {
		LocalDateTime d = LocalDateTime.now();
		hour = d.getHour();
		minute = d.getMinute();
		second = d.getSecond();
	}

	public static double getHourRot() {
		return (hour + minute / 60.0) / 12 * 360;
	}

	public static double getMinuteRot() {
		return (minute / 60.0) * 360;
	}

	public static double getSecondRot() {
		return (second / 60.0) * 360;
	}
}

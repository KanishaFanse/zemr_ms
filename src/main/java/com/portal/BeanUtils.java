package com.portal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;

public class BeanUtils {



	public static boolean isNullOrZero(Long id) {
		if (id == null || id.longValue() == 0)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	public static boolean isNullOrZero(Integer id) {
		if (id == null || id.longValue() == 0)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	public static boolean isNullOrZero(Float id) {
		if (id == null || id.floatValue() == 0.0f)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	public static boolean isNullOrZero(Double id) {
		if (id == null || id.doubleValue() == 0.0d)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	public static boolean isNullOrEmpty(Number id) {
		if (id == null || id.floatValue() == 0.0f || id.longValue() == 0)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	public static boolean isNullOrEmpty(Collection<?> list) {
		if (list == null || list.isEmpty()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static double convertDecimals(double argument, int afterdecimals) {
		DecimalFormat decimalFormat = null;
		if (afterdecimals == 1) {
			decimalFormat = new DecimalFormat("#.#");
		} else if (afterdecimals == 2) {
			decimalFormat = new DecimalFormat("#.##");
		} else if (afterdecimals == 3) {
			decimalFormat = new DecimalFormat("#.###");
		} else if (afterdecimals == 4) {
			decimalFormat = new DecimalFormat("#.####");
		} else if (afterdecimals == 5) {
			decimalFormat = new DecimalFormat("#.#####");
		} else if (afterdecimals == 6) {
			decimalFormat = new DecimalFormat("#.######");
		} else {
			decimalFormat = new DecimalFormat("#");
		}
		return Double.valueOf(decimalFormat.format(argument));
	}

	public static double roundToDecimals(double parameter, int noOfDecimal) {
		int temp = (int) ((parameter * Math.pow(10, noOfDecimal)));
		return (((double) temp) / Math.pow(10, noOfDecimal));
	}

	public static String wrapText(String text, int len) {
		// return empty array for null text
		if (text == null)
			return new String();

		// return text if less than length
		if (text.length() <= len)
			return new String(text);

		char[] chars = text.toCharArray();

		Vector<String> lines = new Vector<String>();

		StringBuffer line = new StringBuffer();

		StringBuffer word = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			word.append(chars[i]);
			if (chars[i] == ' ') {
				if ((line.length() + word.length()) > len) {
					lines.add(line.toString());
					line.delete(0, line.length());
				}
				line.append(word);
				word.delete(0, word.length());
			}
		}
		// handle any extra chars in current word
		if (word.length() > 0) {
			if ((line.length() + word.length()) > len) {
				lines.add(line.toString());
				line.delete(0, line.length());
			}
			line.append(word);
		}

		// handle extra line
		if (line.length() > 0) {
			lines.add(line.toString());
		}
		StringBuffer wrappedText = new StringBuffer(text.length());

		for (Enumeration<String> e = lines.elements(); e.hasMoreElements();) {
			wrappedText.append(e.nextElement() + "&#10;");
		}
		return wrappedText.toString();
	}

	public static double round(double x, int scale) {
		return round(x, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static double round(double x, int scale, int roundingMethod) {

		try {

			return (new BigDecimal(Double.toString(x))

					.setScale(scale, roundingMethod))

					.doubleValue();

		} catch (NumberFormatException ex) {

			if (Double.isInfinite(x)) {

				return x;

			} else {

				return Double.NaN;

			}

		}

	}

	public static String stringTriming(String name) {
		String[] temp = null;
		if (name == null || name.trim().isEmpty()) {
			name = "";
		} else {
			temp = name.trim().split(" ");
			name = "";
			for (int count = 0; count < temp.length; count++) {
				if (!temp[count].isEmpty()) {
					name += " " + temp[count];
				}
			}
			name = name.trim();
		}
		return name;
	}


}

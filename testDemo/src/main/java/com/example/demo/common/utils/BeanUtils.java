package com.example.demo.common.utils;

import java.util.Collection;
import java.util.Map;

public class BeanUtils {

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if ((o instanceof String)) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if ((o instanceof Collection)) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (((Object[]) o).length == 0) {
				return true;
			}
		} else if (((o instanceof Map)) && (((Map) o).isEmpty())) {
			return true;
		}
		return false;
	}

	public static boolean isZeroEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if ((o instanceof String)) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if ((o instanceof Collection)) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (((Object[]) o).length == 0) {
				return true;
			}
		} else if ((o instanceof Map)) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else if ((o instanceof Double)) {
			Double lEmpty = Double.valueOf(0.0D);
			if (o == lEmpty) {
				return true;
			}
		} else if ((o instanceof Float)) {
			Float lEmpty = Float.valueOf(0.0F);
			if (o == lEmpty) {
				return true;
			}
		} else if ((o instanceof Long)) {
			Long lEmpty = Long.valueOf(0L);
			if (o == lEmpty) {
				return true;
			}
		} else if ((o instanceof Short)) {
			Short sEmpty = Short.valueOf((short) 0);
			if (o == sEmpty) {
				return true;
			}
		} else if ((o instanceof Integer)) {
			Integer sEmpty = Integer.valueOf(0);
			if (o == sEmpty) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	public static boolean isNotEmpty(Long o) {
		return !isEmpty(o);
	}

	public static boolean isNotIncZeroEmpty(Object o) {
		return !isZeroEmpty(o);
	}

	public static boolean isNumber(Object o) {
		if (o == null) {
			return false;
		}
		if ((o instanceof Number)) {
			return true;
		}
		if ((o instanceof String)) {
			try {
				Double.parseDouble((String) o);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}


	public static boolean validClass(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
		}
		return false;
	}
}

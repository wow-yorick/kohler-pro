/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.util;

/**
 * 属性名列名转换工具类
 * 
 * @author shefeng
 * @Date 2014年9月25日
 */
public class CamelCaseUtils {

	private static final char SEPARATOR = '_';

	/**
	 * 将属性名转换为列名
     * @param s
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	public static String toUnderlineName(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	
	
	/**
     * 将列名转换为属性名
     * @param s
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
     * @param s
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static void main(String[] args) {
		// System.out.println(CamelCaseUtils.toUnderlineName("ISOCertifiedStaff"));
		// System.out.println(CamelCaseUtils.toUnderlineName("CertifiedStaff"));
		// System.out.println(CamelCaseUtils.toUnderlineName("userId"));
		// System.out.println(CamelCaseUtils.toCamelCase("iso_certified_staff"));
		// System.out.println(CamelCaseUtils.toCamelCase("certified_staff"));
		// System.out.println(CamelCaseUtils.toCamelCase("userId"));
		String str = "TL_LOG";
		System.out.println(str.length());
		int index = str.indexOf("_");
		System.out.println(index);
		System.out.println(str.substring(index + 1));
	}
}
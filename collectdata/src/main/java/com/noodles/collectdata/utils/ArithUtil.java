package com.noodles.collectdata.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 文件名：ArithUtil.java
 * 描述：由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入
 * 作者：KJ00019
 * 日期：2017年8月11日下午5:26:17
 */
public class ArithUtil {
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和 作者：SZ04951_jason 日期：2016年6月7日上午10:01:13
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差 作者：SZ04951_jason 日期：2016年6月7日上午10:02:59
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 作者：SZ04951_jason 日期：2016年6月7日上午10:03:54
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商 作者：SZ04951_jason 日期：2016年6月7日上午10:06:32
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商 作者：SZ04951_jason 日期：2016年6月7日上午10:07:25
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字舍弃。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商 作者：SZ04951_jason 日期：2016年6月7日上午10:09:03
	 */
	public static double divDown(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果 作者：SZ04951_jason 日期：2016年6月7日上午10:10:21
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供舍弃对应小数点处理。
	 * 
	 * @param v
	 *            需要截取小数点尾数的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 截取后的结果 作者：SZ04951_jason 日期：2016年6月7日上午10:15:18
	 */
	public static double roundDown(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 
	 * @param v
	 *            需要平方处理的数值
	 * @return 返回平方 作者：SZ04951_jason 日期：2016年6月7日上午10:16:34
	 */
	public static double pow(double v) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		return b.pow(2).doubleValue();
	}

	// 四则运算表达式计算
	public static String expArithmetic(String exp) {
		String result = parseExp(exp).replaceAll("[\\[\\]]", "");
		return result;
	}

	/**
	 * 解析计算四则运算表达式
	 * 
	 * @param expression
	 * @return
	 */
	private static String parseExp(String expression) {
		// String numberReg="^((?!0)\\d+(\\.\\d+(?<!0))?)|(0\\.\\d+(?<!0))$";
		expression = expression.replaceAll("\\s+", "").replaceAll("^\\((.+)\\)$", "$1");
		String checkExp = "\\d";
		String minExp = "^((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))$";
		String exp = "^((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))";

		// 判断是否包含字母
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(expression);
		if (m.matches()) {
			return "Illegal Expression :" + expression;
		}

		// 是否是只是数字
		if (expression.matches(exp)) {
			// String result=calculate(expression);

			return Double.parseDouble(expression) >= 0 ? expression : "[" + expression + "]";
		}
		// 最小表达式计算
		if (expression.matches(minExp)) {
			String result = calculate(expression);

			return Double.parseDouble(result) >= 0 ? result : "[" + result + "]";
		}
		// 计算不带括号的四则运算
		String noParentheses = "^[^\\(\\)]+$";
		String priorOperatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
		String operatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
		if (expression.matches(noParentheses)) {
			Pattern patt = Pattern.compile(priorOperatorExp);
			Matcher mat = patt.matcher(expression);
			if (mat.find()) {
				String tempMinExp = mat.group();
				expression = expression.replaceFirst(priorOperatorExp, parseExp(tempMinExp));
			} else {
				patt = Pattern.compile(operatorExp);
				mat = patt.matcher(expression);

				if (mat.find()) {
					String tempMinExp = mat.group();
					expression = expression.replaceFirst(operatorExp, parseExp(tempMinExp));
				}
			}
			return parseExp(expression);
		}
		// 计算带括号的四则运算
		String minParentheses = "\\([^\\(\\)]+\\)";
		Pattern patt = Pattern.compile(minParentheses);
		Matcher mat = patt.matcher(expression);
		if (mat.find()) {
			String tempMinExp = mat.group();
			expression = expression.replaceFirst(minParentheses, parseExp(tempMinExp));
		}
		return parseExp(expression);
	}

	/**
	 * 计算最小单位四则运算表达式（两个数字）
	 * 
	 * @param exp
	 * @return
	 */
	private static String calculate(String exp) {
		exp = exp.replaceAll("[\\[\\]]", "");
		String number[] = exp.replaceFirst("(\\d)[\\+\\-\\*\\/]", "$1,").split(",");
		BigDecimal number1 = new BigDecimal(number[0]);
		BigDecimal number2 = new BigDecimal(number[1]);
		BigDecimal result = null;

		String operator = exp.replaceFirst("^.*\\d([\\+\\-\\*\\/]).+$", "$1");
		if ("+".equals(operator)) {
			result = number1.add(number2);
		} else if ("-".equals(operator)) {
			result = number1.subtract(number2);
		} else if ("*".equals(operator)) {
			result = number1.multiply(number2);
		} else if ("/".equals(operator)) {
			result = number1.divide(number2);
		}

		return result != null ? result.toString() : null;
	}

	/**
	 * 公式替换
	 * 
	 * @param strFormula
	 *            替换的字符串
	 * @param strOld关键字
	 * @param strNew替换后的数据
	 * @return 替换后的公式
	 */
	public static String formulaReplace(String strFormula, String strOld, String strNew) {
		StringBuilder sb = new StringBuilder();
		String arr = "+-*/()";
		List<String> ls = new ArrayList<String>();
		String lsVal = "";
		// 一个字符一个字符拆开
		for (int i = 0; i < strFormula.length(); i++) {
			String str = strFormula.substring(i, i + 1);
			if (arr.indexOf(str) >= 0) {
				ls.add(lsVal);
				ls.add(str);
				lsVal = "";
			} else
				lsVal += str;
		}
		ls.add(lsVal);
		// 替换数组中的数据
		for (int h = 0; h < ls.size(); h++) {
			String v = ls.get(h);
			if (v.equals(strOld)) {
				sb.append(strNew);
			} else
				sb.append(v);
		}
		return sb.toString();
	}

}

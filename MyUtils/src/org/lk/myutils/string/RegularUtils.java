package org.lk.myutils.string;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则式处理类
 * @author LK
 *
 */
public class RegularUtils {

	/**
	 * 判断字符串是否与正则式匹配
	 * @param str 传入的字符串
	 * @param regular 正则表达式
	 * @return boolean类型</p> true：匹配成功</p>false：匹配失败
	 */
	public static boolean match(String str, String regular) {
		return str.matches(regular);
	}
	
	public static boolean isContain(String str, String regular) {
        Pattern p = Pattern.compile(regular);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
	
	
	public static int matcherCount(String str, String regular) {
		if(str==null||regular.equals("")||str.equals(""))
			return 0;
        Pattern p = Pattern.compile("(?i)"+regular);
        Matcher m = p.matcher(str);
        int count = 0;
        while(m.find()){
              count ++;
        }
        return count;
    }
	
	
	public static String matcher(String s, String rule) {
		String[] ss = null;
		if(rule.indexOf("@")!=-1) {
			ss = rule.split("@");
			rule = ss[0];
		}
		Pattern p = Pattern.compile(rule);
		Matcher m = p.matcher(s);
		if(m.find(0)){
			if(ss!=null) {
				return m.group(0).replaceFirst(rule, ss[1]);
			} else {
				return m.group(0);
			}
		} else {
			return "";
		}
	}
	
	public static List<String> matcherS(String s, String rule) {
		List<String> results = new ArrayList<String>();
		String[] ss = null;
		if(rule.indexOf("@")!=-1) {
			ss = rule.split("@");
			rule = ss[0];
		}
		Pattern p = Pattern.compile(rule);
		Matcher m = p.matcher(s);
		while (m.find()) {
			if(ss!=null) {
				results.add(m.group().replaceFirst(rule, ss[1]));
			} else {
				results.add(m.group());
			}
		}
		return results;
	}
	
	public static String matchCharset(String content) {  
	    String chs = "utf-8";  
	    Pattern p = Pattern.compile("(?<=charset=)(.+)(?=\")");  
	    Matcher m = p.matcher(content);  
	    if (m.find())  
	        return m.group();  
	    return chs;  
	}  
	
}

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: StringUtil
 * Author:   Administrator
 * Date:     2019/2/22 0022 0:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wjysushe.utils;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/22 0022
 * @since 1.0.0
 */
public class StringUtil {

	public static Boolean empty(String s){
		return s == null || s.equals("");
	}

	public static Boolean notEmpty(String s){
		return !empty(s);
	}

}
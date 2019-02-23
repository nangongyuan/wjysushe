/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: ReplyUtil
 * Author:   yuan
 * Date:     2019/2/21 18:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.utils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ly6333
 * @create 2019/2/21
 * @since 1.0.0
 */
public class ReplyUtil {

    public static String alter(String msg){
        String string = "<script>alert('%s')</script>";
        return String.format(string, msg);
    }
}
package com.archer;

/**
 * <pre>
 *     author : markzl
 *     e-mail : zhoulong2312@163.com
 *     time   : 2017/04/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ConstantUtils {

//    //基地址
//    public final static String BASE_URL = "http://172.25.22.1:8080/server";
//
//    //获取新闻消息
//    public final static String GET_NEWS_URL = "getNewsJSON.php";
//
//    //获取论坛消息
//    public final static String GET_FOURM_URL="getForumJSON.php";
//
//    public final static String LOGIN_URL = "Servlet";

    //基地址1   itheima74                                   172.25.13.242
//    public final static String BASE_URL = "http://192.168.191.1:8080/itheima74/servlet";
    public final static String BASE_URL = "http://192.168.31.1:8080/itheima74/servlet";
//    public final static String BASE_URL = "http://172.20.92.120:8080/itheima74/servlet";


    //基地址2  server                                      172.25.13.242
//    public final static String BASE_URL2 = "http://192.168.191.1:8080/server";
    public final static String BASE_URL2 = "http://192.168.31.1:8080/server";
//    public final static String BASE_URL2 = "http://172.20.92.120:8080/server";

                        //192.168.31.241

    //获取新闻消息
    public final static String GET_NEWS_URL = "GetNewsServlet";

    //获取论坛消息
    public final static String GET_FOURM_URL="getForumJSON.php";

    //注册和登录的Servlet
    public final static String LOGIN_URL = "Servlet";


    public static String getActionUrl(String action) {

        return BASE_URL + "/" + action;
    }

    public static String getActionUrl2(String action) {

        return BASE_URL2 + "/" + action;
    }
}

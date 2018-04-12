package com.archer.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;


import com.archer.model.MessageBean;
import com.archer.model.ResultBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ChatHttpUtils {

    private static final String URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "f166d2eff1911a6c9195b3e89f0eed27";

    public static MessageBean sendMessage(String msg) {
        MessageBean message = new MessageBean();
        String jsonRes = doGet(msg);
        Gson gson = new Gson();
        ResultBean result;
        try {
            result = gson.fromJson(jsonRes, ResultBean.class);
            message.setMsg(result.getText());
        } catch (JsonSyntaxException e) {
            message.setMsg("failed");
        }
        message.setDate(new Date());
        message.setType(MessageBean.Type.OUTCOMING);
        return message;
    }

    private static String doGet(String msg) {
        String result = "";
        String url = getResult(msg);
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try {
            URL urlStr = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlStr.openConnection();
            conn.setReadTimeout(5 * 1000);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");

            is = conn.getInputStream();
            int len = -1;
            byte[] buf = new byte[128];
            baos = new ByteArrayOutputStream();
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            result = new String(baos.toByteArray());
            baos.flush();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    private static String getResult(String msg) {
        String url;
        url = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg);
        return url;
    }


}
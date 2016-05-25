package edu.feicui.jsondemo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/25.
 */
public class HttpUtils {
   public static String getJsonData(String path) {
        String jsonData = null;
        try {
            URL url1 = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            InputStream inputStream = connection.getInputStream();
            String jsonDatas = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = inputStream.read(bytes)) != -1){
                bos.write(bytes,0,len);
            }
            bos.close();
            inputStream.close();
            jsonData = bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}

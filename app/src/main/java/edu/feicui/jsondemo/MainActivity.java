package edu.feicui.jsondemo;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.feicui.jsondemo.bean.StudentsBean;
import edu.feicui.jsondemo.utils.HttpUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int MESSAGE = 1;
    private TextView name1;
    private TextView name2;
    private TextView age1;
    private TextView age2;
    private TextView className;
    private String mClassName;

    private String Path = "http://192.168.1.147:8080/index2.jsp";
    private List<StudentsBean>  listData =null;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE:
                    name1.setText(listData.get(0).name);
                    age1.setText(String.valueOf(listData.get(0).age));
                    name2.setText(listData.get(1).name);
                    age2.setText(String.valueOf(listData.get(1).age));
                    className.setText(mClassName);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name1 = (TextView) findViewById(R.id.tv_name01);
        name2 = (TextView) findViewById(R.id.tv_name02);
        age1 = (TextView) findViewById(R.id.tv_age01);
        age2 = (TextView) findViewById(R.id.tv_age02);
        className = (TextView) findViewById(R.id.tv_class);
        new Thread(){
            @Override
            public void run() {
                String json= HttpUtils.getJsonData(Path);
                listData=parseJson(json);
                handler.sendEmptyMessage(MESSAGE);
            }
        }.start();
    }

    private List<StudentsBean> parseJson(String json) {
        List<StudentsBean> list=new ArrayList<>();
        try {
            JSONObject students=new JSONObject(json);
            mClassName = (String) students.get("class");
            JSONArray studentsArray = (JSONArray) students.get("students");
            for (int i = 0; i <studentsArray.length() ; i++) {
                JSONObject jsonObject= (JSONObject) studentsArray.get(i);
                StudentsBean bean  = new StudentsBean();
                bean.name = (String) jsonObject.get("name");
                bean.age=jsonObject.getInt("age");
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}

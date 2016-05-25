package edu.feicui.jsondemo.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.jsondemo.bean.StudentsBean;

/**
 * Created by Administrator on 2016/5/25.
 */
public class JsonTools {
    public static String mclass;

    public static List<StudentsBean> parseJson(String json) {
        List<StudentsBean> list = new ArrayList<>();
        try {
            JSONObject students = new JSONObject(json);
            mclass = (String) students.get("class");
            JSONArray studentsArray = (JSONArray) students.get("students");
            for (int i = 0; i < studentsArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) studentsArray.get(i);
                StudentsBean bean = new StudentsBean();
                bean.name = (String) jsonObject.get("name");
                bean.age = jsonObject.getInt("age");
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}

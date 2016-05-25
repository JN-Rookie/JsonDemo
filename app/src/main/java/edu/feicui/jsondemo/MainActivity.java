package edu.feicui.jsondemo;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.feicui.jsondemo.adapter.JsonAdapter;
import edu.feicui.jsondemo.bean.StudentsBean;
import edu.feicui.jsondemo.utils.HttpUtils;
import edu.feicui.jsondemo.utils.JsonTools;

import static edu.feicui.jsondemo.utils.JsonTools.parseJson;

public class MainActivity extends AppCompatActivity {
    private static final String TAG     = "MainActivity";
    public static final  int    MESSAGE = 1;
    private TextView className;
    private ListView mListView;

    private String Path = "http://192.168.1.147:8080/index2.jsp";
    private List<StudentsBean> listData;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE:
                    className.setText(JsonTools.mclass);
                    JsonAdapter adapter = new JsonAdapter(MainActivity.this, listData);
                    mListView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_students);
        className = (TextView) findViewById(R.id.tv_class);
        new Thread() {
            @Override
            public void run() {
                String json = HttpUtils.getJsonData(Path);
                listData = JsonTools.parseJson(json);
                handler.sendEmptyMessage(MESSAGE);
            }
        }.start();

    }


}

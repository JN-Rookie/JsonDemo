package edu.feicui.jsondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.feicui.jsondemo.R;
import edu.feicui.jsondemo.bean.StudentsBean;


/**
 * Created by Administrator on 2016/5/25.
 */
public class JsonAdapter extends BaseAdapter{
    private List<StudentsBean> data;
    private LayoutInflater mInflater;

    public JsonAdapter(Context context,List<StudentsBean> data) {
        this.data=data;
        mInflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    @Override
    public StudentsBean getItem(int position) {
        if(data!=null){
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.list_students_item,null);
        }
        TextView name= (TextView) convertView.findViewById(R.id.tv_name);
        TextView age= (TextView) convertView.findViewById(R.id.tv_age);
        StudentsBean bean=data.get(position);
        name.setText(bean.name);
        age.setText(String.valueOf(bean.age));
        return convertView;
    }
}

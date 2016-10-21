package com.study.android.kehan.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.study.android.kehan.myapplication.Data.Contacts;
import com.study.android.kehan.myapplication.R;

import java.util.List;

/**
 * Created by kehan on 16-10-21.
 */
public class ContactsListAdapter extends BaseAdapter {

    private Context context;
    private List<Contacts> dataList;

    public ContactsListAdapter(Context context, List<Contacts> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public List<Contacts> getDataList() {
        return dataList;
    }

    @Override
    public int getCount() {
        if (isNUll()) {
            return 0;
        }
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        if (isNUll()) {
            return null;
        }
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder viewHolder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contacts_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (TextView) convertView.findViewById(R.id.first);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            convertView = view;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.image.setText(dataList.get(i).getFirstLetter());
        viewHolder.name.setText(dataList.get(i).getName());
        return convertView;
    }

    private boolean isNUll() {
        return dataList == null;
    }

    private class ViewHolder {
        public TextView image;
        public TextView name;
    }
}

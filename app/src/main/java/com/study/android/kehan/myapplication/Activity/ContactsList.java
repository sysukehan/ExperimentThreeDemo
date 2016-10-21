package com.study.android.kehan.myapplication.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.study.android.kehan.myapplication.Adapter.ContactsListAdapter;
import com.study.android.kehan.myapplication.Data.Contacts;
import com.study.android.kehan.myapplication.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kehan on 16-10-5.
 * 联系人列表Activity
 */
public class ContactsList extends AppCompatActivity {

    private ListView contactsList;
    private List<Contacts> data = new ArrayList<>();
    private List<Map<String, Object>> listItems = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private AlertDialog.Builder builder;

    private ContactsListAdapter adapter;  //  拓展Adapter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findView();
        initialData();
        setListener();
    }

    private void findView() {
        setContentView(R.layout.contacts_list);
        contactsList = (ListView) findViewById(R.id.contacts_list);
        builder = new AlertDialog.Builder(this);
    }

    private void initialData() {
        data.add(new Contacts("Aaron", "17715523654", "手机", "江苏苏州电信", R.color.background3));
        data.add(new Contacts("Elvis", "18825653224", "手机", "广东揭阳移动", R.color.background2));
        data.add(new Contacts("David", "15052116654", "手机", "江苏无锡移动", R.color.background1));
        data.add(new Contacts("Edwin", "18854211875", "手机", "山东青岛移动", R.color.background4));
        data.add(new Contacts("Frank", "13955188541", "手机", "安徽合肥移动", R.color.background3));
        data.add(new Contacts("Joshua", "13621574410", "手机", "江苏苏州移动", R.color.background2));
        data.add(new Contacts("Ivan", "15684122771", "手机", "山东烟台联通", R.color.background1));
        data.add(new Contacts("Mark", "17765213579", "手机", "广东珠海电信", R.color.background4));
        data.add(new Contacts("Joseph", "13315466578", "手机", "河北石家庄电信", R.color.background3));
        data.add(new Contacts("Phoebe", "17895466428", "手机", "山东东营移动", R.color.background2));
        for (Contacts c : data) {
            Map<String, Object> listItem = new LinkedHashMap<>();
            listItem.put("firstLetter", c.getFirstLetter());
            listItem.put("name", c.getName());
            listItems.add(listItem);
        }
        simpleAdapter = new SimpleAdapter(this, listItems, R.layout.contacts_list_item,
                new String[] {"firstLetter", "name"}, new int[] {R.id.first, R.id.name});
        contactsList.setAdapter(simpleAdapter);

        //  拓展版写法
//        adapter = new ContactsListAdapter(this, data);
//        contactsList.setAdapter(adapter);

        builder.setTitle("删除联系人");
    }

    private void setListener() {

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });

        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ContactsList.this, ContactsInfo.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contacts", data.get(i));
                //  拓展版的单击这样写会好一点
//                bundle.putSerializable("contacts", adapter.getDataList().get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        contactsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                builder.setMessage("确定删除联系人" + data.get(pos).getName() + "？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listItems.remove(pos);
                        data.remove(pos);
                        simpleAdapter.notifyDataSetChanged();
                    }
                }).create().show();
                return true;
            }
        });

        // 拓展版的长按写法
//        contactsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
//                builder.setMessage("确定删除联系人" + adapter.getDataList().get(pos).getName() + "？");
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        adapter.getDataList().remove(pos);
//                        adapter.notifyDataSetChanged();
//                    }
//                }).create().show();
//                return true;
//            }
//        });
    }
}

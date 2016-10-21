package com.study.android.kehan.myapplication.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.android.kehan.myapplication.Data.Contacts;
import com.study.android.kehan.myapplication.R;

/**
 * Created by kehan on 16-10-5.
 * 联系人详细信息Activity
 */
public class ContactsInfo extends AppCompatActivity {

    private RelativeLayout topBack;  //  顶部三分之一
    private ImageView back;  //  返回图标
    private ImageView star;  //  星星图标
    private TextView name;  //  联系人姓名
    private TextView mobileNumber;  //  联系人电话号码
    private TextView type;  //  电话号码的类型
    private TextView location;  //  电话号码归属地
    private ListView operationList;  //  联系人操作列表

    private Resources res;
    private String[] operations;
    private Contacts contacts;  //  该页面的联系人

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findView();
        initialData();
        setListener();
    }

    /**
     * 初始化布局
     */
    private void findView() {
        setContentView(R.layout.main_contacts);
        topBack = (RelativeLayout) findViewById(R.id.top_back);
        back = (ImageView) findViewById(R.id.back);
        star = (ImageView) findViewById(R.id.star);
        name = (TextView) findViewById(R.id.name);
        mobileNumber = (TextView) findViewById(R.id.mobileNumber);
        type = (TextView) findViewById(R.id.type);
        location = (TextView) findViewById(R.id.location);
        operationList = (ListView) findViewById(R.id.operation_list);
    }

    /**
     * 初始化数据
     */
    private void initialData() {
        res = getResources();

        contacts = (Contacts) getIntent().getExtras().get("contacts");
        if (contacts != null) {
            topBack.setBackgroundColor(res.getColor(contacts.getBackgroundColor()));
            name.setText(contacts.getName());
            mobileNumber.setText(contacts.getMobileNumber());
            type.setText(contacts.getType());
            location.setText(contacts.getLocation());
        }

        operations = new String[] {res.getString(R.string.edit_contacts), res.getString(R.string.share_contacts),
                res.getString(R.string.add_to_black_list), res.getString(R.string.delete_contacts)};
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<>(this, R.layout.operation_list_item, operations);
        operationList.setAdapter(arrayAdapter);

        star.setTag(0);  //  将星星图标此时的tag设为0
    }

    /**
     * 设置监听器
     */
    private void setListener() {

        /**
         * 返回图标被点击，销毁该Activity
         */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * 星星图标被点击，切换
         */
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((Integer) view.getTag() == 0) {
                    view.setBackground(res.getDrawable(R.mipmap.full_star));
                    view.setTag(1);
                } else {
                    view.setBackground(res.getDrawable(R.mipmap.empty_star));
                    view.setTag(0);
                }
            }
        });
    }
}

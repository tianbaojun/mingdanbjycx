package com.example.administrator.mingdanbjycx;

/**
 * Created by Administrator on 2015/12/18.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class UpdateListViewActivity extends Activity {
    // 控件
    private Button addBtn;
    private Button deleteBtn;
    private Button editBtn;
    private Button queryBtn;
    private ListView listview;
    // 数组
    private SimpleAdapter listItemAdapter;
    private ArrayList<HashMap<String, Object>> listItem = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 获取控件
        addBtn = (Button) findViewById(R.id.add_id);
        deleteBtn = (Button) findViewById(R.id.delete_id);
        editBtn = (Button) findViewById(R.id.edit_id);
        queryBtn = (Button) findViewById(R.id.query_id);
        listview = (ListView) findViewById(R.id.show_result);

        // 初始化数据
        init();

        // 设置控件事件监听
        addBtn.setOnClickListener(addClick);
        deleteBtn.setOnClickListener(deleteClick);
        editBtn.setOnClickListener(editClick);
        queryBtn.setOnClickListener(queryClick);

    }

    // 添加事件响应
    OnClickListener addClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            // 加载输入框的布局文件
            LayoutInflater inflater = (LayoutInflater) UpdateListViewActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout layout = (LinearLayout) inflater.inflate(
                    R.layout.input_add, null);

            // 弹出的对话框

            new AlertDialog.Builder(UpdateListViewActivity.this)
                    /* 弹出窗口的最上头文字 */
                    .setTitle("添加一条数据")
                    /* 设置弹出窗口的图式 */
                    .setIcon(android.R.drawable.ic_dialog_info)
                    /* 设置弹出窗口的信息 */
                    .setMessage("请输入添加的内容")
                    .setView(layout)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {

                                    EditText inputStringr = (EditText) layout
                                            .findViewById(R.id.input_add_string);

                                    String str = inputStringr.getText()
                                            .toString();

                                    if (str == null || str.equals("")) {

                                        Toast.makeText(getApplicationContext(),
                                                "添加的内容不能为空", Toast.LENGTH_SHORT)
                                                .show();
                                    } else {
                                        HashMap<String, Object> map = new HashMap<String, Object>();
                                        map.put("viewspot", str);
                                        map.put("add", R.drawable.right);
                                        listItem.add(0, map);
                                        // 如果在前面添加一条数据添加
                                        // listItem.add(map);
//                                        listItemAdapter.notifyDataSetChanged();
                                        Toast.makeText(
                                                UpdateListViewActivity.this,
                                                "添加的一条数据为:" + str + "",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() { /* 设置跳出窗口的返回事件 */
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    Toast.makeText(UpdateListViewActivity.this,
                                            "取消了删除数据", Toast.LENGTH_SHORT)
                                            .show();

                                }
                            }).show();

        }
    };

    // 删除事件响应
    OnClickListener deleteClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            /**
             * listItem.clear();清空所有数据
             *
             * */

            /*
             * listItem.clear();
             * listItemAdapter.notifyDataSetChanged();
             */

            // 加载输入框的布局文件
            LayoutInflater inflater = (LayoutInflater) UpdateListViewActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout layout = (LinearLayout) inflater.inflate(
                    R.layout.input_delete, null);

            // 弹出的对话框

            new AlertDialog.Builder(UpdateListViewActivity.this)
                    /* 弹出窗口的最上头文字 */
                    .setTitle("删除一条数据")
                    /* 设置弹出窗口的图式 */
                    .setIcon(android.R.drawable.ic_dialog_info)
                    /* 设置弹出窗口的信息 */
                    .setMessage("请输入删除的索引")
                    .setView(layout)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {

                                    EditText inputNumber = (EditText) layout
                                            .findViewById(R.id.input_delete_number);

                                    String str = inputNumber.getText()
                                            .toString();

                                    if (str == null || str.equals("")) {

                                        Toast.makeText(getApplicationContext(),
                                                "请输入一个数字", Toast.LENGTH_SHORT)
                                                .show();
                                    } else {
                                        int number = Integer.valueOf(str);

                                        int size = listItem.size();

                                        // 判断数字是否超出数组索引范围
                                        if (number >= size) {
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "没有找到删除的数据索引",
                                                    Toast.LENGTH_SHORT).show();

                                        } else {

                                            String value = listItem.get(number)
                                                    .toString();
                                            listItem.remove(number);
//                                            listItemAdapter
//                                                    .notifyDataSetChanged();
                                            Toast.makeText(
                                                    UpdateListViewActivity.this,
                                                    "删除的数据为:" + value + "",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() { /* 设置跳出窗口的返回事件 */
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    Toast.makeText(UpdateListViewActivity.this,
                                            "取消了删除数据", Toast.LENGTH_SHORT)
                                            .show();

                                }
                            }).show();

        }
    };
    // 修改事件响应
    OnClickListener editClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // 加载输入框的布局文件
            LayoutInflater inflater = (LayoutInflater) UpdateListViewActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout layout = (LinearLayout) inflater.inflate(
                    R.layout.input_edit, null);

            // 弹出的对话框

            new AlertDialog.Builder(UpdateListViewActivity.this)
                    /* 弹出窗口的最上头文字 */
                    .setTitle("修改一条数据")
                    /* 设置弹出窗口的图式 */
                    .setIcon(android.R.drawable.ic_dialog_info)
                    /* 设置弹出窗口的信息 */
                    .setMessage("请输入修改的索引及内容")
                    .setView(layout)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {

                                    EditText inputEditNumber = (EditText) layout
                                            .findViewById(R.id.input_edit_number);

                                    String numberStr = inputEditNumber
                                            .getText().toString();

                                    EditText inputEditString = (EditText) layout
                                            .findViewById(R.id.input_edit_string);

                                    String editStr = inputEditString.getText()
                                            .toString();

                                    if (numberStr == null
                                            || numberStr.equals("")) {

                                        Toast.makeText(getApplicationContext(),
                                                "请输入要修改的索引", Toast.LENGTH_SHORT)
                                                .show();
                                    } else {
                                        int number = Integer.valueOf(numberStr);

                                        int size = listItem.size();

                                        // 判断数字是否超出数组索引范围
                                        if (number >= size) {
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "没有找到修改的数据索引",
                                                    Toast.LENGTH_SHORT).show();

                                        } else {

                                            HashMap<String, Object> map = new HashMap<String, Object>();
                                            map.put("viewspot", editStr);
                                            map.put("add", R.drawable.right);

                                            listItem.set(number, map);
//                                            listItemAdapter
//                                                    .notifyDataSetChanged();

                                            Toast.makeText(
                                                    UpdateListViewActivity.this,
                                                    "数据修改为:" + editStr + "",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() { /* 设置跳出窗口的返回事件 */
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    Toast.makeText(UpdateListViewActivity.this,
                                            "取消了修改数据", Toast.LENGTH_SHORT)
                                            .show();

                                }
                            }).show();

        }
    };

    // 查询事件响应
    OnClickListener queryClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // 查询数据
            listItemAdapter.notifyDataSetChanged();
        }
    };

    // 初始化数据
    private void init() {

        listItem = new ArrayList<HashMap<String, Object>>();
        listItemAdapter = new SimpleAdapter(getApplicationContext(), listItem,// 数据源
                R.layout.items, new String[] { "viewspot", "add" }, new int[] {
                R.id.viewspot, R.id.add });
        listview.setAdapter(listItemAdapter);

    }

}

package com.allenxcai.contentproviderdemo;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.allenxcai.contentproviderdemo.adapter.ChapterAdapter;
import com.allenxcai.contentproviderdemo.biz.ChapterBiz;
import com.allenxcai.contentproviderdemo.entity.Chapter;
import com.allenxcai.contentproviderdemo.entity.ChapterLab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ExpandableListView mExpandableListView;

    private static final String         TAG    = "Imooc MainActivity";
    private              ChapterAdapter mAdapter;
    private              List<Chapter>  mDatas = new ArrayList<>();
    private              Button         mBtnAdd;
    private   static     ChapterBiz     mChapterBiz;
    private int activityType=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        inintEvent();
        mDatas = ChapterLab.generateDatas();

        mChapterBiz = new ChapterBiz(this);

        mAdapter = new ChapterAdapter(this, mDatas);
        mExpandableListView.setAdapter(mAdapter);

        mDatas.clear();
        mDatas.addAll(mChapterBiz.loadDatas(this));
        mAdapter.notifyDataSetChanged();

    }

    private void inintEvent() {
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                activityType =groupPosition;
                Log.d(TAG, "onGroupClick: groupPosition = " + groupPosition);
                return false;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                activityType =groupPosition;
                Log.d(TAG, "onChildClick: groupPosition = " + groupPosition + ", childPosition = " + childPosition +
                        ", id = " + id);
                return false;
            }
        });

        mExpandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            int groupPosition = -1;
            int childPosition = -1;
            int itemType = 0;

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                long packedPosition = mExpandableListView.getExpandableListPosition(position);

                itemType = ExpandableListView.getPackedPositionType(packedPosition);
                groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                childPosition = ExpandableListView.getPackedPositionChild(packedPosition);
                activityType =groupPosition;

                Log.d(TAG, "onItemLongClick: groupPosition:" + groupPosition + " childPosition:" + childPosition);

                /*  if group item clicked */
                if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    // do nothing

                }
                /*  if child item clicked */
                else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

                    AlertDialog isDelete = new AlertDialog.Builder(MainActivity.this).create();
                    isDelete.setButton(AlertDialog.BUTTON_POSITIVE, "否", listener);
                    isDelete.setButton(AlertDialog.BUTTON_NEGATIVE, "是", listener);
                    isDelete.setMessage("确定删除" + mDatas.get(groupPosition).getChildren().get(childPosition).getName() + "?");
                    isDelete.show();

                }
                return false;
            }


            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case AlertDialog.BUTTON_POSITIVE:
                            Log.d(TAG, "onClick: 否");
                            break;

                        case AlertDialog.BUTTON_NEGATIVE:
                            Log.d(TAG, "onClick: 是");
                            int id = mChapterBiz.mChapterDao.delete("dish_id=?", new String[]{mDatas.get(groupPosition).getChildren().get(childPosition).getId() + ""});

                            if (id == 1) {//db operate successed
                                mDatas.get(groupPosition).getChildren().remove(childPosition);
                                mAdapter.notifyDataSetChanged();

                                Log.d(TAG, "onClick: 成功删除了数据");
                                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }


                }
            };


        });


        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            // 收回
            @Override
            public void onGroupCollapse(int groupPosition) {

                Log.d(TAG, "onGroupCollapse groupPosition = " + groupPosition);

            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            // 展开
            @Override
            public void onGroupExpand(int groupPosition) {
                activityType =groupPosition;
                Log.d(TAG, "onGroupExpand groupPosition = " + groupPosition);

            }
        });

        mExpandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick position = " + position);

            }
        });

        mBtnAdd.setOnClickListener(this);

    }

    private void initViews() {
        mExpandableListView = findViewById(R.id.id_expandable_listview);
        mBtnAdd = findViewById(R.id.btn_add);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add:

                Intent it = new Intent(this, AddActivity.class);
                it.putExtra("Type",mDatas.get(activityType).getName());
                it.putExtra("menuType",mChapterBiz.getMenuType());
                startActivity(it);
                break;
        }

    }

    public static void addRecorder(ContentValues values){


        mChapterBiz.mChapterDao.insert(values);
        Log.d(TAG, "addRecorder: ");
    }
}

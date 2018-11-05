package com.allenxcai.contentproviderdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvBack;
    private EditText mEdtName;
    private Spinner  mSpType;
    private Button   mBtnAdd;

    private List<CharSequence>         menuTypeList    = null;
    private ArrayAdapter<CharSequence> menuTypeAdapter = null;
    Intent it;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initViews();
        it = getIntent();

        String menuType[] = it.getStringArrayExtra("menuType");
        menuTypeList = new ArrayList<CharSequence>();

        for (int i = 0; i < menuType.length; i++) {
            menuTypeList.add(menuType[i]);
        }


        menuTypeAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, menuTypeList);
        menuTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpType.setAdapter(menuTypeAdapter);

        int position = menuTypeAdapter.getPosition(it.getStringExtra("Type"));    //根据该选项获取位置
        mSpType.setSelection(position);


        initEvents();

    }

    private void initViews() {

        mTvBack = findViewById(R.id.tv_back);
        mEdtName = findViewById(R.id.edt_name);
        mSpType = findViewById(R.id.sp_type);
        mBtnAdd = findViewById(R.id.btn_add);

    }


    private void initEvents() {
        //
        mTvBack.setOnClickListener(this);
        //
        mBtnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_back:
                this.finish();
                break;
            case R.id.btn_add:

                ContentValues values=new ContentValues();
                values.put("dish_name",mEdtName.getText().toString());

                String ss=(String) (mSpType.getItemAtPosition(mSpType.getSelectedItemPosition()));
                values.put("dish_type",ss);

                MainActivity.addRecorder(values);

                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

package com.allenxcai.contentproviderdemo.biz;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.TextView;

import com.allenxcai.contentproviderdemo.R;
import com.allenxcai.contentproviderdemo.dao.ChapterDao;
import com.allenxcai.contentproviderdemo.entity.Chapter;
import com.allenxcai.contentproviderdemo.entity.ChapterChild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:AXC 时间：2018/10/29
 * 项目名：ContentProviderDemo
 * 包名：com.allenxcai.contentproviderdemo.biz
 * 简述：
 */
public class ChapterBiz {
    private static final String TAG = "Im ChapterBiz";

    Context mContext;
    public ChapterDao mChapterDao;
    private String[]   menuType;
    private int[]      menuTypePid = {1, 2, 3, 4, 5, 6, 7, 8};

    public ChapterBiz(Context context) {

        mContext = context;
        mChapterDao = new ChapterDao(mContext, "content://com.imooc.menuprovider");
        menuType = mContext.getResources().getStringArray(R.array.MenuType);


    }

    public String[] getMenuType() {
        return menuType;
    }

    public void setMenuType(String[] menuType) {
        this.menuType = menuType;
    }

    public List<Chapter> loadDatas(final Context context) {

        return loadFromContentProvider(context);

    }

    private List<Chapter> loadFromContentProvider(Context context) {


        Cursor c = mChapterDao.query(null, null, null, null);

        final List<Chapter> chapters = parseContent(c);

        return chapters;
    }


    private List<Chapter> parseContent(Cursor c) {


        List<Chapter> chapters=new ArrayList<>();
        Chapter chapter;
        ChapterChild chapterChild;

        Cursor cur = c;

        for (int i = 0; i <menuType.length ; i++) {

            chapter=new Chapter(menuTypePid[i],menuType[i]);
            chapters.add(chapter);
        }


        while (cur.moveToNext()) {


            int id = cur.getInt(0);
            String name = cur.getString(1);
            String type = cur.getString(2);

            chapterChild =new ChapterChild(id,name);

            chapters.get(findid(type)).addChild(chapterChild);


        }

        return chapters;
    }

    private int findid(String Types) {

        for (int i = 0; i < menuType.length; i++) {

            if (menuType[i].equals(Types))
                return i;

        }

        return 0;

    }


}


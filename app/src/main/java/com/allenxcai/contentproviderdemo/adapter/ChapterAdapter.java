package com.allenxcai.contentproviderdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allenxcai.contentproviderdemo.R;
import com.allenxcai.contentproviderdemo.entity.Chapter;

import java.util.List;

/**
 * Project:ContentProviderDemo
 * Author:Allenxcai
 * Date:2018/10/28/028
 * Description:
 */
public class ChapterAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<Chapter> mDatas;
    private LayoutInflater mInflater;

    public ChapterAdapter(Context mContext, List<Chapter> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDatas.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {


        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ChapterViewHolder vh;
        if(convertView ==null){
            convertView = mInflater.inflate(R.layout.item_parent_chapter, parent, false);
            vh = new ChapterViewHolder();
            vh.mIv = convertView.findViewById(R.id.id_indicator_group);
            vh.mTv = convertView.findViewById(R.id.id_tv_parent);
            convertView.setTag(vh);

        }else
        {
            vh = (ChapterViewHolder) convertView.getTag();
        }
        vh.mTv.setText(mDatas.get(groupPosition).getName());
        vh.mIv.setSelected(isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChapterChildViewHolder vh;
        if(convertView ==null){
            convertView = mInflater.inflate(R.layout.item_child_chapter, parent, false);
            vh = new ChapterChildViewHolder();
            vh.mTv = convertView.findViewById(R.id.id_tv_child);
            convertView.setTag(vh);

        }else
        {
            vh = (ChapterChildViewHolder) convertView.getTag();
        }

        vh.mTv.setText(mDatas.get(groupPosition).getChildren().get(childPosition).getName());

      return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static class ChapterViewHolder {
        ImageView mIv;
        TextView mTv;
    }

    public static class ChapterChildViewHolder {
        TextView mTv;
    }
}

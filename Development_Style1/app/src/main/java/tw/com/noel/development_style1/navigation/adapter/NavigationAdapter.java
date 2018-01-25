package tw.com.noel.development_style1.navigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.com.noel.development_style1.R;
import tw.com.noel.development_style1.navigation.model.NavigationData;

/**
 * Created by noel on 2018/1/25.
 */

public class NavigationAdapter extends BaseExpandableListAdapter {

    private ArrayList<NavigationData> navigationDatas;

    public NavigationAdapter() {
        navigationDatas = new ArrayList<>();
    }

    public void setData(ArrayList<NavigationData> navigationDatas) {
        this.navigationDatas = navigationDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return navigationDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (navigationDatas.get(groupPosition).getChildBeans() == null) {
            return 0;
        } else
            return navigationDatas.get(groupPosition).getChildBeans().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder headerViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_navigation_group, parent, false);
            headerViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (GroupViewHolder) convertView.getTag();
        }


        headerViewHolder.imgArrow.setVisibility(getChildrenCount(groupPosition) == 0 ? View.INVISIBLE : View.VISIBLE);
        headerViewHolder.imgArrow.setBackgroundResource(isExpanded?R.drawable.ic_bottom_arrow:R.drawable.ic_top_arrow);
        headerViewHolder.tvItem.setText(navigationDatas.get(groupPosition).getGroupName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childlViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_navigation_child, parent, false);
            childlViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childlViewHolder);
        } else {
            childlViewHolder = (ChildViewHolder) convertView.getTag();
        }

        childlViewHolder.tvItem.setText(navigationDatas.get(groupPosition).getChildBeans().get(childPosition).getChildName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;//回傳true 才能點選
    }

    //-------
    static class GroupViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        @BindView(R.id.img_arrow)
        ImageView imgArrow;


        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}


package tw.com.noel.development_style1.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tw.com.noel.development_style1.R;
import tw.com.noel.development_style1.basic.BasicFragment;
import tw.com.noel.development_style1.home.HomeFragment;
import tw.com.noel.development_style1.navigation.adapter.NavigationAdapter;
import tw.com.noel.development_style1.navigation.model.NavigationData;
import tw.com.noel.development_style1.second.SecondFragment;
import tw.com.noel.development_style1.third.apple.AppleFragment;
import tw.com.noel.development_style1.third.banana.BananaFragment;
import tw.com.noel.development_style1.third.cat.CatFragment;

/**
 * Created by noel on 2018/1/25.
 */

public class NavigationFragment extends BasicFragment implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {

    @BindView(R.id.expandablelistview)
    ExpandableListView expandablelistview;
    private View view;
    private NavigationAdapter navigationAdapter;
    private ArrayList<NavigationData> navigationDatas;

    private HomeFragment homeFragment;
    private SecondFragment secondFragment;
    private AppleFragment appleFragment;
    private BananaFragment bananaFragment;
    private CatFragment catFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_navigation, container, false);
            ButterKnife.bind(this, view);
            init();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }
    //---------------

    /**
     * 起始點
     */
    private void init() {

        navigationAdapter = new NavigationAdapter();
        expandablelistview.setAdapter(navigationAdapter);
        expandablelistview.setGroupIndicator(null);
        expandablelistview.setOnGroupClickListener(this);
        expandablelistview.setOnChildClickListener(this);
        initData();

    }

    //---------------

    /***
     * 資料
     */
    private void initData() {
        navigationDatas = new ArrayList<>();
        NavigationData navigationData = new NavigationData();

        navigationData.setGroupName("HOME");
        navigationDatas.add(navigationData);


        navigationData = new NavigationData();
        navigationData.setGroupName("SECOND");
        navigationDatas.add(navigationData);


        navigationData = new NavigationData();
        navigationData.setGroupName("THIRD");
        navigationData.setChildBeans(getChildData());
        navigationDatas.add(navigationData);


        navigationAdapter.setData(navigationDatas);
    }


    private ArrayList<NavigationData.ChildBean> getChildData() {
        ArrayList<NavigationData.ChildBean> childBeans = new ArrayList<>();
        NavigationData.ChildBean childBean = new NavigationData.ChildBean();
        childBean.setChildName("APPLE");
        childBeans.add(childBean);
        childBean = new NavigationData.ChildBean();
        childBean.setChildName("BANANA");
        childBeans.add(childBean);
        childBean = new NavigationData.ChildBean();
        childBean.setChildName("CAT");
        childBeans.add(childBean);

        return childBeans;
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int position, long l) {

        switch (position) {
            //home
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                activity.drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(R.id.container_framelayout, homeFragment, false);
                break;

            //second
            case 1:
                if (secondFragment == null) {
                    secondFragment = new SecondFragment();
                }
                activity.drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(R.id.container_framelayout, secondFragment, false);
                break;
        }
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {

        //Third
        if (groupPosition == 2) {

            activity.drawerLayout.closeDrawer(GravityCompat.START);

            Fragment fragment;
            switch (childPosition) {
                //apple
                case 0:
                    if (appleFragment == null) {
                        appleFragment = new AppleFragment();
                    }
                    fragment = appleFragment;
                    break;

                //banana
                case 1:
                    if (bananaFragment == null) {
                        bananaFragment = new BananaFragment();
                    }
                    fragment = bananaFragment;
                    break;

                //cat
                default:
                case 2:
                    if (catFragment == null) {
                        catFragment = new CatFragment();
                    }
                    fragment = catFragment;
                    break;
            }
            replaceFragment(R.id.container_framelayout, fragment, false);
        }
        return false;
    }
}

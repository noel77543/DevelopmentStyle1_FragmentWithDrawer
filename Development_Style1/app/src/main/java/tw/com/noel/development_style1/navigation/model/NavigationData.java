package tw.com.noel.development_style1.navigation.model;

import java.util.ArrayList;

/**
 * Created by noel on 2018/1/25.
 */

public class NavigationData {

    private String groupName;
    private ArrayList<ChildBean> childBeans;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<ChildBean> getChildBeans() {
        return childBeans;
    }

    public void setChildBeans(ArrayList<ChildBean> childBeans) {
        this.childBeans = childBeans;
    }

    public static class ChildBean {

        private String childName;

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }
}

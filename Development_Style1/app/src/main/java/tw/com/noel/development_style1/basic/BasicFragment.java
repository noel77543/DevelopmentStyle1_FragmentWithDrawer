package tw.com.noel.development_style1.basic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import tw.com.noel.development_style1.MainActivity;

/**
 * Created by noel on 2018/1/24.
 */

public class BasicFragment extends Fragment {
    public MainActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
    }

    //-----------
    /**
     * 點選navigation 項目 替換 右側 container frame layout
     * */
    public void replaceFragment(int layoutId, Fragment fragment, boolean addToBackStack) {
        closeKeyboard();

        FragmentTransaction transaction;
        transaction = getFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();

        if (getFragmentManager() != null) {
            getFragmentManager().executePendingTransactions();
        }
    }


//    -----------------

    /**
     * Fragment裡面又有包含Fragment的狀況時使用, 無bundle
     */
    public void replaceInnerFragment(int layoutId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction;
        transaction = getFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();

        if (getChildFragmentManager() != null) {
            getChildFragmentManager().executePendingTransactions();
        }
    }
    //-----------------

    /**
     * Fragment裡面又有包含Fragment的狀況時使用, 有bundle
     */
    public void replaceInnerFragment(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction;
        transaction = getFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();

        if (getChildFragmentManager() != null) {
            getChildFragmentManager().executePendingTransactions();
        }
    }


    //-------------

    /**
     * 關閉虛擬鍵盤
     */
    public void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

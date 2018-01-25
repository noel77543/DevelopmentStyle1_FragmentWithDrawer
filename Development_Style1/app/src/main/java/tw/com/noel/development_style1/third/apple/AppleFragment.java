package tw.com.noel.development_style1.third.apple;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import tw.com.noel.development_style1.R;
import tw.com.noel.development_style1.basic.BasicFragment;

/**
 * Created by noel on 2018/1/25.
 */

public class AppleFragment extends BasicFragment{
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_apple, container, false);
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

    private void init() {
        Log.e("apple","apple");
    }
}

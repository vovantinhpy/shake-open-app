package com.tinhvv.shakeoa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinhvv.shakeoa.R;

/**
 * Created by Ray on 3/20/2016.
 */
public class ShakeOAFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, viewGroup, false);

        return view;
    }
}

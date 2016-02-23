package com.tinhvv.shakeoa.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinhvv.shakeoa.R;

/**
 * Created by ray-jason on 22/02/2016.
 */
public class ShakeOAFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    public static final ShakeOAFragment newInstance(String message)
    {
        ShakeOAFragment fragment = new ShakeOAFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting_shake, container, false);

        return v;
    }
}

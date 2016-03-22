package com.tinhvv.shakeoa.fragment;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tinhvv.shakeoa.R;
import com.tinhvv.shakeoa.util.Constant;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ray-jason on 22/02/2016.
 */
public class ShakeFragment extends Fragment implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final int DEFAULT_POSITION = 0;
    private String[] mArrayAction;
    private List<ApplicationInfo> mApplicationInstalledList;

    private Spinner mSpinnerHorizonal, mSpinnerVertical, mSpinnerBehind, mSpinnerForward;
    private Switch mSwitchDoNothing;
    private Button mBtnChooseApp, mBtnChooseContact, mBtnInputPhoneNo;
    private LinearLayout mLinearLayoutHorizonal, mLinearLayoutVertial, mLinearLayoutBehind, mLinearLayoutForward;
    private TextView mTxtAddData;
    private CircleImageView mImageApp;

    public static final ShakeFragment newInstance(String message) {
        ShakeFragment fragment = new ShakeFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting_shake, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initValues();
        initListeners();
    }

    private void initViews(View v) {
        if (v != null) {
            //Spinner
            mSwitchDoNothing = (Switch) v.findViewById(R.id.switch_do_nothing);
            mSpinnerHorizonal = (Spinner) v.findViewById(R.id.spinner_horizonal);
            mSpinnerVertical = (Spinner) v.findViewById(R.id.spinner_vertial);
            mSpinnerBehind = (Spinner) v.findViewById(R.id.spinner_behind);
            mSpinnerForward = (Spinner) v.findViewById(R.id.spinner_forward);

            // Add layout
            mLinearLayoutHorizonal = (LinearLayout) v.findViewById(R.id.ll_add_horizonal_shaken);
            mLinearLayoutVertial = (LinearLayout) v.findViewById(R.id.ll_add_vertail_shaken);
            mLinearLayoutBehind = (LinearLayout) v.findViewById(R.id.ll_add_behind_shaken);
            mLinearLayoutForward = (LinearLayout) v.findViewById(R.id.ll_add_forward_shaken);
        }
    }

    private void initValues() {
        mArrayAction = getArrayAction();
        if (mArrayAction != null) {
            SpinnerAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,
                    mArrayAction);
            if (mSpinnerHorizonal != null) {
                mSpinnerHorizonal.setAdapter(adapter);
            }
            if (mSpinnerVertical != null) {
                mSpinnerVertical.setAdapter(adapter);
            }
            if (mSpinnerBehind != null) {
                mSpinnerBehind.setAdapter(adapter);
            }
            if (mSpinnerForward != null) {
                mSpinnerForward.setAdapter(adapter);
            }
        }
    }

    private void initListeners() {
        mSwitchDoNothing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String temp = "";
                if (isChecked) {
                    temp = "On";
                } else {
                    temp = "Off";
                }
                Toast.makeText(getActivity(), temp, Toast.LENGTH_SHORT).show();
            }
        });
        //Spinner
        mSpinnerHorizonal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initChooseInSpinner(mLinearLayoutHorizonal, position, DEFAULT_POSITION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerVertical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initChooseInSpinner(mLinearLayoutVertial, position, DEFAULT_POSITION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerBehind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initChooseInSpinner(mLinearLayoutBehind, position, DEFAULT_POSITION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerForward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initChooseInSpinner(mLinearLayoutForward, position, DEFAULT_POSITION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String[] getArrayAction() {
        return this.getActivity().getResources().getStringArray(R.array.array_action_shake);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnChooseApp) {

        } else if (v == mBtnChooseContact) {

        } else if (v == mBtnInputPhoneNo) {

        }
    }

    /**
     * <item>Nothing</item>
     * <item>Open App</item>
     * <item>Call a contact</item>
     * <item>Turn on/off Wifi</item>
     * <item>Turn on/off 3/4G</item>
     * <item>Turn on/off Flash</item>
     * <item>Turn on/off Screen</item>
     * <item>Switch ringer mode</item>
     *
     * @param linearLayoutAddTo
     * @param positionSelect
     * @param defaultPostion
     */
    private void initChooseInSpinner(LinearLayout linearLayoutAddTo, int positionSelect, int defaultPostion) {
        if (mArrayAction != null && positionSelect > defaultPostion) {
            if (linearLayoutAddTo != null) {
                linearLayoutAddTo.removeAllViews();
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                Constant.Action type = getTypeAction(positionSelect);
                if (type == Constant.Action.OPEN_APP) {
                    View view = inflater.inflate(R.layout.layout_open_app, null);
                    //init view
                    mBtnChooseApp = (Button) view.findViewById(R.id.btn_choose_app);
                    mImageApp = (CircleImageView) view.findViewById(R.id.image_icon_app);
//                    mImageApp.setVisibility(View.VISIBLE);
                    //Set Listener
                    mBtnChooseApp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    //
                    linearLayoutAddTo.addView(view);
                } else if (type == Constant.Action.CALL_CONTACT) {
                    View view = inflater.inflate(R.layout.layout_call_so, null);
                    mBtnChooseContact = (Button) view.findViewById(R.id.btn_choose_number);
                    mBtnInputPhoneNo = (Button) view.findViewById(R.id.btn_input_number);

                    linearLayoutAddTo.addView(view);
                } else {
                    View view = inflater.inflate(R.layout.layout_orther_action, null);
                    //init view
                    mTxtAddData = (TextView) view.findViewById(R.id.txt_value_show);
                    //
                    linearLayoutAddTo.addView(view);
                }
            }
        }
    }

    private Constant.Action getTypeAction(int position) {
        if (position == 0) {
            return Constant.Action.NOTHING;
        } else if (position == 1) {
            return Constant.Action.OPEN_APP;
        } else if (position == 2) {
            return Constant.Action.CALL_CONTACT;
        } else if (position == 3) {
            return Constant.Action.TURN_ON_OFF_WIFI;
        } else if (position == 4) {
            return Constant.Action.TURN_ON_OFF_3_4G;
        } else if (position == 5) {
            return Constant.Action.TURN_ON_OFF_FLASH;
        } else if (position == 6) {
            return Constant.Action.TURN_ON_OFF_SCREEN;
        } else if (position == 7) {
            return Constant.Action.SWITCH_RINGER_MODE;
        }
        return Constant.Action.NOTHING;
    }
}

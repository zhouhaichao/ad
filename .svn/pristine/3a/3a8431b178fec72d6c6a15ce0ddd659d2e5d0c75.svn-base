package com.smyhvae.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyhvae.myapplication.AboutUsActivity;
import com.smyhvae.myapplication.HelpActivity;
import com.smyhvae.myapplication.MyApplication;
import com.smyhvae.myapplication.PersonalInfoActivity;
import com.smyhvae.myapplication.PrintActivity;
import com.smyhvae.myapplication.R;


public class SettingsFragment extends Fragment implements View.OnClickListener {
    private MyApplication application;

    private LinearLayout print;
    private LinearLayout per_info;
    private LinearLayout help;
    private LinearLayout aboutUs;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        application = (MyApplication) getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment, container, false);
        TextView settings_name = view.findViewById(R.id.settings_name);
        TextView settings_code = view.findViewById(R.id.settings_code);
        TextView settings_role = view.findViewById(R.id.settings_role);
        TextView settings_invString = view.findViewById(R.id.settings_invString);
        settings_name.setText(application.getFuStaffModel().getName());
        settings_code.setText(application.getFuStaffModel().getCode());
        settings_role.setText(application.getFuStaffModel().getFuRoleList().get(0).getName());
        settings_invString.setText(application.getFuStaffModel().getFuInventoryList().get(0).getName());

        print = view.findViewById(R.id.print);
        per_info = view.findViewById(R.id.per_info);
        help = view.findViewById(R.id.help);
        aboutUs = view.findViewById(R.id.aboutUs);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        print.setOnClickListener(this);
        per_info.setOnClickListener(this);
        help.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.print:
                intent = new Intent(getActivity(), PrintActivity.class);
                startActivity(intent);
                break;
            case R.id.per_info:
                intent = new Intent(getActivity(), PersonalInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.help:
                intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.aboutUs:
                intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

package com.david0926.travity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.david0926.travity.LoginActivity;
import com.david0926.travity.R;
import com.david0926.travity.database.DataManagerKt;
import com.david0926.travity.databinding.FragmentMain4Binding;
import com.david0926.travity.model.Settings;
import com.david0926.travity.model.UserModel;
import com.david0926.travity.util.UserCache;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import javax.xml.transform.Result;

public class MainFragment4 extends Fragment {

    public static MainFragment4 newInstance() {
        return new MainFragment4();
    }

    private Context mContext;
    private FragmentMain4Binding binding;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main4, container, false);


        binding.btnMain4Logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(mContext, LoginActivity.class));

            Activity activity = getActivity();
            if (activity == null) return;
            activity.finish();
        });

        UserModel user = UserCache.getUser(getContext());

        DataManagerKt.getDocument("users", UserCache.getUser(getContext()).getEmail(), result -> { // FIREBASE에서  값 있으면 가져옴
            try {
                HashMap<String, Boolean> map = (HashMap<String, Boolean>) result.get("settings");
                if(map == null)
                    map = user.getSettings();

                map.put(Settings.ALARM_EVENT.toString(), true); // 해당 메뉴를 통해 설정값 바꿀 수 있음
                DataManagerKt.updateSettings(getContext(), map); // 바꾼 셋팅값 서버에 저장하려면 이 메소드로 저장
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        });


        return binding.getRoot();
    }
}

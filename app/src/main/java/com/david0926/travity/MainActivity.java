package com.david0926.travity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.david0926.travity.database.AccountManagerKt;
import com.david0926.travity.database.DataManagerKt;
import com.david0926.travity.databinding.ActivityMainBinding;
import com.david0926.travity.fragment.MainFragment1;
import com.david0926.travity.fragment.MainFragment2;
import com.david0926.travity.fragment.MainFragment3;
import com.david0926.travity.fragment.MainFragment4;
import com.david0926.travity.model.FlightModel;
import com.david0926.travity.model.NotificationModel;
import com.david0926.travity.model.TodoModel;
import com.david0926.travity.util.UserCache;

import java.util.ArrayList;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.bottomMain.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_1:
                    switchFragment(MainFragment1.newInstance());
                    break;
                case R.id.action_2:
                    switchFragment(MainFragment2.newInstance());
                    break;
                case R.id.action_3:
                    switchFragment(MainFragment3.newInstance());
                    break;
                case R.id.action_4:
                    switchFragment(MainFragment4.newInstance());
                    break;
            }
            return true;
        });

        switchFragment(MainFragment1.newInstance());
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }
}
package com.doodhbhandaar.dbadmin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TaskBottomActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String a="jkbbdjk";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Toast.makeText(TaskBottomActivity.this,item.getItemId()+"",Toast.LENGTH_LONG).show();
            Fragment fragment=new CustomersFragment();;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment=new CustomersFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment=new ListofAllDeliveryAreasFragment();
                    break;
                case R.id.navigation_notifications:
                    return false;
            }
            Toast.makeText(TaskBottomActivity.this,item.getItemId()+"",Toast.LENGTH_LONG).show();
            setFragment(fragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_bottom);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setFragment(new CustomersFragment());
    }

    private void setFragment(Fragment fragment) {
        Toast.makeText(TaskBottomActivity.this,fragment+"",Toast.LENGTH_LONG).show();

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.launcher_Container,fragment);
        fragmentTransaction.commit();

    }

}

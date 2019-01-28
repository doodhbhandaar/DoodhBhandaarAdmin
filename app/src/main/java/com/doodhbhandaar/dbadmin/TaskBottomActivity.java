package com.doodhbhandaar.dbadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class TaskBottomActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String a="jkbbdjk";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Toast.makeText(TaskBottomActivity.this,item.getItemId()+"",Toast.LENGTH_LONG).show();
            Fragment fragment=new CustomersFragment();;
            switch (item.getItemId()) {
                case R.id.list_of_all_customers:
                    setTitle("List of All Customers");
                    fragment=new CustomersFragment();
//                    Toast.makeText(TaskBottomActivity.this,"Customer Fragment",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.list_of_all_delivery_area:
                    setTitle("List of All Delivery Areas");
                    fragment=new ListofAllDeliveryAreasFragment();
                    break;
                case R.id.Logout:
                    // Code for logout
                    setTitle("Logout");
                    AlertDialog.Builder builder = new AlertDialog.Builder(TaskBottomActivity.this);

                    builder.setTitle("Logout");
                    builder.setCancelable(true);
                    builder.setMessage("Are you sure");
                    builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            getInstance().signOut();
                            Intent intent = new Intent(TaskBottomActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //TODO
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    break;
                case R.id.navigation_notifications2:
                    // Code for logout
                    setTitle("List of All Area");
                    fragment=new ListOfAllAreasFragment();
                    break;
                case R.id.list_of_all_delivery_boys:
                    return false;

                 default: return false;

            }
//            Toast.makeText(TaskBottomActivity.this,item.getItemId()+"",Toast.LENGTH_LONG).show();
            setFragment(fragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_bottom);
        setTitle("List of All Customers");
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setFragment(new CustomersFragment());
    }

    private void setFragment(Fragment fragment) {
//        Toast.makeText(TaskBottomActivity.this,fragment+"",Toast.LENGTH_LONG).show();

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.launcher_Container,fragment);
        fragmentTransaction.commit();

    }

}

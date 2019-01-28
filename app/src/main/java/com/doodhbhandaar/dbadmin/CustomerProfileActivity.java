package com.doodhbhandaar.dbadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CustomerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String address=bundle.getString("Address");
        String pno=bundle.getString("PhoneNo");
        TextView NameText=(TextView)findViewById(R.id.textView);
        NameText.setText(name);
        TextView AddressText=(TextView)findViewById(R.id.Addresstext);
        AddressText.setText(address);
        TextView Pno=(TextView)findViewById(R.id.pnotext);
        Pno.setText(pno);

    }
}

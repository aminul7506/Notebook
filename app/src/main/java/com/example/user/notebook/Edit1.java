package com.example.user.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 3/24/2016.
 */
public class Edit1 extends AppCompatActivity {
    Button edit1;
    TextView title1,notes1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtext);
        edit1 = (Button) findViewById(R.id.edit1);
        notes1 = (TextView) findViewById(R.id.notes1);
        title1 = (TextView) findViewById(R.id.title1);
        title1.setText(listView.tit);
        notes1.setText(listView.not);
        //title1.setEnabled(false);
        //notes1.setEnabled(false);
        edit1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit1.this,Edit2.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        listView.tit = "";
        listView.tit1 = "";
        listView.not = "";
        Intent intent=new Intent(Edit1.this,listView.class);
        startActivity(intent);
    }

}

package com.example.user.notebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by User on 3/25/2016.
 */
public class Edit2  extends AppCompatActivity {
    Button modify;
    EditText title2,notes2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        modify = (Button) findViewById(R.id.modify);
        notes2 = (EditText) findViewById(R.id.notes2);
        title2 = (EditText) findViewById(R.id.title2);
        title2.setText(listView.tit);
        notes2.setText(listView.not);
        title2.setSelection(title2.getText().length());
        notes2.setSelection(notes2.getText().length());
        //title2.setEnabled(false);
        //notes2.setEnabled(false);
        modify.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Edit2.this,Edit.class);
                //startActivity(intent);
                deleteFile(listView.tit1);
                String fileName1 = title2.getText().toString();
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
                String formattedDate = df.format(c.getTime());
                String content = notes2.getText().toString();
                if(fileName1.matches(""))fileName1 = "No Title";
                String fileName = fileName1 + "\n" + formattedDate + "  ";
                if(content.matches(""))content = "No notes!";
                FileOutputStream fos;
                try {
                    fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();

                    Toast.makeText(
                            Edit2.this,
                            fileName1 + " modified!",
                            Toast.LENGTH_LONG).show();
                    listView.tit = "";
                    listView.tit1 = "";
                    listView.not = "";
                    Intent intent = new Intent(com.example.user.notebook.Edit2.this, listView.class);
                    startActivity(intent);

                }catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
        });
    }
    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        listView.tit = "";
        listView.tit1 = "";
        listView.not = "";
        Intent intent=new Intent(Edit2.this,listView.class);
        startActivity(intent);
    }

}


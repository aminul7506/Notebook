package com.example.user.notebook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ArrayList;

/**
 * Created by User on 3/24/2016.
 */
public class Edit extends AppCompatActivity {

    Button save;
    EditText title, notes;
    static String tit1,not1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        save = (Button) findViewById(R.id.save);
        title = (EditText) findViewById(R.id.title);
        notes = (EditText) findViewById(R.id.notes);
       // final ArrayAdapter<String> myAdapter = new
       /* listView.myAdapter = new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listView.ab);*/
        save.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //button on press method

                /*Intent intent = new Intent(com.example.user.notebook.Edit.this, listView.class);
                startActivity(intent);
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                String formattedDate = df.format(c.getTime());
                listView.ab.add(title.getText().toString() + "\n" + formattedDate);
                listView.listView.setAdapter(listView.myAdapter);*/
                //      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
                String fileName1 = title.getText().toString();
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
                String formattedDate = df.format(c.getTime());
                String content = notes.getText().toString();
                if(fileName1.matches(""))fileName1 = "No Title";
                String fileName = fileName1 + "\n" + formattedDate + "  ";
                if(content.matches(""))content = "No notes!";
                FileOutputStream fos;
                try {
                    fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();

                    Toast.makeText(
                            Edit.this,
                            fileName1 + " saved",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(com.example.user.notebook.Edit.this, listView.class);
                    startActivity(intent);

                } catch (FileNotFoundException e) {
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
        Intent intent=new Intent(Edit.this,listView.class);
        startActivity(intent);
    }

}
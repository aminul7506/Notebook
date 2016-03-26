package com.example.user.notebook;

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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class listView extends AppCompatActivity {
    Button create,delete;
     ListView listView;
    String SavedFiles[] = new String[100];
    int i = 0;
    boolean log = false;
    int pos;
    int backButtonCount = 0;
    static  String tit = "" ,not = "",tit1 = "";
  ArrayList<String> ab = new ArrayList<String>();
    String myStringArray[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
     ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        create = (Button) findViewById(R.id.create);
        delete = (Button) findViewById(R.id.delete);
        listView = (ListView) findViewById(R.id.listView);

    showSavedFiles();

        //listView.setAdapter(myAdapter);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        create.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //button on press method
               /* Calendar c = Calendar.getInstance();
                System.out.println("Current time => "+c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                String formattedDate = df.format(c.getTime());
                ab.add("A" + i + "\n" + formattedDate);
                i++;
                listView.setAdapter(myAdapter);*/
                Intent intent = new Intent(com.example.user.notebook.listView.this, Edit.class);
                startActivity(intent);
            }
        });
       /*AdapterView.OnItemClickListener listSavedFilesOnItemClickListener
                = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                String clickedFile = (String) parent.getItemAtPosition(position);
                OpenFileDialog(clickedFile);
            }

        };*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                            {
                                                @Override
                                                public void onItemClick(AdapterView<?> a, View v, int position,
                                                                               long id) {
//                listView.removeViewAt(position);
                                                    pos = position;


                                                    // SavedFiles[position] = null;
                                                    // Toast.makeText(listView.this, "Item has been added into your list", Toast.LENGTH_LONG)
                                                    //       .show();
                                                    //showSavedFiles();
                                                    AlertDialog alertDialog = new AlertDialog.Builder(listView.this).create(); //Read Update
                                                    alertDialog.setTitle("Action");
                                                    alertDialog.setMessage("What do you want?");
                                                    //alertDialog.setPositiveButton("OK", null);
                                                    //alertDialog.setNegativeButton("Cancel", null);

                                                    alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Delete", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            String name = ab.get(pos);
                                                            deleteFile(ab.get(pos));
                                                            ab.remove(pos);
                                                            myAdapter.notifyDataSetChanged();
                                                            String name1 = name.substring(0,name.length()-26);
                                                            Toast.makeText(
                                                                    listView.this,
                                                                    name1 + " deleted",
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                                    alertDialog.setButton(Dialog.BUTTON_POSITIVE, "View", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            String name = ab.get(pos);
                                                            tit1 = name;
                                                            String name1 = name.substring(0, name.length() - 26);
                                                            tit += name1;
                                                            FileInputStream fis;
                                                            try {
                                                                fis = openFileInput(ab.get(pos));
                                                                byte[] input = new byte[fis.available()];
                                                                while (fis.read(input) != -1) {
                                                                }
                                                                not += new String(input);
                                                            } catch (FileNotFoundException e) {
                                                                e.printStackTrace();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                            Intent intent = new Intent(com.example.user.notebook.listView.this, Edit1.class);
                                                            startActivity(intent);


                                                        }
                                                    });


                                                    alertDialog.show();
                                                   // return false;
                                                }

                                            }

        );


        delete.setOnClickListener(new View.OnClickListener()

                                  {


                                      @Override
                                      public void onClick(View v) {
                                          String f = "";
                                          for (String s : fileList()) {
                                              //if(!s.equals("listView.txt") || !s.equals("Edit1.txt") || !s.equals("Edit.txt"))ab.add(s);

                                              if (s.endsWith("  ")) {

                                                  f += s;
                                              }
                                          }
                                          if (myAdapter.isEmpty()) {
                                              Toast.makeText(getApplicationContext(), "Nothing to be deleted!",
                                                      Toast.LENGTH_SHORT).show();
                                              listView.setAdapter(null);
                                          } else if (f.matches("")) {
                                              Toast.makeText(getApplicationContext(), "Nothing to be deleted!",
                                                      Toast.LENGTH_SHORT).show();
                                              listView.setAdapter(null);
                                          } else {
                                              AlertDialog alertDialog = new AlertDialog.Builder(listView.this).create(); //Read Update
                                              alertDialog.setTitle("DELETE ALL");
                                              alertDialog.setMessage("Are you sure you want to delete all?");
                                              //alertDialog.setPositiveButton("OK", null);
                                              //alertDialog.setNegativeButton("Cancel", null);

                                              alertDialog.setButton(Dialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      listView.setAdapter(null);
                                                      i = 0;
                                                      //ab.clear();
                                                      for (String s : fileList()) {
                                                          deleteFile(s);
                                                      }
                                                      Toast.makeText(
                                                              listView.this,
                                                              " All notes deleted!!",
                                                              Toast.LENGTH_LONG).show();

                                                  }
                                              });
                                              alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                                                  public void onClick(DialogInterface dialog, int which) {
                                                  }
                                              });


                                              alertDialog.show();
                                              //button on press method
                                              // listView.setAdapter(null);
                                              //i = 0;
                                              //ab.clear();
                                          }
                                      }

                                  }

        );
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
//                listView.removeViewAt(position);

                    tit = ab.get(position);
                    FileInputStream fis;
                    try {
                        fis = openFileInput(tit);
                        byte[] input = new byte[fis.available()];
                        while (fis.read(input) != -1) {
                        }
                        not += new String(input);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(com.example.user.notebook.listView.this, Edit1.class);
                    startActivity(intent);


            }
        });*/


        }

    public void showSavedFiles() {

                for (String s : fileList()) {
                    //if(!s.equals("listView.txt") || !s.equals("Edit1.txt") || !s.equals("Edit.txt"))ab.add(s);

                    if (s.endsWith("  ")) {

                        ab.add(s);
                    }
                }

                myAdapter = new
                        ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        ab);

                listView.setAdapter(myAdapter);
            }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_list_view, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }
    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            backButtonCount = 0;
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
    /*@Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        tit = "";
        tit1 = "";
        not = "";
       // Intent intent=new Intent(Edit.this,listView.class);
        //startActivity(intent);
        System.exit(0);
        finish();
        moveTaskToBack(true);
    }*/

}

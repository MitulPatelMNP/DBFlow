package com.m2.developer.DBFlow;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.m2.developer.DBFlow.DBConfig.Dbconfig;
import com.m2.developer.DBFlow.ModelTable.Subjects;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView add;
    ListView list;

    List<Subjects> dataList;
    String dbname, dblastname, dbdis, dbcommant;
    Boolean EditTextEmptyHold;
    TextView TVVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TVVV =findViewById(R.id.TVVV);
        add = findViewById(R.id.add);
        list =findViewById(R.id.list_add_item);

        String tv = getIntent().getStringExtra("DATA_PASS");
        dataList = new ArrayList<>();
        TVVV.setText(tv);
        ShowSQLiteDBdata();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_item);
                dialog.setTitle("Add Item");

                final EditText name =  dialog.findViewById(R.id.name);
                final EditText last_name =  dialog.findViewById(R.id.last_name);
                final EditText dis =  dialog.findViewById(R.id.dis);
                final EditText commant =  dialog.findViewById(R.id.commant);
                Button save =  dialog.findViewById(R.id.save);

                dialog.show();
                ShowSQLiteDBdata();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (name.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                        } else if (last_name.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                        } else if (dis.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "Enter Discpriction", Toast.LENGTH_SHORT).show();
                        } else if (commant.getText().toString().trim().length() == 0) {
                            Toast.makeText(MainActivity.this, "Enter Commant", Toast.LENGTH_SHORT).show();
                        } else {

                            dbname = name.getText().toString();
                            dblastname = last_name.getText().toString();
                            dbdis = dis.getText().toString();
                            dbcommant = commant.getText().toString();

                            if (TextUtils.isEmpty(dbname) || TextUtils.isEmpty(dblastname) || TextUtils.isEmpty(dbdis) || TextUtils.isEmpty(dbcommant)) {
                                EditTextEmptyHold = false;
                            } else {
                                EditTextEmptyHold = true;
                            }

                            if (EditTextEmptyHold == true) {

                                Subjects subjects = new Subjects();

                                subjects.setName(dbname);
                                subjects.setLastname(dblastname);
                                subjects.setDis(dbdis);
                                subjects.setCommant(dbcommant);

                                boolean addUserResponse = Dbconfig.InsertData(subjects);
                                ShowSQLiteDBdata();
                                dialog.dismiss();

                                Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(MainActivity.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

                            }

                            name.getText().clear();

                            last_name.getText().clear();

                            dis.getText().clear();

                            commant.getText().clear();

                        }


                    }
                });
            }
        });


    }
    public void ShowSQLiteDBdata() {
        dataList = Dbconfig.GetAllData();
        SQLiteListAdapter doctorAdapter = new SQLiteListAdapter(MainActivity.this, dataList);
        list.setAdapter(doctorAdapter);
    }
}

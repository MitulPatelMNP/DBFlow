package com.m2.developer.DBFlow;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.m2.developer.DBFlow.DBConfig.Dbconfig;
import com.m2.developer.DBFlow.ModelTable.Subjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M2-Developer.
 */

class SQLiteListAdapter extends BaseAdapter{
    MainActivity context;

    private List<Subjects> ModelList;

    String dbupname, dbuplastname, dbupdis, dbupcommant,SQLiteDataBaseQueryHolder;
    Boolean EditTextEmptyHoldUpdate;

    public SQLiteListAdapter(MainActivity mainActivity, List<Subjects> dataList) {
        this.context = mainActivity;
        this.ModelList = dataList;
    }

    @Override
    public int getCount() {
        return ModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View child, ViewGroup parent) {
        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewname = (TextView) child.findViewById(R.id.textViewName);
            holder.textviewlastname = (TextView) child.findViewById(R.id.textViewLastName);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }


        holder.textviewname.setText(ModelList.get(position).getName());
        holder.textviewlastname.setText(ModelList.get(position).getLastname());

        int st = ModelList.get(position).getId();
        final int str = st;
        final int abc = str +1;
        ModelList = new ArrayList<>();
        ModelList=Dbconfig.GetAllData();
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(context);
                dialog1.setContentView(R.layout.update_item);
                dialog1.setTitle("Add Item");
                dialog1.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

                dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final EditText upname = (EditText) dialog1.findViewById(R.id.upname);
                final EditText uplast_name = (EditText) dialog1.findViewById(R.id.uplast_name);
                final EditText updis = (EditText) dialog1.findViewById(R.id.updis);
                final EditText upcommant = (EditText) dialog1.findViewById(R.id.upcommant);
                Button upsave = (Button) dialog1.findViewById(R.id.upsave);
                Button updelete = (Button) dialog1.findViewById(R.id.updelete);

                Subjects mkmk = Dbconfig.GetData(ModelList.get(position).getId());

                upname.setText(mkmk.getName());
                uplast_name.setText(mkmk.getLastname());
                updis.setText(mkmk.getDis());
                upcommant.setText(mkmk.getCommant());

                upsave.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        if (upname.getText().toString().trim().length() == 0) {
                            Toast.makeText(context, "Enter Name", Toast.LENGTH_SHORT).show();
                        } else if (uplast_name.getText().toString().trim().length() == 0) {
                            Toast.makeText(context, "Enter Last Name", Toast.LENGTH_SHORT).show();
                        } else if (updis.getText().toString().trim().length() == 0) {
                            Toast.makeText(context, "Enter Discpriction", Toast.LENGTH_SHORT).show();
                        } else if (upcommant.getText().toString().trim().length() == 0) {
                            Toast.makeText(context, "Enter Commant", Toast.LENGTH_SHORT).show();
                        } else {
                            dbupname = upname.getText().toString();
                            dbuplastname = uplast_name.getText().toString();
                            dbupdis = updis.getText().toString();
                            dbupcommant = upcommant.getText().toString();

                            if (TextUtils.isEmpty(dbupname) || TextUtils.isEmpty(dbuplastname) || TextUtils.isEmpty(dbupdis) || TextUtils.isEmpty(dbupcommant)) {
                                EditTextEmptyHoldUpdate = false;
                            } else {
                                EditTextEmptyHoldUpdate = true;
                            }

                            if (EditTextEmptyHoldUpdate == true) {
                                int idstr = ModelList.get(position).getId();
                                Subjects upDateSubjects = new Subjects();

                                upDateSubjects.setName(dbupname);
                                upDateSubjects.setLastname(dbuplastname);
                                upDateSubjects.setDis(dbupdis);
                                upDateSubjects.setCommant(dbupcommant);

                                boolean EditUserResponse = Dbconfig.UpDateData(upDateSubjects,idstr);
                                notifyDataSetChanged();
                                context.ShowSQLiteDBdata();
                                dialog1.dismiss();

                                Toast.makeText(context, "Data UpDate Successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                });

                updelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int idstr = ModelList.get(position).getId();
                        boolean result = Dbconfig.DeleteData(idstr);
                        context.ShowSQLiteDBdata();
                        Toast.makeText(context, "Data Delete Successfully", Toast.LENGTH_LONG).show();
                        notifyDataSetChanged();
                        dialog1.dismiss();
                    }
                });


                dialog1.show();
            }
        });
        return child;
    }

    public class Holder {

        String textViewId;
        TextView textviewname;
        TextView textviewlastname;
    }

}

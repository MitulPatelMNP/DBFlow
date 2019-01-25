package com.m2.developer.DBFlow.DBConfig;

import android.util.Log;

import com.m2.developer.DBFlow.ModelTable.Subjects;
import com.m2.developer.DBFlow.ModelTable.Subjects_Table;
import com.raizlabs.android.dbflow.annotation.Database;

import com.raizlabs.android.dbflow.sql.language.Delete;

import java.util.ArrayList;
import java.util.List;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;
import static com.raizlabs.android.dbflow.sql.language.SQLite.update;

/**
 * Created by M2-Developer.
 */

@Database(name = Dbconfig.NAME, version = Dbconfig.VERSION)
public class Dbconfig {
    public  static final String NAME = "MyDataBase";
    public static final int VERSION = 1;

    public static boolean InsertData(Subjects subjects) {
        try {
            subjects.save();
            return true;
        } catch (Exception ex) {
            Log.e("AddData", ex.getMessage());
        }
        return false;
    }

    public static boolean DeleteData(int str) {
        try {
            new Delete()
                    .from(Subjects.class)
                    .where(Subjects_Table.id.eq(str))
                    .execute();
            return true;
        } catch (Exception ex) {
            Log.e("DeleteData", ex.getMessage());
        }
        return false;
    }

    public static boolean UpDateData(Subjects upDateSubjects, int idstr) {
        try {
            update(Subjects.class)
                    .set(Subjects_Table.name.eq(upDateSubjects.name)
                            , Subjects_Table.lastname.eq(upDateSubjects.lastname)
                            , Subjects_Table.dis.eq(upDateSubjects.dis)
                            , Subjects_Table.commant.eq(upDateSubjects.commant))
                    .where(Subjects_Table.id.eq(idstr))
                    .execute();
            return true;
        } catch (Exception ex) {
            Log.e("UpdateData", ex.getMessage());
        }
        return false;
    }

    public static List<Subjects> GetAllData() {
        try {
            ArrayList<Subjects> ModelList = new ArrayList<>();
            List<Subjects> Models = select()
                    .from(Subjects.class)
                    .queryList();
            for (int i = 0; i < Models.size(); i++) {
                Subjects subjectsModel = new Subjects();
                subjectsModel.setId(Models.get(i).getId());
                subjectsModel.setName(Models.get(i).getName());
                subjectsModel.setLastname(Models.get(i).getLastname());
                subjectsModel.setDis(Models.get(i).getDis());
                subjectsModel.setCommant(Models.get(i).getCommant());
                ModelList.add(subjectsModel);
            }
            if (ModelList.size() > 0) {
                return ModelList;
            } else {
                return ModelList;
            }
        } catch (Exception ex) {
            Log.e("GetList", ex.getMessage());
        }
        return null;
    }

    public static Subjects GetData(int str) {
        try {
            Subjects subjects = select(Subjects_Table.name,Subjects_Table.lastname,Subjects_Table.dis,Subjects_Table.commant)
                    .from(Subjects.class)
                    .where(Subjects_Table.id.eq(str))
                    .querySingle();
            if (subjects != null) {
                return subjects;
            }
        } catch (Exception ex) {
            Log.e("Model", ex.getMessage());
        }
        return null;
    }

}

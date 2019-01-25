package com.m2.developer.DBFlow.ModelTable;

import com.m2.developer.DBFlow.DBConfig.Dbconfig;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by M2-Developer.
 */
@Table(database = Dbconfig.class)
public class Subjects extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public int id;
    @Column
    public String name;
    @Column
    public String lastname;
    @Column
    public String dis;
    @Column
    public String commant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getCommant() {
        return commant;
    }

    public void setCommant(String commant) {
        this.commant = commant;
    }

}

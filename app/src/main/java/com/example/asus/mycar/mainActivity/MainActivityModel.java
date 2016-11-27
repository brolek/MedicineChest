package com.example.asus.mycar.mainActivity;

import com.example.asus.mycar.Medicine;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016-08-25.
 */
public class MainActivityModel {

    private List<Medicine> medicineList =  new ArrayList<>();

    public MainActivityModel(){
        this.medicineList = new Select().from(Medicine.class).queryList();
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public void reSelectFromDatabase(){
        this.medicineList = new Select().from(Medicine.class).queryList();
    }
}

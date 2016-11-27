package com.example.asus.mycar.searchFragment;

import com.example.asus.mycar.Medicine;
import com.example.asus.mycar.mainActivity.MainActivityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016-08-26.
 */
public class SearchFragmentModel {

    private List<String> dataArray ;
    private List<Medicine> medicineList ;


    MainActivityModel mainActivityModel;

    SearchFragmentModel(){
        mainActivityModel = new MainActivityModel();
        dataArray = new ArrayList<>();
        medicineList = mainActivityModel.getMedicineList();

        for(int i=0;i<mainActivityModel.getMedicineList().size();i++){
            dataArray.add(i, mainActivityModel.getMedicineList().get(i).getName());

        }

    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public List<String> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<String> dataArray) {
        this.dataArray = dataArray;
    }
}

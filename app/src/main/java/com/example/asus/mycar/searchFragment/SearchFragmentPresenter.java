package com.example.asus.mycar.searchFragment;

import android.content.Intent;

import com.example.asus.mycar.SpecificContent;

/**
 * Created by asus on 2016-08-26.
 */
public class SearchFragmentPresenter {


    SearchFragmentModel searchFragmentModel;
    SearchFragment searchFragment;

    SearchFragmentPresenter(SearchFragment searchFragment){
        this.searchFragmentModel = new SearchFragmentModel();
        this.searchFragment = searchFragment;


    }


    public void startSpecificActivity(int arg2){

        Intent intent = new Intent(searchFragment,SpecificContent.class);
        intent.putExtra("medicine", searchFragmentModel.getMedicineList().get(arg2));
        searchFragment.startActivity(intent);
    }

}

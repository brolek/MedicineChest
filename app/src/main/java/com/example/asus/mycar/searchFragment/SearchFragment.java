package com.example.asus.mycar.searchFragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.mycar.Medicine;
import com.example.asus.mycar.R;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by asus on 2016-08-22.
 */
public class SearchFragment extends ActionBarActivity {

    ArrayAdapter<String> myAdapter;
    ListView listView;
//    private List<Medicine> medicineList;
    //String[] dataArray = new String[];
//    List<String> dataArray = new ArrayList<>();

    SearchFragmentPresenter searchFragmentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        searchFragmentPresenter =new SearchFragmentPresenter(this);

//        medicineList = new Select().from(Medicine.class).queryList();
//
//        for(int i=0;i<medicineList.size();i++){
//            dataArray.add(i, medicineList.get(i).getName());
//
//        }


        ActionBar actionBar = getSupportActionBar();

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        //==========================

        listView = (ListView) findViewById(R.id.listView);
        myAdapter = new ArrayAdapter<String>(this, R.layout.list_view_element, searchFragmentPresenter.searchFragmentModel.getDataArray());
        listView.setAdapter(myAdapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

//                System.out.println(arg2+" --postion");

                searchFragmentPresenter.startSpecificActivity(arg2);
//                Intent intent = new Intent(SearchFragment.this,SpecificContent.class);
//                intent.putExtra("medicine", medicineList.get(arg2));
//                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String newText)
            {
                // this is your adapter that will be filtered
                myAdapter.getFilter().filter(newText);
                System.out.println("on text chnge text: "+newText);
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                // this is your adapter that will be filtered
                myAdapter.getFilter().filter(query);
                System.out.println("on query submit: "+query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    protected void onResume(){
        super.onResume();
        searchFragmentPresenter.searchFragmentModel.getDataArray().clear();
        List<Medicine> medicineList2 = new Select().from(Medicine.class).queryList();
        for(int i=0;i<medicineList2.size();i++){
            searchFragmentPresenter.searchFragmentModel.getDataArray().add(i, medicineList2.get(i).getName());


        }
        myAdapter.notifyDataSetChanged();
    }


}
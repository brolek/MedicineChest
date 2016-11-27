package com.example.asus.mycar.mainActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.example.asus.mycar.MainAdapter;
import com.example.asus.mycar.Medicine;
import com.example.asus.mycar.MessageEvent;
import com.example.asus.mycar.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by asus on 2016-08-16.
 */
public class MainActivity extends FragmentActivity {
    //    private List<Medicine> medicineList;
    private RecyclerView recyclerView;
    //    private MainAdapter mAdapter;
    private Button addNewMedicineButton;
    private ImageView goBackImageView;
    private ImageView saveImageView;
    private ImageView searchContentImageView;
    private Button takePhoto;
    private EditText setName, setComment, setDate;
    private EditText dateView;
    private Button getData;
    private DatePickerDialog chooseDatePicker;
    private SimpleDateFormat dateFormatter;
    //    MainActivityModel mainActivityModel;
    MainActivityPresenter mainActivityPresenter;

    View myView;
    View transparentPanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViews();

//        mainActivityModel = new MainActivityModel();
        mainActivityPresenter = new MainActivityPresenter(this);
//        mAdapter = new MainAdapter(medicineList, this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
        recyclerView.setAdapter(mainActivityPresenter);

        Picasso.with(getApplicationContext()).load("http://bsccongress.com/im19/search-icon-white-clip-art.png").into(searchContentImageView);
        Picasso.with(getApplicationContext()).load("http://www.myiconfinder.com/uploads/iconsets/dc0fa3f38f18c9d1b4e2c99967401405-Arrow.png").into(goBackImageView);
        Picasso.with(getApplicationContext()).load("http://www.myiconfinder.com/uploads/iconsets/256-256-b87200a0c07928471ed513f34d480468-accept.png").into(saveImageView);


        addNewMedicineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
//                //getWindow().setExitTransition(TransitionInflater.from(getApplicationContext()).inflateTransition(android.R.transition.explode));
//               // addNewButton.setVisibility(View.INVISIBLE);
//                Fragment fragment = new CameraActivity();
//               // fragment.setEnterTransition(TransitionInflater.from(getApplicationContext()).inflateTransition(android.R.transition.explode));
//                ft.replace(R.id.frameLayout, fragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                //ft.addToBackStack(null);
//                ft.commit();

                openingAnimation();

//                    dateView = (TextView) findViewById(R.id.getDataTextView);
//                    calendar = Calendar.getInstance();
//                    year = calendar.get(Calendar.YEAR);
//                    getData = (Button) findViewById(R.id.setDataButton);
//
//                    month = calendar.get(Calendar.MONTH);
//                    day = calendar.get(Calendar.DAY_OF_MONTH);
//                    showDate(year, month+1, day);


                setDateTimeField();

                getData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        chooseDatePicker.show();

                    }
                });
                final Medicine medicine = new Medicine();


                takePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
//                        intent.putExtra("medicine", medicine);
//                        startActivity(intent);
                        mainActivityPresenter.startCameraActivity(medicine);


                    }
                });


                saveImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (String.valueOf(setName.getText()).equals("") || String.valueOf(setComment.getText()).equals("") || String.valueOf(setDate.getText()).equals(""))
                            Toast.makeText(getApplicationContext(), "Uzupełnij powyższe pola", Toast.LENGTH_SHORT).show();
                        else {

//                                CameraActivity cameraActivity = new CameraActivity();

                            medicine.setName(String.valueOf(setName.getText()));
                            medicine.setComment(String.valueOf(setComment.getText()));
                            medicine.setDate(String.valueOf(setDate.getText()));
                            // cameraActivity.setPhotoMedicine(medicine);
                            medicine.save();
                            //EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));
                            //producersList = new Select().from(Medicine.class).queryList();
                            mainActivityPresenter.notifyDataSetChanged();
                            mainActivityPresenter.mainActivityModel.reSelectFromDatabase();

//                                final View myView = findViewById(R.id.newOne);
//                                final View transparentPanel = findViewById(R.id.transparentPanel);


                            closingAnimation();

                            setName.setText("");
                            setComment.setText("");
                            setDate.setText("");

                            if (myView != null) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(myView.getWindowToken(), 0);
                            }

                        }


                    }
                });


            }
        });

        searchContentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityPresenter.startSearchActivity();
            }
        });


        goBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                final View myView = findViewById(R.id.newOne);
//                final View transparentPanel = findViewById(R.id.transparentPanel);

//                int cx = addNewMedicineButton.getWidth() / 2;
//                int cy = addNewMedicineButton.getHeight() / 2;
//
//                Animator anim =
//                        ViewAnimationUtils.createCircularReveal(myView, myView.getWidth() - cx, myView.getHeight() - cy, 2000, 0);
//                anim.setDuration(500);
//                anim.setInterpolator(new LinearOutSlowInInterpolator());
//                addNewMedicineButton.setText("+");
//                transparentPanel.setVisibility(View.INVISIBLE);
//                anim.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        myView.setVisibility(View.INVISIBLE);
//
//                        addNewMedicineButton.setVisibility(View.VISIBLE);
//                    }
//
//                });
//                anim.start();

                closingAnimation();

//                View view = findViewById(R.id.newOne);
                if (myView != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(myView.getWindowToken(), 0);
                }
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {

        mainActivityPresenter.notifyDataSetChanged();
        mainActivityPresenter.mainActivityModel.reSelectFromDatabase();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    private void setDateTimeField() {
        //fromDateEtxt.setOnClickListener(this);
        //toDateEtxt.setOnClickListener(this);

        dateView = (EditText) findViewById(R.id.getDataEditView);
        final Calendar newCalendar = Calendar.getInstance();

        chooseDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateView.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    public void findViews() {

        addNewMedicineButton = (Button) findViewById(R.id.plusButton);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        searchContentImageView = (ImageView) findViewById(R.id.searchImageView);
        goBackImageView = (ImageView) findViewById(R.id.backImageView);
        saveImageView = (ImageView) findViewById(R.id.saveImageView);
        myView = findViewById(R.id.newOne);
        transparentPanel = findViewById(R.id.transparentPanel);
        getData = (Button) findViewById(R.id.setDataButton);
        takePhoto = (Button) findViewById(R.id.takeAPhotoButton);
        setName = (EditText) findViewById(R.id.setName);
        setComment = (EditText) findViewById(R.id.setComment);
        setDate = (EditText) findViewById(R.id.getDataEditView);
        goBackImageView = (ImageView) findViewById(R.id.backImageView);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void openingAnimation() {

        int cx = addNewMedicineButton.getWidth() / 2;
        int cy = addNewMedicineButton.getHeight() / 2;
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, myView.getWidth() - cx, myView.getHeight() - cy, 0, 2000);
        anim.setDuration(500);
        anim.setInterpolator(new LinearOutSlowInInterpolator());

        myView.setVisibility(View.VISIBLE);
        transparentPanel.setVisibility(View.VISIBLE);
        addNewMedicineButton.setVisibility(View.INVISIBLE);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void closingAnimation() {

        int cx = addNewMedicineButton.getWidth() / 2;
        int cy = addNewMedicineButton.getHeight() / 2;

        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, myView.getWidth() - cx, myView.getHeight() - cy, 2000, 0);
        anim.setDuration(500);
        anim.setInterpolator(new LinearOutSlowInInterpolator());
        addNewMedicineButton.setText("+");
        transparentPanel.setVisibility(View.INVISIBLE);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);

                addNewMedicineButton.setVisibility(View.VISIBLE);
            }

        });
        anim.start();
    }

}

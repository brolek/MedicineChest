package com.example.asus.mycar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by asus on 2016-08-22.
 */
public class SpecificContent extends Activity {

    TextView textView1, textView2,textView3;
    Button button1,button2,button3,editButton1,editButton2,editButton3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    EditText editText1,editText2,editText3;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.specific_content);



        setFinds();




        Bundle extras = getIntent().getExtras();
        Medicine medicine =new Medicine();
        if (extras != null) {
            medicine = extras.getParcelable("medicine");
        }

        textView1.setText(medicine.getName());
        textView2.setText(medicine.getComment());
        textView3.setText(medicine.getDate());

        final Medicine medicine2 = new Select().from(Medicine.class).where(Medicine_Table.name.is(medicine.getName())).querySingle();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout1.setVisibility(View.VISIBLE);
                editText1 = (EditText) findViewById(R.id.nameEditText);
                editButton1 = (Button) findViewById(R.id.nameOkButton);
                editButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        medicine2.setName(String.valueOf(editText1.getText()));
                        medicine2.save();
                        linearLayout1.setVisibility(View.GONE);
                        textView1.setText(String.valueOf(editText1.getText()));
                        EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));



                    }
                });


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout2.setVisibility(View.VISIBLE);
                editText2 = (EditText) findViewById(R.id.commentEditText);
                editButton2 = (Button) findViewById(R.id.commentOkButton);
                editButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        medicine2.setComment(String.valueOf(editText2.getText()));
                        medicine2.save();
                        linearLayout2.setVisibility(View.GONE);
                        textView2.setText(String.valueOf(editText2.getText()));
                        EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));


                    }
                });

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout3.setVisibility(View.VISIBLE);
                editText3 = (EditText) findViewById(R.id.dateEditText);
                editButton3 = (Button) findViewById(R.id.dateOkButton);
                dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

                setDateTimeField();
                toDatePickerDialog.show();


                editButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        medicine2.setDate(String.valueOf(editText3.getText()));
                        medicine2.save();
                        linearLayout3.setVisibility(View.GONE);
                        textView3.setText(String.valueOf(editText3.getText()));
                        EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));


                    }
                });

            }
        });




    }

    private void setDateTimeField() {
        //fromDateEtxt.setOnClickListener(this);
        //toDateEtxt.setOnClickListener(this);

        editText3 = (EditText) findViewById(R.id.dateEditText);
        final Calendar newCalendar = Calendar.getInstance();

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editText3.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    public void setFinds(){

        textView1= (TextView)  findViewById(R.id.nameEditTextView);
        textView2= (TextView)  findViewById(R.id.commentEditTextView);
        textView3= (TextView)  findViewById(R.id.dateEditTextView);
        button1 = (Button) findViewById(R.id.nameEditButton);
        button2 = (Button) findViewById(R.id.commentEditButton);
        button3 = (Button) findViewById(R.id.dateEditButton);
        linearLayout1 = (LinearLayout) findViewById(R.id.edit1LinearLayout);
        linearLayout2 = (LinearLayout) findViewById(R.id.edit2LinearLayout);
        linearLayout3 = (LinearLayout) findViewById(R.id.edit3LinearLayout);
    }
}

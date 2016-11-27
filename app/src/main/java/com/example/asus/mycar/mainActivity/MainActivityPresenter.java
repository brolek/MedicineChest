package com.example.asus.mycar.mainActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.mycar.CameraActivity;
import com.example.asus.mycar.Medicine;
import com.example.asus.mycar.Medicine_Table;
import com.example.asus.mycar.MessageEvent;
import com.example.asus.mycar.R;
import com.example.asus.mycar.searchFragment.SearchFragment;
import com.example.asus.mycar.SpecificContent;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by asus on 2016-08-25.
 */
public class MainActivityPresenter extends RecyclerView.Adapter<MainActivityPresenter.MyViewHolder>{

    MainActivityModel mainActivityModel;
    MainActivity mainActivity;
    private List<Medicine> medicineList;



    MainActivityPresenter(MainActivity mainActivity ){
        this.mainActivity = mainActivity;
        mainActivityModel = new MainActivityModel();
        this.medicineList = mainActivityModel.getMedicineList();


    }

    public void startCameraActivity(Medicine medicine){
        Intent intent = new Intent(mainActivity, CameraActivity.class);
        intent.putExtra("medicine", medicine);
        mainActivity.startActivity(intent);

    }

    public void startSearchActivity(){

        Intent intent = new Intent(mainActivity, SearchFragment.class);
        mainActivity.startActivity(intent);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,using,date;
        public ImageView image,minus;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nameTextView);
            using = (TextView) view.findViewById(R.id.usingTextView);
            date = (TextView) view.findViewById(R.id.dateTextView);
            minus = (ImageView) view.findViewById(R.id.minusImageView);
            image = (ImageView) view.findViewById(R.id.recyclerViewElementImageView);
//
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                        FragmentTransaction ft = fragmentManager.beginTransaction();
//                        Fragment fragment = new CameraActivity();
//                        ft.replace(R.id.frameLayout, fragment);
//                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                        ft.addToBackStack(null);
//                        ft.commit();
//
//                }
//            });




        }
    }


//    public MainActivityPresenter(List<Medicine> moviesList, Activity activity) {
//        //this.producersList = moviesList;
//        this.medicineList = new Select().from(Medicine.class).queryList();
//
//        this.activity = activity;
//
//    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_view_row, parent, false);





        return new MyViewHolder(itemView);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder( final MyViewHolder holder, final int position) {
//        final CarProducers producers = producersList.get(position);
        final Medicine medicine = medicineList.get(position);

        holder.name.setText(medicine.getName());
        holder.using.setText(medicine.getComment());
        holder.date.setText(medicine.getDate());

        // Bitmap bmp = BitmapFactory.decodeByteArray(producers.getPhoto(), 0, producers.getPhoto().length);
        // holder.image.setImageBitmap(bmp);
//        try {
//            ContextWrapper cw = new ContextWrapper(activity);
//            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//            // Create imageDir
//            File f=new File(directory, "profile.jpg");
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//
//            holder.image.setImageBitmap(b);
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }

//        holder.name.setText(producers.getText());
//        Picasso.with(activity).load(producers.getImage()).into(holder.image);
        Picasso.with(mainActivity).load("https://image.freepik.com/free-icon/minus-horizontal-straight-line_318-75301.jpg").into(holder.minus);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SpecificContent.class);
                intent.putExtra("medicine", medicineList.get(position));
                mainActivity.startActivity(intent);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Medicine medicine = new Medicine();
                //medicine.setId(position);
                //medicine.setName(producersList.get(position).getName());

                medicine = new Select().from(Medicine.class).where(Medicine_Table.name.is(medicineList.get(position).getName())).querySingle();

                medicineList.remove(position);
                medicine.delete();

                holder.itemView.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        holder.itemView.animate().translationXBy(holder.itemView.getWidth()).setDuration(500).setInterpolator(new LinearOutSlowInInterpolator());




                    }
                });
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));
                    }
                }, 500);


            }
        });

        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(c.getTime());

        try {
            if(sdf.parse(String.valueOf(holder.date.getText())).before(sdf.parse(formattedDate)))
            {
                holder.itemView.setBackgroundColor(Color.parseColor("#FFEBEE"));



//                Intent intent = new Intent(activity, MainActivity.class);
//                PendingIntent pIntent = PendingIntent.getActivity(activity, (int) System.currentTimeMillis(), intent, 0);
//
//                // Build notification
//                // Actions are just fake
//                Notification noti = new Notification.Builder(activity)
//                        .setContentTitle("My medicine chest" )
//                        .setContentText("Lek "+ holder.name.getText()+ " Jest przeterminowany").setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentIntent(pIntent).build();
//                NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
//                // hide the notification after its selected
//                noti.flags |= Notification.FLAG_AUTO_CANCEL;
//
//                notificationManager.notify(0, noti);
                ;




//                scheduleNotification(getNotification("10 second delay"), 10000);


            }
            else
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }


}

package com.example.asus.mycar;

/**
 * Created by asus on 2016-08-16.
 */
//public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
//
////    private List<CarProducers> producersList;
//    private List<Medicine> producersList;
//    private Activity activity;
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name,using,date;
//        public ImageView image,minus;
//
//        public MyViewHolder(View view) {
//            super(view);
//            name = (TextView) view.findViewById(R.id.nameTextView);
//            using = (TextView) view.findViewById(R.id.usingTextView);
//            date = (TextView) view.findViewById(R.id.dateTextView);
//            minus = (ImageView) view.findViewById(R.id.minusImageView);
//            image = (ImageView) view.findViewById(R.id.recyclerViewElementImageView);
////
////            view.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////
////                        FragmentTransaction ft = fragmentManager.beginTransaction();
////                        Fragment fragment = new CameraActivity();
////                        ft.replace(R.id.frameLayout, fragment);
////                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
////                        ft.addToBackStack(null);
////                        ft.commit();
////
////                }
////            });
//
//
//
//
//        }
//    }
//
//
//    public MainAdapter(List<Medicine> moviesList, Activity activity) {
//        //this.producersList = moviesList;
//        this.producersList = new Select().from(Medicine.class).queryList();
//
//        this.activity = activity;
//
//    }
//
//    public void reSelectFromDatabase(){
//        this.producersList = new Select().from(Medicine.class).queryList();
//    }
//
//
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        final View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.main_recycler_view_row, parent, false);
//
//
//
//
//
//        return new MyViewHolder(itemView);
//    }
//
//    private void loadImageFromStorage(String path)
//    {
//
////        try {
////            File f=new File(path, "profile.jpg");
////            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
////            ImageView img=(ImageView).findViewById(R.id.imgPicker);
////            img.setImageBitmap(b);
////        }
////        catch (FileNotFoundException e)
////        {
////            e.printStackTrace();
////        }
//
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public void onBindViewHolder( final MyViewHolder holder, final int position) {
////        final CarProducers producers = producersList.get(position);
//        final Medicine producers = producersList.get(position);
//
//        holder.name.setText(producers.getName());
//        holder.using.setText(producers.getComment());
//        holder.date.setText(producers.getDate());
//
//      // Bitmap bmp = BitmapFactory.decodeByteArray(producers.getPhoto(), 0, producers.getPhoto().length);
//      // holder.image.setImageBitmap(bmp);
////        try {
////            ContextWrapper cw = new ContextWrapper(activity);
////            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
////            // Create imageDir
////            File f=new File(directory, "profile.jpg");
////            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
////
////            holder.image.setImageBitmap(b);
////        }
////        catch (FileNotFoundException e)
////        {
////            e.printStackTrace();
////        }
//
////        holder.name.setText(producers.getText());
////        Picasso.with(activity).load(producers.getImage()).into(holder.image);
//        Picasso.with(activity).load("https://image.freepik.com/free-icon/minus-horizontal-straight-line_318-75301.jpg").into(holder.minus);
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity,SpecificContent.class);
//                intent.putExtra("medicine", producersList.get(position));
//                activity.startActivity(intent);
//            }
//        });
//
//        holder.minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Medicine medicine = new Medicine();
//                //medicine.setId(position);
//                //medicine.setName(producersList.get(position).getName());
//
//                medicine = new Select().from(Medicine.class).where(Medicine_Table.name.is(producersList.get(position).getName())).querySingle();
//
//                producersList.remove(position);
//                medicine.delete();
//
//                holder.itemView.post(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        holder.itemView.animate().translationXBy(holder.itemView.getWidth()).setDuration(500).setInterpolator(new LinearOutSlowInInterpolator());
//
//
//
//
//                    }
//                });
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));
//                    }
//                }, 500);
//
//
//            }
//        });
//
//        Calendar c = Calendar.getInstance();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        String formattedDate = sdf.format(c.getTime());
//
//        try {
//            if(sdf.parse(String.valueOf(holder.date.getText())).before(sdf.parse(formattedDate)))
//            {
//                holder.itemView.setBackgroundColor(Color.parseColor("#FFEBEE"));
//
//
//
////                Intent intent = new Intent(activity, MainActivity.class);
////                PendingIntent pIntent = PendingIntent.getActivity(activity, (int) System.currentTimeMillis(), intent, 0);
////
////                // Build notification
////                // Actions are just fake
////                Notification noti = new Notification.Builder(activity)
////                        .setContentTitle("My medicine chest" )
////                        .setContentText("Lek "+ holder.name.getText()+ " Jest przeterminowany").setSmallIcon(R.mipmap.ic_launcher)
////                        .setContentIntent(pIntent).build();
////                NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
////                // hide the notification after its selected
////                noti.flags |= Notification.FLAG_AUTO_CANCEL;
////
////                notificationManager.notify(0, noti);
//                ;
//
//
//
//
////                scheduleNotification(getNotification("10 second delay"), 10000);
//
//
//            }
//            else
//                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//    private void scheduleNotification(Notification notification, int delay) {
//
//        Intent notificationIntent = new Intent(activity, NotificationPublisher.class);
//        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
//        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        long futureInMillis = SystemClock.elapsedRealtime() + delay;
//        AlarmManager alarmManager = (AlarmManager)activity.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private Notification getNotification(String content) {
//        Notification.Builder builder = new Notification.Builder(activity);
//        builder.setContentTitle("Scheduled Notification");
//        builder.setContentText(content);
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        return builder.build();
//    }
//
//    @Override
//    public int getItemCount() {
//        return producersList.size();
//    }
//}
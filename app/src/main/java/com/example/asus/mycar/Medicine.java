package com.example.asus.mycar;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by asus on 2016-08-18.
 */
@Table(database = MyDatabase.class)
public class Medicine extends BaseModel implements Parcelable{

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String name;
    @Column
    String comment;
    @Column
    String date;
//    @Column
//    Blob photo;

    protected Medicine(Parcel in) {
        id = in.readInt();
        name = in.readString();
        comment = in.readString();
        date = in.readString();
//        photo.setBlob(in.createByteArray());
    }

    public static final Creator<Medicine> CREATOR = new Creator<Medicine>() {
        @Override
        public Medicine createFromParcel(Parcel in) {
            return new Medicine(in);
        }

        @Override
        public Medicine[] newArray(int size) {
            return new Medicine[size];
        }
    };

//    public byte[] getPhoto() {
//        return photo.getBlob();
//    }
//
//    public void setPhoto(byte[] photo) {
//        this.photo.setBlob(photo);
//    }

    public Medicine(){}

//    protected Medicine(Parcel in) {
//        id = in.readInt();
//        name = in.readString();
//        comment = in.readString();
//        date = in.readString();
//
//
//    }



//    public static final Creator<Medicine> CREATOR = new Creator<Medicine>() {
//        @Override
//        public Medicine createFromParcel(Parcel in) {
//            return new Medicine(in);
//        }
//
//        @Override
//        public Medicine[] newArray(int size) {
//            return new Medicine[size];
//        }
//    };

//    @Override
//    public int describeContents() {
//        return 0;
//    }

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeString(name);
//        dest.writeString(comment);
//        dest.writeString(date);
//    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(comment);
        dest.writeString(date);
//        dest.writeByteArray(photo.getBlob());
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


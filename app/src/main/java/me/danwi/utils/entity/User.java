package me.danwi.utils.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/12/13
 * Time: 上午9:49
 */
public class User implements Parcelable {
    private String name;
    private int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

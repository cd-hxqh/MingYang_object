package com.example.admin.mingyang_object.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 库存余量
 */
public class Udpro extends Entity implements Parcelable {
    private static final String TAG = "Udpro";
    private static final long serialVersionUID = 2015050105L;

    public String pronum; //项目编号
    public String description; //项目描述
    public String branch; //所属中心
    public String capacity; //总厂容量（MW）
    public String contractstatus; //合同状态
    public String owner; //业务单位
    public String period; //质保期（年）
    public String prostage; //项目当前阶段
    public String respons; //责任人编号
    public String signdate; //签订时间


    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        pronum = jsonObject.getString("pronum");
        description = jsonObject.getString("description");
        branch = jsonObject.getString("branch");
        capacity = jsonObject.getString("capacity");
        contractstatus = jsonObject.getString("contractstatus");
        owner = jsonObject.getString("owner");
        period = jsonObject.getString("period");
        prostage = jsonObject.getString("prostage");
        respons = jsonObject.getString("respons");
        signdate = jsonObject.getString("signdate");
    }

    public Udpro() {
    }


    private Udpro(Parcel in) {
        pronum = in.readString();
        description = in.readString();
        branch = in.readString();
        capacity = in.readString();
        contractstatus = in.readString();
        owner = in.readString();
        period = in.readString();
        prostage = in.readString();
        respons = in.readString();
        signdate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pronum);
        dest.writeString(description);
        dest.writeString(branch);
        dest.writeString(capacity);
        dest.writeString(contractstatus);
        dest.writeString(owner);
        dest.writeString(period);
        dest.writeString(prostage);
        dest.writeString(respons);
        dest.writeString(signdate);

    }


    public static final Creator<Udpro> CREATOR = new Creator<Udpro>() {
        @Override
        public Udpro createFromParcel(Parcel source) {
            return new Udpro(source);
        }

        @Override
        public Udpro[] newArray(int size) {
            return new Udpro[size];
        }
    };

}

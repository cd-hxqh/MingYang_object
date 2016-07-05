package com.example.admin.mingyang_object.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by apple on 15/10/.
 */
public class Entity<T> {
    private T t;

    public Entity(T oT) {
        this.t = oT;
    }

    public Entity() {
    }

    public String getClassType() {
        return t.getClass().getName();
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

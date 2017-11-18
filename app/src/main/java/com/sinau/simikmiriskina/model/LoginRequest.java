package com.sinau.simikmiriskina.model;

import android.text.Editable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muklis on 11/18/17.
 */

public class LoginRequest {
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("password")
    @Expose
    private String password;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

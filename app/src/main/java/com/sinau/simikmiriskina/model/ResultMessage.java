package com.sinau.simikmiriskina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muklis on 11/18/17.
 */

public class ResultMessage {

    @Expose
    private String message;

    @Expose
    private Object result;

    public String getMessage() {
        return message;
    }

    public Object getResult() {
        return result;
    }
}

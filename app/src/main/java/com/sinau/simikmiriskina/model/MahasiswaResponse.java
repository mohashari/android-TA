package com.sinau.simikmiriskina.model;

import java.util.List;

/**
 * Created by santoso on 11/17/17.
 */

public class MahasiswaResponse {
    private String message;
    private Object result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setResult(List<Mahasiswa> result) {
        this.result = result;
    }

}

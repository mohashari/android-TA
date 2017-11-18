package com.sinau.simikmiriskina.model;

import java.util.List;

/**
 * Created by santoso on 11/17/17.
 */

public class MahasiswaResponse {
    private String message;
    private List<Mahasiswa> result;
    private String pages;
    private String elements;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Mahasiswa> getResult() {
        return result;
    }

    public void setResult(List<Mahasiswa> result) {
        this.result = result;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }
}

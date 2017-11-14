package com.sinau.simikmiriskina.model;

import java.util.List;

public class MataKuliahValue {
    private String message;
    private List<MataKuliah> result;
    private String pages;
    private String elements;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MataKuliah> getResult() {
        return result;
    }

    public void setResult(List<MataKuliah> result) {
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

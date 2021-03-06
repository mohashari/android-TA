package com.sinau.simikmiriskina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by santoso on 11/18/17.
 */

public class AddJadwal {
    @SerializedName("secure_id_mahasiswa")
    @Expose
    private String idMahasiswa;

    @SerializedName("secure_id_matakuliah")
    @Expose
    private String idMataKuliah;

    public String getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(String idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public String getIdMataKuliah() {
        return idMataKuliah;
    }

    public void setIdMataKuliah(String idMataKuliah) {
        this.idMataKuliah = idMataKuliah;
    }
}

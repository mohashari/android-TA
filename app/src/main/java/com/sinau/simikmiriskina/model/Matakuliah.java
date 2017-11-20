package com.sinau.simikmiriskina.model;


public class Matakuliah {
    private String id;
    private String name;
    private String semester;
    private String version;;
    private String sks;
    private String hari;
    private String secureIdJadwal;

    public String getId() {
        return id;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSecureIdJadwal(){return secureIdJadwal;}

    public void setSecureIdJadwal(String secureIdJadwal){this.secureIdJadwal = secureIdJadwal;}
}

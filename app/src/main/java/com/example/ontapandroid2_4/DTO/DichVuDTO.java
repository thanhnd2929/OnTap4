package com.example.ontapandroid2_4.DTO;

public class DichVuDTO{

    private int madv;
    private String noidung;
    private String ngay;
    private int thanhtien;

    public DichVuDTO(int madv, String noidung, String ngay, int thanhtien) {
        this.madv = madv;
        this.noidung = noidung;
        this.ngay = ngay;
        this.thanhtien = thanhtien;
    }

    public DichVuDTO() {
    }

    public DichVuDTO(String noidung, String ngay, int thanhtien) {
        this.noidung = noidung;
        this.ngay = ngay;
        this.thanhtien = thanhtien;
    }

    public int getMadv() {
        return madv;
    }

    public void setMadv(int madv) {
        this.madv = madv;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}

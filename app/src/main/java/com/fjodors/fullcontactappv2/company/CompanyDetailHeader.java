package com.fjodors.fullcontactappv2.company;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class CompanyDetailHeader {
    private String logo;
    private String name;
    private long approxEmployees;
    private String founded;

    @ParcelConstructor
    public CompanyDetailHeader(String logo, String name, long approxEmployees, String founded) {
        this.logo = logo;
        this.name = name;
        this.approxEmployees = approxEmployees;
        this.founded = founded;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getApproxEmployees() {
        return approxEmployees;
    }

    public String getFounded() {
        return founded;
    }
}
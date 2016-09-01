package com.fjodors.fullcontactappv2.company;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class CompanyDetailHeader {
    String logo;
    String name;
    long approxEmployees;
    String founded;

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

    public void setLogo(String logo) {
        this.logo = logo;
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

    public void setApproxEmployees(long approxEmployees) {
        this.approxEmployees = approxEmployees;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }
}
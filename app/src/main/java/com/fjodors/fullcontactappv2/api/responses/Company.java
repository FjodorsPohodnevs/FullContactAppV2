package com.fjodors.fullcontactappv2.api.responses;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Company {
    @SerializedName("status")
    long status;
    @SerializedName("requestId")
    String requestId;
    @SerializedName("category")
    List<Category> category = new ArrayList<Category>();
    @SerializedName("logo")
    String logo;
    @SerializedName("website")
    String website;
    @SerializedName("languageLocale")
    String languageLocale;
    @SerializedName("organization")
    Organization organization;
    @SerializedName("socialProfiles")
    List<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
    @SerializedName("traffic")
    Traffic traffic;

    @Parcel
    public static class Category {

        @SerializedName("name")
        String name;
        @SerializedName("code")
        String code;

    }

    @Parcel
    public static class Organization {

        @SerializedName("name")
        String name;
        @SerializedName("approxEmployees")
        long approxEmployees;
        @SerializedName("founded")
        String founded;
        @SerializedName("contactInfo")
        ContactInfo contactInfo;
        @SerializedName("links")
        List<Link> links = new ArrayList<Link>();
        @SerializedName("images")
        List<Image> images = new ArrayList<Image>();
        @SerializedName("keywords")
        List<String> keywords = new ArrayList<String>();

        @Parcel
        public static class ContactInfo {

            @SerializedName("addresses")
            List<Address> addresses = new ArrayList<Address>();

            @Parcel
            public static class Address {

                @SerializedName("addressLine1")
                String addressLine1;
                @SerializedName("locality")
                String locality;
                @SerializedName("country")
                Country country;
                @SerializedName("label")
                String label;

                @Parcel
                public static class Country {

                    @SerializedName("name")
                    String name;
                    @SerializedName("code")
                    String code;

                }

            }

        }

        @Parcel
        public static class Link {

            @SerializedName("url")
            String url;
            @SerializedName("label")
            String label;

        }

        @Parcel
        public static class Image {

            @SerializedName("url")
            String url;
            @SerializedName("label")
            String label;

        }

    }

    @Parcel
    public static class SocialProfile {

        @SerializedName("typeId")
        String typeId;
        @SerializedName("typeName")
        String typeName;
        @SerializedName("url")
        String url;
        @SerializedName("bio")
        String bio;
        @SerializedName("followers")
        long followers;
        @SerializedName("following")
        long following;
        @SerializedName("username")
        String username;
        @SerializedName("id")
        String id;

    }

    @Parcel
    public static class Traffic {

        @SerializedName("topCountryRanking")
        List<TopCountryRanking> topCountryRanking = new ArrayList<TopCountryRanking>();
        @SerializedName("ranking")
        List<Ranking> ranking = new ArrayList<Ranking>();

        @Parcel
        public static class TopCountryRanking {

            @SerializedName("rank")
            long rank;
            @SerializedName("locale")
            String locale;

        }

        @Parcel
        public static class Ranking {

            @SerializedName("rank")
            long rank;
            @SerializedName("locale")
            String locale;

        }

    }

}
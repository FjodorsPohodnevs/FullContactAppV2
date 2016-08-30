package com.fjodors.fullcontactappv2.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//TODO: implement parcerlable
public class Company implements Serializable {
    @SerializedName("status")
    @Expose
    public long status;
    @SerializedName("requestId")
    @Expose
    public String requestId;
    @SerializedName("category")
    @Expose
    public List<Category> category = new ArrayList<Category>();
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("languageLocale")
    @Expose
    public String languageLocale;
    @SerializedName("organization")
    @Expose
    public Organization organization;
    @SerializedName("socialProfiles")
    @Expose
    public List<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
    @SerializedName("traffic")
    @Expose
    public Traffic traffic;

    public static class Category {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("code")
        @Expose
        public String code;

    }

    public class Organization {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("approxEmployees")
        @Expose
        public long approxEmployees;
        @SerializedName("founded")
        @Expose
        public String founded;
        @SerializedName("contactInfo")
        @Expose
        public ContactInfo contactInfo;
        @SerializedName("links")
        @Expose
        public List<Link> links = new ArrayList<Link>();
        @SerializedName("images")
        @Expose
        public List<Image> images = new ArrayList<Image>();
        @SerializedName("keywords")
        @Expose
        public List<String> keywords = new ArrayList<String>();

        public class ContactInfo {

            @SerializedName("addresses")
            @Expose
            public List<Address> addresses = new ArrayList<Address>();

            public class Address {

                @SerializedName("addressLine1")
                @Expose
                public String addressLine1;
                @SerializedName("locality")
                @Expose
                public String locality;
                @SerializedName("country")
                @Expose
                public Country country;
                @SerializedName("label")
                @Expose
                public String label;

                public class Country {

                    @SerializedName("name")
                    @Expose
                    public String name;
                    @SerializedName("code")
                    @Expose
                    public String code;

                }

            }

        }

        public class Link {

            @SerializedName("url")
            @Expose
            public String url;
            @SerializedName("label")
            @Expose
            public String label;

        }

        public class Image {

            @SerializedName("url")
            @Expose
            public String url;
            @SerializedName("label")
            @Expose
            public String label;

        }

    }

    public class SocialProfile {

        @SerializedName("typeId")
        @Expose
        public String typeId;
        @SerializedName("typeName")
        @Expose
        public String typeName;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("bio")
        @Expose
        public String bio;
        @SerializedName("followers")
        @Expose
        public long followers;
        @SerializedName("following")
        @Expose
        public long following;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("id")
        @Expose
        public String id;

    }

    public class Traffic {

        @SerializedName("topCountryRanking")
        @Expose
        public List<TopCountryRanking> topCountryRanking = new ArrayList<TopCountryRanking>();
        @SerializedName("ranking")
        @Expose
        public List<Ranking> ranking = new ArrayList<Ranking>();

        public class TopCountryRanking {

            @SerializedName("rank")
            @Expose
            public long rank;
            @SerializedName("locale")
            @Expose
            public String locale;

        }

        public class Ranking {

            @SerializedName("rank")
            @Expose
            public long rank;
            @SerializedName("locale")
            @Expose
            public String locale;

        }

    }

}
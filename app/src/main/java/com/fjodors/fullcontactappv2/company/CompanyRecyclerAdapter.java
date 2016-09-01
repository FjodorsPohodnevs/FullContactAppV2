package com.fjodors.fullcontactappv2.company;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.api.responses.Company;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_COMPANY_LOGO = 0;
    private static final int TYPE_COMPANY_FOUNDED_EMPLOYEE = 1;
    private static final int TYPE_COMPANY_URLS = 2;
    private static final int TYPE_COMPANY_EMAILS = 3;
    private static final int TYPE_COMPANY_BIOS = 4;

    private List<Object> companyData;
    private Context context;


    public CompanyRecyclerAdapter() {
    }

    @Override
    public int getItemViewType(int position) {

        if (companyData.get(position) instanceof Company) {
            return TYPE_COMPANY_LOGO;
        } else if (companyData.get(position) instanceof Company.Organization) {
            return TYPE_COMPANY_FOUNDED_EMPLOYEE;
        } else if (companyData.get(position) instanceof Company.Organization.Link) {
            return TYPE_COMPANY_URLS;
        } else if (companyData.get(position) instanceof Company.Organization.ContactInfo.EmailAddress) {
            return TYPE_COMPANY_EMAILS;
        } else if (companyData.get(position) instanceof Company.SocialProfile) {
            return TYPE_COMPANY_BIOS;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_COMPANY_LOGO:
                View viewCompanyLogo = inflater.inflate(R.layout.item_company_logo_website, parent, false);
                viewHolder = new ViewHolderLogo(viewCompanyLogo);
                break;
            case TYPE_COMPANY_FOUNDED_EMPLOYEE:
                View viewCompanyFoundedEmployee = inflater.inflate(R.layout.item_company_founded_emloyee, parent, false);
                viewHolder = new ViewHolderFoundedEmployee(viewCompanyFoundedEmployee);
                break;
            case TYPE_COMPANY_URLS:
                View viewCompanyUrl = inflater.inflate(R.layout.item_company_url, parent, false);
                viewHolder = new ViewHolderUrls(viewCompanyUrl);
                break;
            case TYPE_COMPANY_EMAILS:
                View viewCompanyEmail = inflater.inflate(R.layout.item_company_email, parent, false);
                viewHolder = new ViewHolderEmails(viewCompanyEmail);
                break;
            case TYPE_COMPANY_BIOS:
                View viewCompanyBio = inflater.inflate(R.layout.item_company_social_bio, parent, false);
                viewHolder = new ViewHolderBio(viewCompanyBio);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_COMPANY_LOGO:
                ViewHolderLogo viewHolderLogo = (ViewHolderLogo) holder;
                Picasso.with(context)
                        .load(((Company) companyData.get(position)).getLogo())
                        .into(viewHolderLogo.companyLogo);
                break;
            case TYPE_COMPANY_FOUNDED_EMPLOYEE:
                ViewHolderFoundedEmployee viewHolderFoundedEmployee = (ViewHolderFoundedEmployee) holder;
                viewHolderFoundedEmployee.companyName.setText(((Company.Organization) companyData.get(position)).getName());
                viewHolderFoundedEmployee.companyFoundedYear.setText(((Company.Organization) companyData.get(position)).getName());
                viewHolderFoundedEmployee.companyEmployeeCount.setText(String.valueOf(((Company.Organization) companyData.get(position)).getApproxEmployees()));
                break;
            case TYPE_COMPANY_URLS:
                ViewHolderUrls viewHolderUrls = (ViewHolderUrls) holder;
                viewHolderUrls.url.setText(((Company.Organization.Link) companyData.get(position)).getUrl());
                break;
            case TYPE_COMPANY_EMAILS:
                ViewHolderEmails viewHolderEmails = (ViewHolderEmails) holder;
                viewHolderEmails.email.setText(((Company.Organization.ContactInfo.EmailAddress) companyData.get(position)).getValue());
                break;
            case TYPE_COMPANY_BIOS:
                ViewHolderBio viewHolderBio = (ViewHolderBio) holder;
                viewHolderBio.bioSource.setText(((Company.SocialProfile) companyData.get(position)).getTypeName());
                viewHolderBio.bio.setText(((Company.SocialProfile) companyData.get(position)).getBio());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return companyData == null ? 0 : companyData.size();
    }

    public void setCompany(List<Object> companyData) {
        this.companyData = companyData;
    }

    public class ViewHolderLogo extends RecyclerView.ViewHolder {

        @BindView(R.id.companyLogo)
        ImageView companyLogo;

        public ViewHolderLogo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolderFoundedEmployee extends RecyclerView.ViewHolder {

        @BindView(R.id.companyName)
        TextView companyName;
        @BindView(R.id.companyFounded)
        TextView companyFoundedYear;
        @BindView(R.id.companyEmployees)
        TextView companyEmployeeCount;


        public ViewHolderFoundedEmployee(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolderUrls extends RecyclerView.ViewHolder {

        @BindView(R.id.url)
        TextView url;

        public ViewHolderUrls(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public class ViewHolderEmails extends RecyclerView.ViewHolder {

        @BindView(R.id.email)
        TextView email;

        public ViewHolderEmails(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolderBio extends RecyclerView.ViewHolder {

        @BindView(R.id.bioSource)
        TextView bioSource;
        @BindView(R.id.bio)
        TextView bio;

        public ViewHolderBio(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

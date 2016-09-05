package com.fjodors.fullcontactappv2.company;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.api.response.Company;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_COMPANY_HEADER = 0;
    private static final int TYPE_COMPANY_URLS_EMAILS = 1;
    private static final int TYPE_COMPANY_BIOS = 2;

    private List<Object> companyData;
    private Context context;

    @Override
    public int getItemViewType(int position) {
        if (companyData.get(position) instanceof CompanyDetailHeader) {
            return TYPE_COMPANY_HEADER;
        } else if (companyData.get(position) instanceof Company.Organization.Link
                || companyData.get(position) instanceof Company.Organization.ContactInfo.EmailAddress) {
            return TYPE_COMPANY_URLS_EMAILS;
        } else if (companyData.get(position) instanceof Company.SocialProfile) {
            return TYPE_COMPANY_BIOS;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_COMPANY_HEADER:
                View viewCompanyLogo = inflater.inflate(R.layout.item_company_header,
                        parent
                        , false);
                viewHolder = new ViewHolderHeader(viewCompanyLogo);
                break;
            case TYPE_COMPANY_URLS_EMAILS:
                View viewCompanyUrl = inflater.inflate(R.layout.item_company_url_email,
                        parent,
                        false);
                viewHolder = new ViewHolderUrlsEmails(viewCompanyUrl);
                break;
            case TYPE_COMPANY_BIOS:
            default:
                View viewCompanyBio = inflater.inflate(R.layout.item_company_social_bio,
                        parent,
                        false);
                viewHolder = new ViewHolderBio(viewCompanyBio);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_COMPANY_HEADER:
                ViewHolderHeader viewHolderHeader = (ViewHolderHeader) holder;
                Picasso.with(context)
                        .load(((CompanyDetailHeader) companyData.get(position)).getLogo())
                        .placeholder(R.drawable.ic_photo_black_48dp)
                        .into(viewHolderHeader.companyLogo);
                viewHolderHeader.companyName.setText(((CompanyDetailHeader)
                        companyData.get(position)).getName());
                viewHolderHeader.companyFoundedYear.setText(((CompanyDetailHeader)
                        companyData.get(position)).getFounded());
                viewHolderHeader.companyEmployeeCount.setText(String.valueOf(((CompanyDetailHeader)
                        companyData.get(position)).getApproxEmployees()));
                break;
            case TYPE_COMPANY_URLS_EMAILS:
                ViewHolderUrlsEmails viewHolderUrlsEmails = (ViewHolderUrlsEmails) holder;
                if (companyData.get(position) instanceof Company.Organization.Link) {
                    viewHolderUrlsEmails.urlEmail.setText(((Company.Organization.Link)
                            companyData.get(position)).getUrl());
                } else {
                    viewHolderUrlsEmails.urlEmail.setText(((Company.Organization.ContactInfo.EmailAddress)
                            companyData.get(position)).getValue());
                }
                break;
            case TYPE_COMPANY_BIOS:
                ViewHolderBio viewHolderBio = (ViewHolderBio) holder;
                viewHolderBio.bioSource.setText(((Company.SocialProfile)
                        companyData.get(position)).getTypeName());
                viewHolderBio.bio.setText(((Company.SocialProfile)
                        companyData.get(position)).getBio());
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

    public class ViewHolderHeader extends RecyclerView.ViewHolder {
        @BindView(R.id.companyLogo)
        ImageView companyLogo;
        @BindView(R.id.companyName)
        TextView companyName;
        @BindView(R.id.companyFounded)
        TextView companyFoundedYear;
        @BindView(R.id.companyEmployees)
        TextView companyEmployeeCount;

        public ViewHolderHeader(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public class ViewHolderUrlsEmails extends RecyclerView.ViewHolder {
        @BindView(R.id.urlEmail)
        TextView urlEmail;

        public ViewHolderUrlsEmails(View view) {
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

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_COMPANY_HEADER = 0;
    private static final int TYPE_COMPANY_URLS_EMAILS = 1;
    private static final int TYPE_COMPANY_BIOS = 2;

    private static final int COMPANY_HEADER_SIZE = 1;

    //    private Company.Organization company;
//    private Company.SocialProfile company;
    private Company company;
    private Context context;

//    private List<Object> items;

    public CompanyRecyclerAdapter() {
    }

    @Override
    public int getItemViewType(int position) {

        if (isPositionHeader(position)) {
            return TYPE_COMPANY_HEADER;
        }
//        } else if () {
//
//        } else if () {
//
//        } else {
//
//        }

//        switch (position){
//            case COMPANY_MAIN_INFO_SIZE:
//                return TYPE_COMPANY_MAIN_INFO;
//            case COMPANY_MAIN_INFO_SIZE:
//                return TYPE_COMPANY_URLS;
//            case COMPANY_MAIN_INFO_SIZE:
//                return TYPE_COMPANY_EMAILS;
//            case COMPANY_MAIN_INFO_SIZE:
//                return TYPE_COMPANY_BIOS;
//        }

        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company_main_info, parent, false);
        return new ViewHolderMainInfo(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderMainInfo viewHolderMainInfo = (ViewHolderMainInfo) holder;
        Picasso.with(context)
                .load(company.getLogo())
                .into(viewHolderMainInfo.companyLogo);
        viewHolderMainInfo.companyWebsite.setText(company.getWebsite());
        viewHolderMainInfo.companyName.setText(company.getOrganization().getName());
        viewHolderMainInfo.companyFoundedYear.setText(company.getOrganization().getName());
        viewHolderMainInfo.companyEmployeeCount.setText(String.valueOf(company.getOrganization().getApproxEmployees()));

    }

    @Override
    public int getItemCount() {
        return company == null ? 0 : getCompanyDataSize();
    }

    private int getCompanyDataSize() {
        int companyDataSize = 0;

        if (company.getOrganization() != null) {
            companyDataSize += COMPANY_HEADER_SIZE;
            if (company.getOrganization().getLinks() != null) {
                companyDataSize += company.getOrganization().getLinks().size();
            }
            if (company.getOrganization().getContactInfo() != null
                    && company.getOrganization().getContactInfo().getEmailAddresses() != null) {
                companyDataSize += company.getOrganization().getContactInfo().getEmailAddresses().size();
            }
        }

        if (company.getSocialProfiles() != null) {
            companyDataSize += company.getSocialProfiles().size();
        }

        return companyDataSize;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public class ViewHolderMainInfo extends RecyclerView.ViewHolder {

        @BindView(R.id.companyLogo)
        ImageView companyLogo;
        @BindView(R.id.companyName)
        TextView companyName;
        @BindView(R.id.companyFounded)
        TextView companyFoundedYear;
        @BindView(R.id.companyEmployees)
        TextView companyEmployeeCount;
        @BindView(R.id.companyWebsite)
        TextView companyWebsite;

        public ViewHolderMainInfo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

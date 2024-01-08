package com.zoho.client.api.dto.contact;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter
@Setter
public class CreateContactRequest {

        private String contact_id;

        @Size(max = 200, message = "Contact name length must be at most 200")
        @NotNull(message = "Contact name is required")
        public String contact_name;

        @NotNull(message="Company name is required")
        public String company_name;

        public String website;
        public String language_code;
        public String contact_type;
        public String customer_sub_type;
        public Double credit_limit;
        public ArrayList<Tag> tags;
        public Boolean is_portal_enabled;
        public String currency_id;
        public Integer payment_terms;
        public String payment_terms_label;
        public String notes;
        public BillingAddress billing_address;
        public ShippingAddress shipping_address;
        public ArrayList<ContactPerson> contact_persons;
        public DefaultTemplates default_templates;
        public ArrayList<CustomField> custom_fields;
        public Double opening_balance_amount;
        public Double exchange_rate;
        public String vat_reg_no;
        public String owner_id;
        public String tax_reg_no;
        public String country_code;
        public String vat_treatment;
        public String tax_treatment;
        public String tax_regime;
        public Boolean is_tds_registered;
        public String place_of_contact;
        public String gst_no;
        public String gst_treatment;
        public String tax_authority_name;
        public String avatax_exempt_no;
        public String avatax_use_code;
        public String tax_exemption_id;
        public String tax_exemption_code;
        public String tax_authority_id;
        public String tax_id;
        public String tds_tax_id;
        public Boolean is_taxable;
        public String facebook;
        public String twitter;
        public Boolean track_1099;
        public String tax_id_type;
        public String tax_id_value;
}

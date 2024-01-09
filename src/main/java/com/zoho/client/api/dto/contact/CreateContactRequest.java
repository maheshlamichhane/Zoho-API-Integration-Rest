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
        private String contact_name;

        @NotNull(message="Company name is required")
        private String company_name;

        private String website;
        private String language_code;
        private String contact_type;
        private String customer_sub_type;
        private Double credit_limit;
        private ArrayList<Tag> tags;
        private Boolean is_portal_enabled;
        private String currency_id;
        private Integer payment_terms;
        private String payment_terms_label;
        private String notes;
        private BillingAddress billing_address;
        private ShippingAddress shipping_address;
        private ArrayList<ContactPerson> contact_persons;
        private DefaultTemplates default_templates;
        private ArrayList<CustomField> custom_fields;
        private Double opening_balance_amount;
        private Double exchange_rate;
        private String vat_reg_no;
        private String owner_id;
        private String tax_reg_no;
        private String country_code;
        private String vat_treatment;
        private String tax_treatment;
        private String tax_regime;
        private Boolean is_tds_registered;
        private String place_of_contact;
        private String gst_no;
        private String gst_treatment;
        private String tax_authority_name;
        private String avatax_exempt_no;
        private String avatax_use_code;
        private String tax_exemption_id;
        private String tax_exemption_code;
        private String tax_authority_id;
        private String tax_id;
        private String tds_tax_id;
        private Boolean is_taxable;
        private String facebook;
        private String twitter;
        private Boolean track_1099;
        private String tax_id_type;
        private String tax_id_value;
}

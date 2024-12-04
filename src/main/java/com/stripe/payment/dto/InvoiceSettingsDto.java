package com.stripe.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceSettingsDto {
	
	@JsonProperty("custom_fields")
    private String customFields;

    @JsonProperty("default_payment_method")
    private String defaultPaymentMethod;

    @JsonProperty("footer")
    private String footer;

    @JsonProperty("rendering_options")
    private String renderingOptions;

}

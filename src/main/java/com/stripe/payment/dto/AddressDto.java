package com.stripe.payment.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

	@JsonProperty("city")
	private String city;

	@JsonProperty("country")
	private String country;

	@JsonProperty("line1")
	private String line1;

	@JsonProperty("line2")
	private String line2;
	
	@JsonProperty("postal_code")
	private String postalCode;

	@JsonProperty("state")
	private String state;

}

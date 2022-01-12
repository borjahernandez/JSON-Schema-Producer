package clients.JsonClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"name",
		"ticker",
		"id",
		"address"
})

@JsonSchemaTitle("Company")

public class Company {
	@JsonProperty("name") private String name;
	@JsonProperty("ticker") private String ticker;
	@JsonProperty("id") private String id;
	@JsonProperty("address") private String address;

	public Company(String name, String ticker, String id, String address) {
		this.name = name;
		this.ticker = ticker;
		this.id = id;
		this.address = address;
	}
}

package clients.JsonClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"first_name",
		"last_name",
		"id",
		"email"
})

@JsonSchemaTitle("Customer")

public class Customer {

	@JsonProperty("first_name") private String first_name;
	@JsonProperty("last_name") private String last_name;
	@JsonProperty("id") private Integer id;
	@JsonProperty("email") private String email;

	public Customer(String first_name, String last_name, Integer id, String email) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.id = id;
		this.email = email;
	}
}

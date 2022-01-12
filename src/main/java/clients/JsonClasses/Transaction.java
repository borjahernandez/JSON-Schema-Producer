package clients.JsonClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"num_shares",
		"amount",
		"txn_ts",
		"customer",
		"company"
})

@JsonSchemaTitle("Transaction")

public class Transaction {
	@JsonProperty("num_shares") private Integer num_shares;
	@JsonProperty("amount") private Double amount;
	@JsonProperty("txn_ts") private String txn_ts;
	@JsonProperty("customer") private Customer customer;
	@JsonProperty("company") private Company company;

	public Transaction(Integer num_shares, Double amount, String txn_ts, Customer customer, Company company) {
		this.num_shares = num_shares;
		this.amount = amount;
		this.txn_ts = txn_ts;
		this.customer = customer;
		this.company = company;
	}
}

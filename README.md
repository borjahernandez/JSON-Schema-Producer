# JSON Schema Producer - Confluent Cloud

1. Configure the `confluent_cloud.config` file
2. Create the topic `financial_txns`
3. Use `./gradlew run --console plain --args="./confluent_cloud.config"` to start the producer


### Sample of the JSON payload sent to Kafka
```
{
  "num_shares": 50000,
  "amount": 50044568.89,
  "txn_ts": "2020-11-18 02:31:43",
  "customer": {
    "first_name": "Jill",
    "last_name": "Smith",
    "id": 1234567,
    "email": "jsmith@gmail.com"
  },
  "company": {
    "name": "ACME Corp",
    "ticker": "ACMC",
    "id": "ACME837275222752952",
    "address": "Anytown USA, 333333"
  }
}
```

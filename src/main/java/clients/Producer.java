package clients;

import clients.JsonClasses.Company;
import clients.JsonClasses.Customer;
import clients.JsonClasses.Transaction;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class Producer {
  static final String DATA_DIRECTORY = "./src/main/resources/json-schema/";
  static final String KAFKA_TOPIC = (System.getenv("TOPIC") != null) ? System.getenv("TOPIC") : "financial_txns";

  /**
   * Java producer.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Starting Java producer.");

    // Creating the Kafka producer
    final Properties settings = loadConfig(args[0]);
    settings.put(ProducerConfig.CLIENT_ID_CONFIG, "demo-json-schema-producer");
    settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class);

    final KafkaProducer<String, Transaction> producer = new KafkaProducer<>(settings);

    
    // Adding a shutdown hook to clean up when the application exits
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("Closing producers.");
      producer.close();
    }));

    File folder = new File(DATA_DIRECTORY);
    File[] listOfFiles = folder.listFiles();


    int id = 1;
    assert listOfFiles != null;
    for (File file : listOfFiles) {
      if (file.isFile()) {
        final String transaction_string = new Scanner(new File(DATA_DIRECTORY + file.getName())).useDelimiter("\\Z").next();
        final JSONObject transaction_json = new JSONObject(transaction_string);
        final JSONObject company_json = transaction_json.getJSONObject("company");
        final JSONObject customer_json = transaction_json.getJSONObject("customer");
        final Company company = new Company(
                company_json.getString("name"),
                company_json.getString("ticker"),
                company_json.getString("id"),
                company_json.getString("address"));
        final Customer customer = new Customer(
                customer_json.getString("first_name"),
                customer_json.getString("last_name"),
                customer_json.getInt("id"),
                customer_json.getString("email"));
        final Transaction transaction_value = new Transaction(
                transaction_json.getInt("num_shares"),
                transaction_json.getDouble("amount"),
                transaction_json.getString("txn_ts"),
                customer,
                company);
        final ProducerRecord<String, Transaction> record = new ProducerRecord<String, Transaction>(KAFKA_TOPIC, String.valueOf(id), transaction_value);
        producer.send(record);
        id++;
      }
    }

  }

  public static Properties loadConfig(final String configFile) throws IOException {
    if (!Files.exists(Paths.get(configFile))) {
      throw new IOException(configFile + " not found.");
    }
    final Properties cfg = new Properties();
    try (InputStream inputStream = new FileInputStream(configFile)) {
      cfg.load(inputStream);
    }
    return cfg;
  }
}
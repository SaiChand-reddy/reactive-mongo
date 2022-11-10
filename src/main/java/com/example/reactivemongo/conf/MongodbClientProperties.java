package com.example.reactivemongo.conf;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static java.util.concurrent.TimeUnit.*;


@NoArgsConstructor
@Data
@ConfigurationProperties(
        prefix = "com.example.reactivemongo.data",
        ignoreUnknownFields = false)
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class MongodbClientProperties {

    //Using default value from ConnectionPoolSettings
    int connectTimeoutMS = 10_000;
    //Cosmos DB SLA's defined available here https://azure.microsoft.com/en-us/support/legal/sla/cosmos-db/v1_2/
    //P99 Latency greater than or equal to 10ms for document read or 15ms for document write operations
    //Search queries might take longer
    int readTimeoutMS = 1000;
    //Using default value from ConnectionPoolSettings
    int maxSize = 100;
    //defaulting value
    int minSize = 2;
    //Using default value from ConnectionPoolSettings
    int maxWaitQueueSize = 500;
    //Using default value (2 mins) from ConnectionPoolSettings
    long maxWaitTimeMS = 12_0000L;
    //defaulting value
    //For MongoDB, you will have better results with shorter keepalive periods, on the order of 120 seconds
    long maxConnectionLifeTimeMS = MILLISECONDS.convert(2, MINUTES);
    //defaulting value
    long maxConnectionIdleTimeMS = MILLISECONDS.convert(1, MINUTES);
    //defaulting value
    Long maintenanceInitialDelayMS = MILLISECONDS.convert(1, MINUTES);
    //Using default value from ConnectionPoolSettings
    long maintenanceFrequencyMS = MILLISECONDS.convert(30, SECONDS);
    //jmx metrics enabled
    boolean jmxMetricsEnabled = false;
    //server selector timeout
    long serverSelectorTimeoutMS = 1000;
}

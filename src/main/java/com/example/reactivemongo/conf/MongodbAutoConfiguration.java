package com.example.reactivemongo.conf;

import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.management.JMXConnectionPoolListener;
import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@ConditionalOnClass(MongoClient.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties(MongodbClientProperties.class)
public class MongodbAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer(
            final MongodbClientProperties mongodbClientProperties) {
        return builder -> {
            builder.applyToClusterSettings(clusterSettings ->
                clusterSettings.serverSelectionTimeout(mongodbClientProperties.getServerSelectorTimeoutMS(),
                                                       TimeUnit.MILLISECONDS)
            );
            builder.applyToSocketSettings(socketSettings -> {
                socketSettings.connectTimeout(mongodbClientProperties.getConnectTimeoutMS(), TimeUnit.MILLISECONDS);
                socketSettings.readTimeout(mongodbClientProperties.getReadTimeoutMS(), TimeUnit.MILLISECONDS);
            });
            builder.applyToConnectionPoolSettings(
                    connectionPoolSettings -> applyMongoDBConnectionPoolSettings(mongodbClientProperties,
                            connectionPoolSettings));
        };
    }

    /**
     * Set the MongoDB client properties to ConnectionPoolSettings.Builder object.
     * @param mongodbClientProperties
     * @param connectionPoolSettings
     */
    private static void applyMongoDBConnectionPoolSettings(
            final MongodbClientProperties mongodbClientProperties,
            final ConnectionPoolSettings.Builder connectionPoolSettings) {
        connectionPoolSettings.maxSize(mongodbClientProperties.getMaxSize());
        connectionPoolSettings.minSize(mongodbClientProperties.getMinSize());
        connectionPoolSettings.maxWaitQueueSize(mongodbClientProperties.getMaxWaitQueueSize());
        connectionPoolSettings.maxConnectionLifeTime(mongodbClientProperties.getMaxConnectionLifeTimeMS(),
                TimeUnit.MILLISECONDS);
        connectionPoolSettings.maxConnectionIdleTime(mongodbClientProperties.getMaxConnectionIdleTimeMS(),
                TimeUnit.MILLISECONDS);
        connectionPoolSettings.maxWaitTime(mongodbClientProperties.getMaxWaitTimeMS(), TimeUnit.MILLISECONDS);
        connectionPoolSettings.maintenanceInitialDelay(mongodbClientProperties.getMaintenanceInitialDelayMS(),
                TimeUnit.MILLISECONDS);
        connectionPoolSettings.maintenanceFrequency(mongodbClientProperties.getMaintenanceFrequencyMS(),
                TimeUnit.MILLISECONDS);
        if (mongodbClientProperties.isJmxMetricsEnabled()) {
            connectionPoolSettings.addConnectionPoolListener(new JMXConnectionPoolListener());
        }
    }

}

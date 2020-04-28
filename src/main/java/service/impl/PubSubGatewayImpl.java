package service.impl;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import service.PubSubGatewayInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PubSubGatewayImpl implements PubSubGatewayInterface {

    @Override
    public void sendError() throws ExecutionException, InterruptedException, IOException {

        String topicId = "tci-pse-topic-report-error";
        int messageCount =1;
        ProjectTopicName topicName = ProjectTopicName.of("tci-microservices-262022", topicId);
        Publisher publisher = null;
        List<ApiFuture<String>> futures = new ArrayList<>();
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new FileInputStream(new File("tci-microservices-262022-67b0ad07806a.json")));
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).setCredentialsProvider(FixedCredentialsProvider.create(googleCredentials)).build();

            for (int i = 0; i < messageCount; i++) {
                String message = "message-test-deyviz-perez-" + i;

                // convert message to bytes
                ByteString data = ByteString.copyFromUtf8(message);
                PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

                // Schedule a message to be published. Me|ssages are automatically batched.
                ApiFuture<String> future = publisher.publish(pubsubMessage);
                futures.add(future);
            }
        } finally {
            // Wait on any pending requests
            List<String> messageIds = ApiFutures.allAsList(futures).get();

            for (String messageId : messageIds) {
                System.out.println(messageId);
            }

            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
            }
        }
    }

}

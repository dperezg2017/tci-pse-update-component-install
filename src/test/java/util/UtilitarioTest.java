package util;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import org.apache.log4j.Logger;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UtilitarioTest {

    Logger logger = Logger.getLogger(Utilitario.class);

    @Test
    public void pubsub_sendError_ok() throws IOException, ExecutionException, InterruptedException
    {
        String topicId = "tci-pse-topic-report-error";
        int messageCount =1;
        ProjectTopicName topicName = ProjectTopicName.of("tci-microservices-262022", topicId);
        Publisher publisher = null;
        List<ApiFuture<String>> futures = new ArrayList<>();
        try {
            GoogleCredentials googleCredentials =
                    GoogleCredentials.
                            fromStream(new FileInputStream("tci-microservices-262022-67b0ad07806a.json"));
            //fromStream(new FileInputStream("D:\\Deyviz Perez\\Proyectos\\tci-pse-update-component-install\\v1.0.0.0\\src\\main\\resources\\tci-microservices-262022-67b0ad07806a.json"));
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).setCredentialsProvider(FixedCredentialsProvider.create(googleCredentials)).build();
            for (int i = 0; i < messageCount; i++) {
                String message = "message-test-deyviz-perez-" + i;

                // convert message to bytes
                ByteString data = ByteString.copyFromUtf8(message);
                PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

                // Schedule a message to be published. Messages are automatically batched.
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

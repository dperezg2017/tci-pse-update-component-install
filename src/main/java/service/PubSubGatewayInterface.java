package service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface PubSubGatewayInterface {

  void sendError() throws ExecutionException, InterruptedException, IOException;
}

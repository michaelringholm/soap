package dk.commentor.pinfo.soap.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import pitrans70ver6.OpdateringFuldBestand;

@Endpoint
public class UpdateCustomerEndpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCustomerEndpoint.class);

  @PayloadRoot(namespace = "PITrans70Ver6", localPart = "OpdateringFuldBestand")
  @ResponsePayload
  public void updateCustomerList(@RequestPayload OpdateringFuldBestand request) {
    // Call dotnet core via REST here <-----
    LOGGER.info("updateCustomerList() called.",request.getCprData());
  }
}
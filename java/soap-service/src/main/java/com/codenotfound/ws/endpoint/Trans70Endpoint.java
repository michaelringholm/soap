package com.codenotfound.ws.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import pitrans70ver6.OpdateringFuldBestand;

@Endpoint
public class Trans70Endpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(Trans70Endpoint.class);

  @PayloadRoot(namespace = "PITrans70Ver6", localPart = "OpdateringFuldBestand")
  @ResponsePayload
  public void opdaterFuldBestand(@RequestPayload OpdateringFuldBestand request) {
    LOGGER.info("Endpoint received cprData[%s]",request.getCprData());
  }
}
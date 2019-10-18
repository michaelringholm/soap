package com.codenotfound.ws.endpoint;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.xml.sax.SAXException;

public class CustomValidationInterceptor extends PayloadValidatingInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws IOException, SAXException, TransformerException {
    System.out.println("CustomValidationInterceptor triggered!");
    return super.handleRequest(messageContext, endpoint);
    }

}
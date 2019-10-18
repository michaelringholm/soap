package com.codenotfound.ws.endpoint;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.xml.sax.SAXException;

public class CustomValidationInterceptor extends PayloadValidatingInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws IOException, SAXException, TransformerException {
        System.out.println("CustomValidationInterceptor triggered!");
        javax.xml.transform.Source payloadSource = messageContext.getRequest().getPayloadSource(); // javax.xml.transform.dom.DOMSource
        try {
            String soapXML = toString(payloadSource);
        } catch (Exception e) {
            throw new TransformerException(e);
        }
        // IOUtils.toString(payloadSource.getClass)
        return super.handleRequest(messageContext, endpoint);
    }

    private String toString(Source payloadSource) throws Exception {
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(payloadSource, result);
        String soapXML = writer.toString();
        System.out.println("XML IN String format is: \n" + soapXML);
        return soapXML;
    }

}
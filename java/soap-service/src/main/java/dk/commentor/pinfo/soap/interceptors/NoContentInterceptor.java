package dk.commentor.pinfo.soap.interceptors;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

public class NoContentInterceptor extends EndpointInterceptorAdapter {

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception e) throws Exception {
        if (!messageContext.hasResponse()) {
            TransportContext tc = TransportContextHolder.getTransportContext();
            if (tc != null && tc.getConnection() instanceof HttpServletConnection) {
                HttpServletConnection connection = ((HttpServletConnection) tc.getConnection());
                connection.setFaultCode(null);
                connection.getHttpServletResponse().setStatus(204);
                messageContext.setResponse(createEmptySOAPEnvelope());
            }
        }
    }

    private WebServiceMessage createEmptySOAPEnvelope() throws Exception {
        MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage soapMessage = factory.createMessage();
        return new SaajSoapMessage(soapMessage);
    }
}
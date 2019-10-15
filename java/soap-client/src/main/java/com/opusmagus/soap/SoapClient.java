package com.opusmagus.soap;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SoapClient {
    public static void main(String args[]) {
        System.out.println("SoapClient started...");
        try {            
            Map<String,String> appProperties = getAppProperties();
            String soapURL = appProperties.get("soap-url");
            System.out.println(String.format("SOAP URL = %s", soapURL));
            CallRESTMethod();
            CallSOAPMethod(soapURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SoapClient ended.");
    }

    private static  Map<String, String> getAppProperties() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/main/resources/app.dev.properties"));
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet())
            map.put(entry.getKey().toString(), entry.getValue().toString());
        return map;
    }

    private static void CallSOAPMethod(String soapURL) throws Exception {
        System.out.println("Calling SOAP method...");
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(soapURL);
            httpPost.addHeader("Content-Type", "text/xml");
            String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ss=\"http://tempuri.org/\">"
                    + "<soapenv:Body>" + "<ss:Echo>" + "<ss:msg>Please echo this</ss:msg>" + "</ss:Echo>"
                    + "</soapenv:Body>" + "</soapenv:Envelope>";
            HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            String responseData = EntityUtils.toString(response.getEntity());
            System.out.println(String.format("Response from %s", soapURL));
            System.out.println("===============================");
            System.out.println(responseData);
        } finally {
            if (client != null)
                client.close();
        }

        System.out.println("Calling SOAP method done.");
    }

    private static void CallRESTMethod() throws Exception {
        System.out.println("Calling REST method...");
        String url = "https://httpbin.org/post";
        HttpPost post = new HttpPost(url);

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", "abc"));
        urlParameters.add(new BasicNameValuePair("password", "123"));
        urlParameters.add(new BasicNameValuePair("custom", "secret"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(String.format("Response from %s", url));
            System.out.println("===============================");
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
        System.out.println("Calling REST method done.");
    }
}
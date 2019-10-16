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

public class SoapService {
    public static void main(String args[]) {
        System.out.println("SoapService started...");
        try {            
            Map<String,String> appProperties = getAppProperties();
            String soapURL = appProperties.get("soap-url");
            System.out.println(String.format("SOAP URL = %s", soapURL));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SoapService ended.");
    }

    private static  Map<String, String> getAppProperties() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/main/resources/app.dev.properties"));
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet())
            map.put(entry.getKey().toString(), entry.getValue().toString());
        return map;
    }    
}
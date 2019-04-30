package com.ifd.mijnapi;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Arrays;

public class Util {
    static HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            String auth = "admin" + ":" + "test";
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }
}

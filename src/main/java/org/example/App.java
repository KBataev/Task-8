package org.example;

import org.example.JSONToModel.Transform;
import org.example.REST.Request;
import org.example.model.User;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {


        Request.GetRequest();

    }
}

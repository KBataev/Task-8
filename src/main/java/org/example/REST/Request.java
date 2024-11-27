package org.example.REST;

import org.example.JSONToModel.Transform;
import org.example.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Request {
    public static final String url = "http://94.198.50.185:7081/api/users";
    public static RestTemplate restTemplate = new RestTemplate();
    public static String sessionId;

    public static void GetRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        HttpHeaders responseHeaders = response.getHeaders();
        List<String> cookies = responseHeaders.get("Set-Cookie");
        if (cookies != null && !cookies.isEmpty()) {
            sessionId = cookies.get(0).split(";")[0]; // Извлекаем только sessionId
            System.out.println("Session ID: " + sessionId);
        } else {
            System.out.println("Не удалось получить Session ID.");
            return;
        }

        List<User> users = Transform.ToUser(response.getBody());


    }



}

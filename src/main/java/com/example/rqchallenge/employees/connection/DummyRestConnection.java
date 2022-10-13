package com.example.rqchallenge.employees.connection;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.rqchallenge.util.DummyServiceUrls;

@Component
public class DummyRestConnection {

  @Autowired
  private RestTemplate restTemplate;

  public String getAllEmployees() throws HttpClientErrorException {

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);

      return restTemplate
          .exchange(DummyServiceUrls.GET_ALL_EMPLOYEES.url, HttpMethod.GET, entity, String.class)
          .getBody();
    } catch (HttpClientErrorException httpClientErrorException) {
      throw httpClientErrorException;
    }

  }

  public String getEmployeeById(String id) throws HttpClientErrorException {

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);

      return restTemplate.exchange(String.format(DummyServiceUrls.GET_EMPLOYEE_ID.url, id),
          HttpMethod.GET, entity, String.class).getBody();

    } catch (HttpClientErrorException httpClientErrorException) {

      throw httpClientErrorException;
    }
  }

  public String createEmployee(Map<String, Object> employeeInput) throws HttpClientErrorException {

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

      HttpEntity<Map<String, Object>> request = new HttpEntity<>(employeeInput, headers);

      ResponseEntity<String> result =
          restTemplate.postForEntity(DummyServiceUrls.CREATE_EMPlOYEE.url, request, String.class);

      return result.getBody();
    } catch (HttpClientErrorException httpClientErrorException) {
      throw httpClientErrorException;
    }
  }

  public String deleteEmployee(String id) throws HttpClientErrorException {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);

      ResponseEntity<String> result =
          restTemplate.exchange(String.format(DummyServiceUrls.DELETE_EMPLOYEE.url, id),
              HttpMethod.DELETE, entity, String.class);
      return result.getBody();
    } catch (HttpClientErrorException httpClientErrorException) {
      throw httpClientErrorException;
    }
  }

}

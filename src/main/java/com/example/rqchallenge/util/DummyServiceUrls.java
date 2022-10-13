package com.example.rqchallenge.util;

public enum DummyServiceUrls {
  
  GET_ALL_EMPLOYEES("https://dummy.restapiexample.com/api/v1/employees"),
  GET_EMPLOYEE_ID("https://dummy.restapiexample.com/api/v1/employee/%s"),
  CREATE_EMPlOYEE("https://dummy.restapiexample.com/api/v1/create"),
  DELETE_EMPLOYEE("https://dummy.restapiexample.com/api/v1/delete/%s");
  
  public final String url;

  private DummyServiceUrls(String url) {
      this.url = url;
  } 

}

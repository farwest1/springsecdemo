package com.bmoellerit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bernd on 28.02.2021.
 * <p>
 * Package com.bmoellerit.controller
 */
@RestController("/hello")
public class HelloController {
  @GetMapping()
  public String getHello(){
    return "Hello";
  }
}

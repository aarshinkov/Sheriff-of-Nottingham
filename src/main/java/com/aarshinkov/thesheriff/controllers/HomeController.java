package com.aarshinkov.thesheriff.controllers;

import com.aarshinkov.thesheriff.base.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
public class HomeController extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @GetMapping(value = "/")
  public String home() {
    return "redirect:/players";
  }
}

package com.aarshinkov.thesheriff.controllers;

import com.aarshinkov.thesheriff.base.*;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController extends Base
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  @GetMapping(value = "/")
  public String home(Model model)
  {
    
    model.addAttribute("notUsedPlayers", getNotUsedPlayers());
    
    return "home";
  }
}

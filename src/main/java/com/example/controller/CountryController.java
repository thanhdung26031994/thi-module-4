package com.example.controller;

import com.example.model.City;
import com.example.model.Country;
import com.example.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("country")
public class CountryController {
    @Autowired
    private ICountryService iCountryService;

    @GetMapping("")
    public ModelAndView listCountry(){
        ModelAndView modelAndView = new ModelAndView("/home");
        Iterable<Country> countries = iCountryService.findAll();
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }
}

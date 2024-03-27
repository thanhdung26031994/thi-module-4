package com.example.controller;

import com.example.model.City;
import com.example.model.Country;
import com.example.service.ICityService;
import com.example.service.ICountryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private ICityService iCityService;

    @Autowired
    private ICountryService iCountryService;
    @ModelAttribute("country")
    public Iterable<Country> listCountry(){
        return iCountryService.findAll();
    }
    @GetMapping
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("/home");
        Iterable<City> cities = iCityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("cities", new City());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("city") City city){
        iCityService.save(city);
        return "redirect:/city";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<City> cityOptional = iCityService.findById(id);
        if (cityOptional.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("city", cityOptional.get());
            return modelAndView;
        }
        return new ModelAndView("error");
    }
    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute City city){
        iCityService.save(city);
        return "redirect:/city";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Optional<City> cityOptional = iCityService.findById(id);
        if (cityOptional.isPresent()){
            iCityService.remove(id);
            return "redirect:/city";
        }
        return "redirect:/error";
    }
}

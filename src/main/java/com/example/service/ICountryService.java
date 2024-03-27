package com.example.service;

import com.example.model.Country;

public interface ICountryService {
    Iterable<Country> findAll();
}

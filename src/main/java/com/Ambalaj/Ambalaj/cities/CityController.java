package com.Ambalaj.Ambalaj.cities;

import com.Ambalaj.Ambalaj.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/city")
public class CityController {

    @GetMapping
//    @PreAuthorize("hasRole('USER')")
    public String GetCities() {
        return "cities";
    }
}

package com.in28minutes.rest.webservices.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webservices.restfulwebservices.entity.SomeBean;

@RestController
public class FilteringController {

    // value1, value2
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1",
                "value2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    // value2, value3
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveList() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value1", "value2", "value3"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value3",
                "value2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeans);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}

package com.minutes.rest.webservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/static-filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "vlaue2", "value3");
	}

	@GetMapping("/static-filtering-list")
	public List<SomeBean> retrieveSomeBeanLists() {
		return Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("v4", "v5", "v6"));

	}

	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retrieveSomeBeanByDynamicFiltering() {
		SomeBean someBean = new SomeBean("value1", "vlaue2", "value3");
		String[] getValues = { "field1", "field2" };
		return applyFiltering(getValues, someBean);
		// return mapping;
	}

	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue retrieveSomeBeanListsByDynamicFiltering() {
		List<SomeBean> someBeans = Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("v4", "v5", "v6"));
		String[] getValues = { "field1", "field3" };
		return applyFiltering(getValues, someBeans);

	}

	public static MappingJacksonValue applyFiltering(String[] getValues, Object obj) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(getValues);
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(obj);
		mapping.setFilters(filters);
		return mapping;

	}
}

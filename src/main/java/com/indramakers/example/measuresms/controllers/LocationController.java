package com.indramakers.example.measuresms.controllers;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.indramakers.example.measuresms.model.entities.Locations;
import com.indramakers.example.measuresms.services.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private LocationService locationService;

	@PostMapping
	public void create(@RequestBody Locations location) {
		locationService.registerLocation(location);
	}

	@GetMapping("/locationId")
	public List<Locations> getLocationsById(@RequestParam(name = "id") int id) {

		return locationService.getLocationsById(id);
	}

	@DeleteMapping("/eliminar")
	public int delete(@RequestParam(name = "id") int id) throws SQLException {
		return locationService.delete(id);
	}

}
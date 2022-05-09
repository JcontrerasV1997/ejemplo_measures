package com.indramakers.example.measuresms.controllers;

import com.indramakers.example.measuresms.config.Routes;
import com.indramakers.example.measuresms.model.entities.Device;
import com.indramakers.example.measuresms.model.entities.Measure;
import com.indramakers.example.measuresms.model.requests.MeasureValueRequest;
import com.indramakers.example.measuresms.services.DeviceService;
import com.indramakers.example.measuresms.services.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Routes.DEVICES_PATH)
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private MeasureService measureService;

	/**
	 * URL /devices
	 * 
	 * @param device
	 */
	@PostMapping
	public void create(@Valid @RequestBody Device device) {
		deviceService.createDevice(device);
	}
	
	@GetMapping(Routes.DEVICE_LOCATION)
	public void getDeviceLocation(@RequestParam(name = "id") int id) {
		deviceService.getDeviceLocation(id);
		
	}

	/**
	 * GET /devices?marca={{marca}},
	 * 
	 * @param branch
	 * @return
	 */
	@GetMapping
	public List<Device> getDevicesByBranch(@RequestParam(name = "branch") String branch) {
		return deviceService.getBytBranch(branch);
	}
//wipidi
	/**
	 * GET /devices/by-branch?branch={{valor}}
	 * 
	 * @param branch
	 * @return
	 */
	@GetMapping(Routes.DEVICE_BY_BRANCH_PATH)
	public List<Device> getDevicesByBranch2(@RequestParam(name = "branch") String branch) {
		return deviceService.getBytBranch(branch);
	}

	/**
	 * PATH /devices/{deviceId}/measures POST PARAMS: body -> { value: 12312 }
	 */
	@PostMapping(Routes.MEASURES_BY_DEVICE_PATH)
	public void addMeasureToDevice(@Valid @RequestBody MeasureValueRequest request,
			@PathVariable("deviceId") int deviceId) {

		measureService.registerMeasure(deviceId, request.getValue());
	}

	/**
	 * GET /devices/{id}/measures
	 */

	@GetMapping(Routes.MEASURES_BY_DEVICE_PATH)
	public List<Measure> getDeviceMeasures(@PathVariable("deviceId") int deviceId) {

		return measureService.getMeasuresByDevice(deviceId);
	}

}

package demo.springframework.devices.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.springframework.devices.model.Device.Device;
import demo.springframework.devices.service.DevicesService;

@RestController
@RequestMapping("/devices")
public class DevicesController {
	@Autowired 
	DevicesService devicesService;
		
	@GetMapping(value = "/{deviceId}")
	private Device getDeviceById(@PathVariable Long deviceId)  {
		return  devicesService.getDeviceById(deviceId);
	}

}

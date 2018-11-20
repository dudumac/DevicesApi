package demo.springframework.devices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.springframework.devices.model.Device.Device;
import demo.springframework.devices.repository.DevicesRepository;

@Service
public class DevicesService {

	@Autowired
	DevicesRepository devicesRepository;
	
	public DevicesService(DevicesRepository devicesRepository) {
		this.devicesRepository = devicesRepository;
	}

	public Device getDeviceById(long deviceId) {
		Device device =  devicesRepository.findById(deviceId);
		System.err.println("Device is " + device);
		return device;
	}

}

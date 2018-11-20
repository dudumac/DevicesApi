package demo.springframework.devices.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.springframework.devices.model.Device.Device;

@Repository
public interface DevicesRepository extends CrudRepository<Device, Long> {

	public Device findById(long deviceId);
}

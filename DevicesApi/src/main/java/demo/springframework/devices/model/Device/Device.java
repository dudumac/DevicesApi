package demo.springframework.devices.model.Device;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Device {

	@Id
	private long id;
	private String deviceName;
	private String serialId;

	public Device(long id, String deviceName, String serialId) {
		this.id = id;
		this.deviceName = deviceName;
		this.serialId = serialId;
	}
}

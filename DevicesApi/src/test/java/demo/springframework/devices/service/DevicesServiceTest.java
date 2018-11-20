package demo.springframework.devices.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import demo.springframework.devices.model.Device.Device;
import demo.springframework.devices.repository.DevicesRepository;

@RunWith(MockitoJUnitRunner.class)
public class DevicesServiceTest {
	DevicesService devicesService;

	@Mock
	DevicesRepository devicesRepository;

	@Before
	public void setUp() throws Exception {
		devicesService = new DevicesService(devicesRepository);
	}

	@Test
	public void getDeviceByIdReturnsDeviceInfo() {
		// Given
		BDDMockito.given(devicesRepository.findById(1L))
				.willReturn(new Device(Long.valueOf(1), "powertrain-ecu", "123456789"));

		// When
		Device device = devicesService.getDeviceById(1L);

		// Then
		assertThat(device.getDeviceName()).isEqualTo("powertrain-ecu");
		assertThat(device.getSerialId()).isEqualTo("123456789");
	}

}

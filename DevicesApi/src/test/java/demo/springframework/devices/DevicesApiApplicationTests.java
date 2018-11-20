package demo.springframework.devices;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import demo.springframework.devices.model.Device.Device;
import demo.springframework.devices.repository.DevicesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DevicesApiApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {
	}

	
	@Test
	public void getDeviceByIsShouldReturnDeviceInfo() {
		// Given
		
		// When
		ResponseEntity<Device> response = testRestTemplate.getForEntity("/devices/{id}", Device.class, 1);
		
		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getId()).isEqualTo(1);
		Assertions.assertThat(response.getBody().getDeviceName()).isEqualTo("powertrain-ecu");
		Assertions.assertThat(response.getBody().getSerialId()).isEqualTo("123456789");
	}

	@Ignore
	@Test
	public void getDevicesShouldReturnListOfAllAvailableDevices() {
		// Given
		
		// When
		ResponseEntity<List<Device>> response = testRestTemplate.exchange(
		  "/devices",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<Device>>(){});
		List<Device> devices = response.getBody();
		
		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(devices.size()).isEqualTo("3");
	}
}

// TODO - Method to bootstrap the DB (H2 for these integration tests)
// Bootstrap EndToEnd Test Data
@Component
class SampleDataCLR implements CommandLineRunner {
	private DevicesRepository devicesRepository;
	
	public SampleDataCLR(DevicesRepository devicesRepository) {
		this.devicesRepository = devicesRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("@@@@@@@@@@@@@@ Entering SampleDataCLR : run");
		devicesRepository.save(new Device(1, "powertrain-ecu","123456789"));
		devicesRepository.save(new Device(2, "dashboard-ecu","987654321"));
		devicesRepository.findAll().forEach(System.out :: println);
		System.err.println("@@@@@@@@@@@@@@ Leaving SampleDataCLR : run");
	}
}


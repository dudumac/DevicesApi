package demo.springframework.devices.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import demo.springframework.devices.model.Device.Device;
import demo.springframework.devices.service.DevicesService;

@RunWith(SpringRunner.class)
@WebMvcTest(DevicesController.class)
public class DevicesControllterTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	DevicesService devicesService;

	@Test
	public void getDeviceByIdShouldReturnDeviceInfo() throws Exception {
		BDDMockito.given(devicesService.getDeviceById(ArgumentMatchers.anyLong()))
				.willReturn(new Device(1L, "powertrain-ecu", "123456789"));

		mockMvc.perform(MockMvcRequestBuilders.get("/devices/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("deviceName").value("powertrain-ecu"))
				.andExpect(MockMvcResultMatchers.jsonPath("serialId").value("123456789"));
	}

}

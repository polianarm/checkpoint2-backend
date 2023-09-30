package br.com.dentalclinica;

import br.com.dentalclinica.api.dto.request.PacienteRequest;
import br.com.dentalclinica.domain.service.ClinicaService;
import br.com.dentalclinica.domain.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class DentalclinicApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@SpyBean
	private PacienteService pacienteService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(mvc);
	}

	@Test
	void dadoUmObjetoValido_quandoChamamosCriarPaciente_entaoRetornarObjetoMockado() throws Exception {
		PacienteRequest paciente = Fixture.PacienteFake.anyPaciente();

		String patientAsJson = mapper.writeValueAsString(paciente);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post("/v1/pacientes")
				.content(patientAsJson)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(request)
				.andDo(print())
				.andExpect(status().isCreated());
	}

	@Test
	void dado5RegistrosNoBanco_quandoChamamosEndpointBuscarPacientes_entaoRetornarRegistrosPaginados() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get("/v1/pacientes")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(request)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", equalTo(5)))
				.andExpect(jsonPath("$", hasKey("size")));
	}
}

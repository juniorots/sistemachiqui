package com.orthochiqui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orthochiqui.controller.ClienteController;
import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.model.Orcamento;
import com.orthochiqui.model.PerfilCliente;
import com.orthochiqui.model.Procedimento;
import com.orthochiqui.model.Telefone;
import com.orthochiqui.service.impl.ClienteServiceImpl;

@WebMvcTest(ClienteController.class)
public class SistemaChiquiApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ClienteServiceImpl clienteService;
	
	@BeforeEach
	void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    //This  
	    Arrays.stream(webApplicationContext.getBeanDefinitionNames())
	            .map(name -> webApplicationContext.getBean(name).getClass().getName())
	            .sorted()
	            .forEach(System.out::println);
	}
	
	@Test
	void testSave() throws ClienteNotFoundException {
		Cliente c = new Cliente();
		c.setProntuario("A-01");
		c.setNome("Cliente 01");
		c.setCpf("000.111.222-33");
		c.setIndicacao("S");
		c.setIndicacaoResponsavel("PANFLETO 01");
		PerfilCliente pc = new PerfilCliente();
		pc.setNome("GRUPO PADRAO");
		c.setPerfilCliente(pc);
		Telefone t = new Telefone();
		t.setNumero("(11) 22333-4455");
		c.getTelefones().add(t);
		Orcamento o = new Orcamento();
		o.setDtOrcamento(Calendar.getInstance().getTime());
		Procedimento p = new Procedimento();
		p.setNome("Profilaxia");
		p.setNrDente("N/A");
		p.setVrProcedimento(new BigDecimal(130));
		o.getProcedimentos().add(p);
		c.getOrcamentos().add(o);
		
		try {
			mockMvc.perform(post("/api/clientes")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(c)))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}

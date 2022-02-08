package com.orthochiqui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orthochiqui.controller.ClienteController;
import com.orthochiqui.controller.ContatoController;
import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.exception.ContatoNotFoundException;
import com.orthochiqui.model.Banco;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.model.Contato;
import com.orthochiqui.model.Orcamento;
import com.orthochiqui.model.PerfilCliente;
import com.orthochiqui.model.Procedimento;
import com.orthochiqui.model.Telefone;
import com.orthochiqui.service.impl.ClienteServiceImpl;
import com.orthochiqui.service.impl.ContatoServiceImpl;
import com.orthochiqui.util.ClienteMapping;

@WebMvcTest({ClienteController.class, ContatoController.class})
public class SistemaChiquiApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ClienteServiceImpl clienteService;
	
	@MockBean
	private ContatoServiceImpl contatoService;
	
	@MockBean
	ClienteMapping clienteMapping;
	
	@BeforeEach
	void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    //This  
	    Arrays.stream(webApplicationContext.getBeanDefinitionNames())
	            .map(name -> webApplicationContext.getBean(name).getClass().getName())
	            .sorted()
	            .forEach(System.out::println);
	}
	Cliente getCliente() {
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
		return c;
	}

	Contato getContato() {
		Contato c = new Contato();
		c.setNome("Contato 01");
		c.setCro("CRO-01");
		c.setEmail("email01@gmail.com");
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date d = format.parse("10/01/1990");
			c.setDtNascimento(d);
		} catch (ParseException e) {
			c.setDtNascimento(null);
		}
		c.setCpfCnpj("000.111.222-33");
		Telefone t = new Telefone();
		t.setNumero("(11) 22222-3333");
		c.getTelefones().add(t);
		Banco b = new Banco();
		b.setNome("Banco do Brasil");
		b.setTpConta("Conta Corrente");
		b.setAgencia("0000");
		b.setNrConta("1122334455");
		b.setOp("N/A");
		b.setTpPix("CPF");
		b.setChavePix("111.333.444-55");
		c.setBanco(b);		
		return c;
	}
	
	@Test
	void testSaveCliente() throws ClienteNotFoundException {
		try {
			mockMvc.perform(post("/api/clientes")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(getCliente())))
					.andExpect(status().isCreated());
			
			mockMvc.perform(MockMvcRequestBuilders
								.get("/api/clientes/prontuario/A-01")
								.accept(MediaType.APPLICATION_JSON))
//								.andDo(print())
								.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	void testeUpdateCliente() throws ClienteNotFoundException {
		Cliente tmp = getCliente();
		tmp.setNome("CLIENTE EDITADO");
		try {
			mockMvc.perform(MockMvcRequestBuilders
					.put("/api/clientes/prontuario/A-01")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(tmp)))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testeDeleteCliente() throws ClienteNotFoundException {
		try {
			mockMvc.perform(MockMvcRequestBuilders
					.delete("/api/clientes/prontuario/A-01")
					.contentType("application/json")
					.accept("application/json"))
					.andExpect(status().isNoContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void listarClientesByNome() throws ClienteNotFoundException {
		try {
			mockMvc.perform(MockMvcRequestBuilders
					.get("/api/clientes/Cliente")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	void testSaveContato() throws ContatoNotFoundException {
		try {
			mockMvc.perform(post("/api/contatos")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(getContato())))
					.andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}

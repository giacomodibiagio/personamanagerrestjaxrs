package it.prova.personamanager.web.rest.resources;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.prova.personamanager.model.Persona;
import it.prova.personamanager.service.MyServiceFactory;
import it.prova.personamanager.service.PerosnaService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

@Path("/persona")
public class PersonaResource {
	private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());

	@Context
	HttpServletRequest request;

	private PerosnaService personaService;

	public PersonaResource() {
		personaService = MyServiceFactory.getPersonaServiceInstance();
	}
	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersona(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaDaCaricare = null;
		String risultato = null;
		try {
			personaDaCaricare = MyServiceFactory.getPersonaServiceInstance().caricaSingoloElemento(id);
			ObjectMapper objectMapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
			objectMapper.setDateFormat(df);
			risultato = objectMapper.writeValueAsString(personaDaCaricare);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(risultato).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertiNuovapersona(Persona personaInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			MyServiceFactory.getPersonaServiceInstance().inserisciNuovo(personaInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(personaInput).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Persona> result = null;
		String risultato = null;
		try {
			result = MyServiceFactory.getPersonaServiceInstance().listAllElements();
			ObjectMapper objectMapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
			objectMapper.setDateFormat(df);
			risultato = objectMapper.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(risultato).build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPersona(@QueryParam("nome") String nome, @QueryParam("cognome") String cognome) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona example = new Persona();
		example.setNome(nome);
		example.setCognome(cognome);
		List<Persona> result = null;
		String risultato = null;
		try {
			result = MyServiceFactory.getPersonaServiceInstance().findByExample(example);
			ObjectMapper objectMapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
			objectMapper.setDateFormat(df);
			risultato = objectMapper.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(risultato).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	public Response deletepersona(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			Persona personaDaEliminare = MyServiceFactory.getPersonaServiceInstance().caricaSingoloElemento(id);
			MyServiceFactory.getPersonaServiceInstance().rimuovi(personaDaEliminare);
			return Response.status(200).entity("Rimossa persona con id: "+id).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
		}


	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornapersona(Persona personaInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		try {
			MyServiceFactory.getPersonaServiceInstance().aggiorna(personaInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(personaInput).build();
	}
	
}

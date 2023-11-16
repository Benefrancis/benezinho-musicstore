package br.com.fiap.domain.resource;

import br.com.fiap.Main;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
public class IndexResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
        return Response.ok( "Sejam Bem vindos à API - Benezinho Music Store  🎤🤓👍🏽" ).build();
    }

}

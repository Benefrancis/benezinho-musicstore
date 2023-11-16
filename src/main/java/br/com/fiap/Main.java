package br.com.fiap;

import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.infra.configuration.cors.CORSFilter;
import br.com.fiap.infra.configuration.exception.ConstraintViolationExceptionMapper;
import br.com.fiap.infra.configuration.exception.ExceptionHandling;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost/api";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()
                // Configure container response filters (CORSFilter)
                .register( CORSFilter.class )

                // Configure ExceptionHandling (ExceptionHandling)
                .register( ExceptionHandling.class )

                // Configure ExceptionHandling para validações (ConstraintViolationExceptionMapper)
                .register( ConstraintViolationExceptionMapper.class )

                // Configure ConnectionFactory
                .register( ConnectionFactory.build() )

                // Configure os pacotes em que temos Recursos da API REST
                .packages( "br.com.fiap.domain.resource" );
        return GrizzlyHttpServerFactory.createHttpServer( URI.create( BASE_URI ), rc );
    }

    public static void main(String[] args) {
        var server = startServer();
        System.out.println( String.format(
                "Benezinho Music Store App 🎤🤓👍🏽   started with endpoints available " +
                        "as %s%nHit Ctrl-C to stop it....", BASE_URI ) );
        try {
            System.in.read();
            server.stop();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
}
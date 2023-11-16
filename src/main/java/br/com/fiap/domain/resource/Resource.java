package br.com.fiap.domain.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;

public interface Resource<T, U> {

    public Response findAll();

    public Response findById(U id);

    public Response persist(@Valid T t);

}

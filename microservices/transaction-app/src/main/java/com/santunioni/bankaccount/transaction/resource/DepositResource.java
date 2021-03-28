package com.santunioni.bankaccount.transaction.resource;

import com.santunioni.bankaccount.transaction.domain.Deposit;
import com.santunioni.bankaccount.transaction.service.DepositService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/deposits")
public class DepositResource {

    private final DepositService depositService = DepositService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Deposit deposit) {
        return depositService.create(deposit);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return depositService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") final String id) {
        return depositService.findById(id);
    }

}

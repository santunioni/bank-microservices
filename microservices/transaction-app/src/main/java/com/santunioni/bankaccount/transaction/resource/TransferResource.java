package com.santunioni.bankaccount.transaction.resource;

import com.santunioni.bankaccount.transaction.domain.Transfer;
import com.santunioni.bankaccount.transaction.service.TransferService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/transfers")
public class TransferResource {

    private final TransferService transferService = TransferService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Transfer transfer) {
        return transferService.create(transfer);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return transferService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") final String id) {
        return transferService.findById(id);
    }

}

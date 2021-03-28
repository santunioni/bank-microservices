package com.santunioni.bankaccount.transaction.resource;

import com.santunioni.bankaccount.transaction.domain.Withdraw;
import com.santunioni.bankaccount.transaction.service.WithdrawService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/withdraws")
public class WithdrawResource {

    private final WithdrawService withdrawService = WithdrawService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Withdraw withdraw) {
        return withdrawService.create(withdraw);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return withdrawService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") final String id) {
        return withdrawService.findById(id);
    }

}

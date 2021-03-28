package com.santunioni.bankaccount.balance.resource;

import com.santunioni.bankaccount.balance.domain.Account;
import com.santunioni.bankaccount.balance.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static java.util.Optional.ofNullable;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.status;

@ApplicationScoped
@Path("/accounts")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);
    private final AccountService accountService = AccountService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Account account) {
        log.debug("Creating the account " + account);
        try {
            account = accountService.create(account);
        } catch (Exception e)
        {
            System.out.println("Account already exist.");
        }
        return Response
                .created(URI.create(account.getAccountId().replaceAll(" ","_")))
                .build();
    }

//    @DELETE
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response delete(Account account) {
//        log.debug("Deleting the account " + account);
//        accountService.delete(account);
//        return Response.noContent().build();
//    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        log.debug("List accounts...");
        return ofNullable(accountService.listAll())
                .map(Response::ok)
                .orElse(status(NOT_FOUND))
                .build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listById(@PathParam("id") final String id) {
        log.debug("Getting the account " + id);
//        return ofNullable(accountService.findById(id))
//                .map(Response::ok)
//                .orElse(status(NOT_FOUND))
//                .build();
        return Response.noContent().build();
    }

}

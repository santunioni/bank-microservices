package com.santunioni.bankaccount.transaction.resource;

import com.santunioni.bankaccount.transaction.domain.Withdraw;
import com.santunioni.bankaccount.transaction.repository.WithdrawRepository;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

//@Produces("application/json")
@ApplicationScoped
@Path("/withdraws")
public class WithdrawResource {

    private final Logger log = LoggerFactory.getLogger(TransferResource.class);

    private final Emitter<Withdraw> withdrawEmitter;
    private final WithdrawRepository withdrawRepository;

    @Inject
    public WithdrawResource(@Channel("transactions/withdraws") Emitter<Withdraw> withdrawEmitter,
                            WithdrawRepository withdrawRepository) {
        this.withdrawEmitter = withdrawEmitter;
        this.withdrawRepository = withdrawRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Withdraw withdraw) {
        log.debug("Creating the withdraw " + withdraw);

        if (withdraw.getUuid() == null) {
            withdraw = new Withdraw(
                    withdraw.getAccountId(),
                    withdraw.getValue());
        }

        var createdWithdraw = withdrawRepository.save(withdraw);
        withdrawEmitter.send(createdWithdraw);

        return Response
                .created(URI.create(withdraw.getUuid().toString()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        log.debug("Getting all the withdraws");
        return Response.ok(withdrawRepository.findAll()).build();

    }
    //TODO: implement the findById() method for transfers
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findById(@PathParam("id") final UUID id) {
//        log.debug("Getting the transfer with id: " + id);
//        return Optional
//                .ofNullable(withdrawRepository.findById(id))
//                .map(Response::ok)
//                .orElse(Response.status(NOT_FOUND))

//                .build();

//    }

//    TODO: implement the update() method for transfers
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response update(Transfer transfer) {
//        log.debug("Updating the transaction " + transfer);
//        return Response.ok(withdrawRepository.update(transfer)).build();
//    }

    // TODO: implement the delete() method for transfers
//    @DELETE
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response delete(@PathParam("id") final UUID id) {
//        log.debug("Deleting the transaction " + id);
//        withdrawRepository.deleteById(id);
//        return Response.noContent().build();
//    }
}

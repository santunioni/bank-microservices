package com.santunioni.bankaccount.transaction.resource;

import com.santunioni.bankaccount.transaction.domain.Deposit;
import com.santunioni.bankaccount.transaction.domain.Transfer;
import com.santunioni.bankaccount.transaction.domain.Withdraw;
import com.santunioni.bankaccount.transaction.repository.DepositRepository;
import com.santunioni.bankaccount.transaction.repository.TransferRepository;
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

@ApplicationScoped
@Path("/transfers")
public class TransferResource {

    private final Logger log = LoggerFactory.getLogger(TransferResource.class);

    private final DepositResource depositResource;
    private final WithdrawResource withdrawResource;
    private final TransferRepository transferRepository;
    @Inject
    public TransferResource(DepositResource depositResource,
                            WithdrawResource withdrawResource,
                            TransferRepository transferRepository) {
        this.depositResource = depositResource;
        this.withdrawResource = withdrawResource;
        this.transferRepository = transferRepository;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Transfer transfer) {

        transfer.validate();

        log.debug("Creating the transaction " + transfer);

        withdrawResource.create(new Withdraw(
                transfer.getUuid(),
                transfer.getFromAccountID(),
                transfer.getValue()));

        depositResource.create(new Deposit(
                transfer.getUuid(),
                transfer.getToAccountID(),
                transfer.getValue()));

        transferRepository.save(transfer);

        return Response
                .created(URI.create(transfer.getUuid().toString()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        log.debug("Getting all the transfers");
        return Response.ok(transferRepository.findAll()).build();

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

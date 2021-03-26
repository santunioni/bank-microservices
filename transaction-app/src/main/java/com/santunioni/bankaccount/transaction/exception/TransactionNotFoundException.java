//package com.santunioni.bankaccount.transaction.exception;
//
//import javax.ws.rs.ClientErrorException;
//import javax.ws.rs.core.Response;
//import java.util.UUID;
//
//
//public class TransactionNotFoundException extends ClientErrorException {
//
//    public TransactionNotFoundException(UUID id) {
//        super(String.format("Transaction with id %s not found in the book.", id),
//                Response.Status.NOT_FOUND);
//    }
//
//}

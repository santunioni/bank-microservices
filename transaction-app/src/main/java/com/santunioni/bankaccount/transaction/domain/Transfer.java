package com.santunioni.bankaccount.transaction.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Transfer {

    @Id
    private String uuid = UUID.randomUUID().toString();
    @Column(name = "from_account_id")
    private String fromAccountID;
    @Column(name = "to_account_id")
    private String toAccountID;
    @Column(name = "value")
    private double value;

    public void validate() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }

}

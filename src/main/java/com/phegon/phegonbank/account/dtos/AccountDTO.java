package com.phegon.phegonbank.account.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.phegon.phegonbank.auth_users.dtos.UserDTO;
import com.phegon.phegonbank.auth_users.entity.User;
import com.phegon.phegonbank.enums.AccountStatus;
import com.phegon.phegonbank.enums.AccountType;
import com.phegon.phegonbank.enums.Currency;
import com.phegon.phegonbank.transaction.dtos.TransactionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    private String accountNumber;

    private BigDecimal balance;

    private AccountType accountType;

    @JsonBackReference // this will not be added to the account dto. it will bw ignored because it is back reference
    private UserDTO user;

    private Currency currency;

    private AccountStatus status;

    @JsonManagedReference  // if helps avoid recursion loop by ignoring the userDTO within the AccountDTO
    private List<TransactionDTO> transactions;

    private LocalDateTime closeAt;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}

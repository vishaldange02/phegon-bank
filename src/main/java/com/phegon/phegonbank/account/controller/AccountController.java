package com.phegon.phegonbank.account.controller;

import com.phegon.phegonbank.account.services.AccountService;
import com.phegon.phegonbank.res.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/me")
    public ResponseEntity<Response<?>> getMyAccount(){
        return ResponseEntity.ok(accountService.getMyAccounts());
    }

    @DeleteMapping("/close/{accountNumber}")
    public ResponseEntity<Response<?>> closeAccount(@PathVariable String accountNumber){
        return ResponseEntity.ok(accountService.closeAccount(accountNumber));
    }

}

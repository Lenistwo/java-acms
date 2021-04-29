package pl.xisp.auth.controller;

import org.springframework.web.bind.annotation.*;
import pl.xisp.auth.document.Account;
import pl.xisp.auth.document.AccountToken;
import pl.xisp.auth.service.AuthenticationService;
import pl.xisp.shared.model.*;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService service) {
        this.authenticationService = service;
    }

    @PostMapping("/login")
    public Response<AccountToken> login(@RequestBody LoginRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/logout")
    public Response<Map<String, String>> logout(@RequestBody LogoutRequest request) {
        return authenticationService.logout(request);
    }

    @PostMapping("/verify-credentials")
    public Response<Account> verifyCredentials(@RequestBody VerifyCredentialsRequest request) {
        return authenticationService.checkCredentials(request);
    }

    @PostMapping("/remind-password-sms")
    public Response<Map<String, String>> remindPasswordSendSms(@RequestBody RemindPasswordRequest request) {
        return authenticationService.remindPasswordSendSms(request);
    }

    @PostMapping("/remind-password-mail/{code}")
    public Response<Map<String, String>> remindPasswordSendMail(@PathVariable String code) {
        return authenticationService.remindPasswordSendMail(code);
    }

    @PutMapping("/password-change")
    public Response<Map<String, String>> changePassword(@RequestBody PasswordChangeRequest request) {
        return authenticationService.changePassword(request);
    }

    @GetMapping("/{id}/tokens")
    public Response<List<AccountToken>> getAccountTokens(@PathVariable String id) {
        return authenticationService.getTokens(id);
    }

    @GetMapping("/account/{accountId}")
    public Response<Account> getAccountById(@PathVariable String accountId) {
        return authenticationService.getAccountById(accountId);
    }
}

package pl.xisp.auth.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.xisp.auth.document.Account;
import pl.xisp.auth.document.AccountToken;
import pl.xisp.auth.document.RemindPassword;
import pl.xisp.auth.exception.*;
import pl.xisp.auth.repository.AccountRepository;
import pl.xisp.auth.repository.AccountTokenRepository;
import pl.xisp.auth.repository.RemindPasswordRepository;
import pl.xisp.shared.model.*;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.service.MailService;
import pl.xisp.shared.service.SmsService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static pl.xisp.shared.constants.MessageConstants.PASSWORD_CHANGE_SUBJECT;
import static pl.xisp.shared.constants.UrlConstants.PASSWORD_CHANGE_URL;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("${token.expire.time}")
    private int TOKEN_EXPIRE_TIME;
    private static final boolean STATUS = true;

    private final SmsService smsService;
    private final MailService mailService;
    private final GoogleAuthenticator authenticator;
    private final AccountRepository accountRepository;
    private final AccountTokenRepository tokenRepository;
    private final RemindPasswordRepository remindPasswordRepository;

    public Response<AccountToken> authenticate(LoginRequest request) {
        var account = accountRepository.findByMail(request.getMail()).orElseThrow(AccountNotFoundException::new);

        verifyPassword(request.getMail(), request.getPassword());

        if (!account.isActive()) throw new AccountInactiveException();

        if (nonNull(account.getSecret2F()) && !authenticator.authorize(account.getSecret2F(), request.getCode())) {
            throw new AuthorizationException();
        }

        var token = createToken(account.getId(), request.getUseragent(), request.getAddress());
        tokenRepository.save(token);

        return new Response<>(STATUS, token);
    }

    public Response<Account> checkCredentials(VerifyCredentialsRequest request) {
        Account account = verifyPassword(request.getMail(), request.getPassword());
        return new Response<>(STATUS, account);
    }

    public Response<Map<String, String>> logout(LogoutRequest request) {
        var accountToken = tokenRepository.findAllByAccount(request.getAccountId());
        tokenRepository.deleteAll(accountToken);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> remindPasswordSendSms(RemindPasswordRequest request) {
        var account = accountRepository.findByMail(request.getMail()).orElseThrow(AccountNotFoundException::new);
        if (isNull(account.getPhoneNumber())) {
            throw new InvalidPhoneNumberException();
        }

        var send = smsService.send(account.getPhoneNumber());

        var remindPassword = new RemindPassword(account.getId(), send.getCode());
        remindPasswordRepository.save(remindPassword);

        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> remindPasswordSendMail(String code) {
        var remindPassword = remindPasswordRepository.findByCode(code).orElseThrow(RemindPasswordNotFound::new);
        var customerAccount = accountRepository.findById(remindPassword.getAccountId()).orElseThrow(AccountNotFoundException::new);

        remindPassword.setSmsConfirmed(true);

        remindPasswordRepository.save(remindPassword);
        var message = new Message(customerAccount.getMail(), PASSWORD_CHANGE_SUBJECT, PASSWORD_CHANGE_URL + remindPassword.getId());
        mailService.sendMailAsync(message);

        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> changePassword(PasswordChangeRequest request) {
        var remindPassword = remindPasswordRepository.findById(request.getRemindId()).orElseThrow(RemindPasswordNotFound::new);
        var customerAccount = accountRepository.findById(remindPassword.getAccountId()).orElseThrow(AccountNotFoundException::new);

        if (!remindPassword.isSmsConfirmed()) {
            throw new PasswordChangeConfirmedException();
        }

        customerAccount.setPassword(request.getPassword());

        remindPasswordRepository.delete(remindPassword);
        accountRepository.save(customerAccount);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    private Account verifyPassword(String mail, String password) {
        var adminAccount = accountRepository.findByMail(mail).orElseThrow(AccountNotFoundException::new);
        if (adminAccount.getPassword().equals(password)) {
            return adminAccount;
        }
        throw new BadCredentialsException();
    }

    private AccountToken createToken(String accountId, String userAgent, String address) {
        var token = new AccountToken();
        token.setAccount(accountId);
        token.setCreated(System.currentTimeMillis());
        token.setExpires(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
        token.setUseragent(userAgent);
        token.setAddress(address);
        return token;
    }

    public Response<List<AccountToken>> getTokens(String accountId) {
        var collect = tokenRepository.findAllByAccountOrderByExpiresDesc(accountId);
        return new Response<>(STATUS, collect);
    }

    public Response<Account> getAccountById(String accountId) {
        return new Response<>(STATUS, accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new));
    }
}

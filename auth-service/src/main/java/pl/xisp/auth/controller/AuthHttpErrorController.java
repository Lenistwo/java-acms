package pl.xisp.auth.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.xisp.shared.response.Response;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthHttpErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public Response<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return new Response<>(false, "ERROR_DURING_PROCESSING_REQUEST " + status.toString());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}

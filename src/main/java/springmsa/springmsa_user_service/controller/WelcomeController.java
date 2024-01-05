package springmsa.springmsa_user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class WelcomeController {

    private final Environment environment;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the user service !";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("user-service-request") String header) {
        log.info("From api gateway header: {}", header);
        return "Filter Check";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port = {}", request.getServerPort());
        return String.format("Service on PORT = %s", environment.getProperty("local.server.port"));
    }
}

package mg.itu.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/bibliothecaire/index")
    public String goToBibliothecaire() {
        return "bibliothecaire/index"; // login.jsp
    }
}

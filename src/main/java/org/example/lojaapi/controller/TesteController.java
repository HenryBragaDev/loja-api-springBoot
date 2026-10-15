package org.example.lojaapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TesteController {
    @GetMapping("/teste")
    public String teste(){
        return "teste funcionando (loja)";
    }
}

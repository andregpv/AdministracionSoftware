package com.example.demo.controller;

import com.example.demo.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mensaje", "");
        return "calculator";
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam("a") double a,
                           @RequestParam("b") double b,
                           @RequestParam("operacion") String operacion,
                           Model model) {

        String mensaje;

        try {
            double resultado;
            switch (operacion) {
                case "sumar":
                    resultado = calculatorService.sumar(a, b);
                    break;
                case "restar":
                    resultado = calculatorService.restar(a, b);
                    break;
                case "multiplicar":
                    resultado = calculatorService.multiplicar(a, b);
                    break;
                case "dividir":
                    resultado = calculatorService.dividir(a, b);
                    break;
                default:
                    throw new IllegalArgumentException("Operación no válida");
            }
            mensaje = "Resultado: " + resultado;
        } catch (Exception e) {
            mensaje = "Error: " + e.getMessage();
        }

        model.addAttribute("mensaje", mensaje);
        return "calculator";
    }
}

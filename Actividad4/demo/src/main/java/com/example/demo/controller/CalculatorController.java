package com.example.demo.controller;

import com.example.demo.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String index() {
        return "calculator"; // ← nombre del HTML en /resources/templates/
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam double a,
                           @RequestParam double b,
                           @RequestParam String operacion,
                           Model model) {
        double resultado = 0;
        String mensaje;

        try {
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
        return "calculator"; // ← debe coincidir con el HTML
    }
}

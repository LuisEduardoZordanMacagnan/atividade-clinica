package br.com.ifsc.clinica.clinicateste.controller;

import br.com.ifsc.clinica.clinicateste.bd.FalsoBanco;
import br.com.ifsc.clinica.clinicateste.model.Paciente;
import br.com.ifsc.clinica.clinicateste.model.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
    FalsoBanco bd;

    public PacienteController() {
        bd = FalsoBanco.getInstance();
    }

    @GetMapping
    public ResponseEntity getAllPacientes() {
        return ResponseEntity.ok(bd.getAllPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity getPaciente(@PathVariable long id) {
        return ResponseEntity.ok(bd.getPaciente(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePaciente(@PathVariable long id) {
        bd.removePaciente(bd.getPaciente(id));
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity addPaciente(@RequestBody Paciente p) throws Exception {
        Paciente paciente = Paciente.builder()
                .cpf(p.getCpf())
                .dataNascimento(p.getDataNascimento())
                .nome(p.getNome())
                .telefone(p.getTelefone())
                .altura(p.getAltura())
                .peso(p.getPeso())
                .build();
        return ResponseEntity.ok(bd.addPaciente(paciente));
    }

    @GetMapping("/getIMC/{id}")
    public ResponseEntity getIMC(@PathVariable long id) {
        Paciente p = bd.getPaciente(id);
        float peso = p.getPeso();
        float altura = p.getAltura();
        double imc = peso / (altura * altura);

        return ResponseEntity.ok(Map.of("imc", imc));
    }

    @GetMapping("/getTMB/{id}")
    public ResponseEntity<?> getTMB(@PathVariable long id) {
        Double valorTmb = tmb(id);
        return ResponseEntity.ok(Map.of("tmb", valorTmb));
    }

    public Double tmb(long id) {
        Paciente p = bd.getPaciente(id);
        Integer idade = Period.between(p.getDataNascimento(), LocalDate.now()).getYears();
        Double tmb;
        double alturaCm = p.getAltura() * 100;
        if ( p.getSexo() == Pessoa.Sexo.MASCULINO ) {
            tmb = (10*p.getPeso())+(6.25*alturaCm)-(5*idade)+5;
        } else {
            tmb = (10*p.getPeso())+(6.25*alturaCm)-(5*idade)-161;
        }
        return tmb;
    }

    @GetMapping("/getGCD/{id}")
    public ResponseEntity getGCD(@PathVariable long id, @RequestParam int nivel) throws Exception {
        return ResponseEntity.ok(Map.of("gcd", gcd(id, nivel)));
    }

    public Double gcd(long id, int nivel) throws Exception {
        Paciente p = bd.getPaciente(id);
        Double fator = null;
        switch (nivel) {
            case 1: fator = 1.2; break;
            case 2: fator = 1.375; break;
            case 3: fator = 1.55; break;
            case 4: fator = 1.725; break;
            case 5: fator = 1.9; break;
            default: throw new Exception("Escolha um nível de 1 a 5");
        }
        Double tmb = tmb(id);
        Double gcd = tmb*fator;
        return gcd;
    }

    @GetMapping("/getMetaCal/{id}")
    public ResponseEntity getMetaCal(@PathVariable long id, @RequestParam int nivel, @RequestParam boolean isEmagrecer) throws Exception {
        Paciente p = bd.getPaciente(id);
        Double gcd = gcd(id, nivel);
        Double metaCal;
        if  ( isEmagrecer ) {
            metaCal = gcd-500;
        } else {
            metaCal = gcd+300;
        }
        metaCal = Math.round(metaCal * 100.0) / 100.0;
        return ResponseEntity.ok(Map.of("metaCal", metaCal));
    }
}

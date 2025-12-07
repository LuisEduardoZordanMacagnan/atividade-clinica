package br.com.ifsc.clinica.clinicateste.controller;

import br.com.ifsc.clinica.clinicateste.bd.FalsoBanco;
import br.com.ifsc.clinica.clinicateste.model.Exame;
import br.com.ifsc.clinica.clinicateste.model.Medico;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medico")
public class MedicoController {
    FalsoBanco bd;

    public MedicoController() {
        bd = FalsoBanco.getInstance();
    }

    @GetMapping
    public ResponseEntity getAllMedicos() {
        return ResponseEntity.ok(bd.getAllMedicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity getMedico(@PathVariable long id) {
        return ResponseEntity.ok(bd.getMedico(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMedico(@PathVariable long id) {
        bd.removeMedico(bd.getMedico(id));
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity addMedico(@RequestBody Medico m) throws Exception {
        Medico medico = Medico.builder()
                .cpf(m.getCpf())
                .CRM(m.getCRM())
                .dataNascimento(m.getDataNascimento())
                .nome(m.getNome())
                .sexo(m.getSexo())
                .telefone(m.getTelefone())
                .build();
        medico = bd.addMedico(medico);
        return ResponseEntity.ok(medico);
    }
}

package br.com.ifsc.clinica.clinicateste.controller;

import br.com.ifsc.clinica.clinicateste.bd.FalsoBanco;
import br.com.ifsc.clinica.clinicateste.model.Especialidade;
import br.com.ifsc.clinica.clinicateste.model.Medico;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/especialidade")
public class EspecialidadeCotroller {
    FalsoBanco bd;

    public EspecialidadeCotroller() {
        bd = FalsoBanco.getInstance();
    }

    @GetMapping
    public ResponseEntity getAllEspecialidades() {
        return ResponseEntity.ok(bd.getAllEspecialidades());
    }

    @GetMapping("/{id}")
    public ResponseEntity getEspecialidade(@PathVariable long id) {
        return ResponseEntity.ok(bd.getEspecialidade(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEspecialidade(@PathVariable long id) {
        bd.removeEspecialidade(bd.getEspecialidade(id));
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity addEspecialidade(@RequestBody Especialidade e) throws Exception {
        Especialidade especialidade = Especialidade.builder()
                .especialidade(e.getEspecialidade())
                .build();
        especialidade = bd.addEspecialidade(especialidade);
        return ResponseEntity.ok(especialidade);
    }

    @PostMapping("/atribuirMedico")
    public ResponseEntity atribuirMedico(@RequestParam long id, @RequestBody ArrayList<Long> idsMedicos) throws Exception {
        Especialidade especialidade = bd.getEspecialidade(id);
        if ( especialidade == null ) throw new Exception("Especialidade não encontrada");
        for (Long mid : idsMedicos) {
            Medico medico = bd.getMedico(mid);
            System.out.println(medico.getNome());
            if ( medico != null ) especialidade.adicionarMedico(medico);
        }
        bd.atualizarEspecialidade(especialidade);
        return ResponseEntity.ok(especialidade);
    }
}

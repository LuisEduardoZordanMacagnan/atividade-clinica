package br.com.ifsc.clinica.clinicateste.controller;

import br.com.ifsc.clinica.clinicateste.bd.FalsoBanco;
import br.com.ifsc.clinica.clinicateste.model.ConcluirExameDDO;
import br.com.ifsc.clinica.clinicateste.model.Exame;
import br.com.ifsc.clinica.clinicateste.model.Medico;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/exame")
public class ExameController {
    FalsoBanco bd;

    public ExameController() {
        bd = FalsoBanco.getInstance();
    }

    @GetMapping
    public ResponseEntity getAllExames(){
        return ResponseEntity.ok(bd.getAllExames());
    }

    @GetMapping("/{id}")
    public ResponseEntity getExame(@PathVariable long id){
        return ResponseEntity.ok(bd.getExame(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExame(@PathVariable long id){
        bd.removeExame(bd.getExame(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity saveExame(@RequestBody Exame e) throws Exception {
        Exame exame = Exame.buider()
                .especialidade(bd.getEspecialidade(e.getEspecialidade().getId()))
                .paciente(bd.getPaciente(e.getPaciente().getId()))
                .descricao(e.getDescricao())
                .build();
        exame = bd.addExame(exame);
        exame.getExameState().escolherMedico(exame, null);
        bd.atualizarExame(exame);
        return ResponseEntity.ok(exame);
    }

    @PostMapping("/pegarExame")
    public ResponseEntity pegarExame(@RequestParam long exameId, @RequestParam long medicoId) {
        Medico medico = bd.getMedico(medicoId);
        Exame exame = bd.getExame(exameId);
        boolean resposta = exame.getExameState().escolherMedico(exame, medico);
        bd.atualizarExame(exame);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("resposta", resposta);
        json.put("statusAtual", exame.getExameState().status());

        return ResponseEntity.ok(json);
    }

    @PostMapping("/agendarExame")
    public ResponseEntity agendarExame(@RequestParam long exameId, @RequestParam LocalDateTime data) {
        Exame exame = bd.getExame(exameId);
        boolean resposta = exame.getExameState().agendar(exame, data);
        bd.atualizarExame(exame);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("resposta", resposta);
        json.put("statusAtual", exame.getExameState().status());

        return ResponseEntity.ok(json);
    }

    @PostMapping("/concluirExame")
    public ResponseEntity concluirExame(@RequestBody ConcluirExameDDO data) {
        Exame exame = bd.getExame(data.exameId());
        boolean resposta = exame.getExameState().concluir(exame, data.conclusao());
        bd.atualizarExame(exame);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("resposta", resposta);
        json.put("statusAtual", exame.getExameState().status());

        return ResponseEntity.ok(json);
    }

    @PostMapping("/cancelarExame")
    public ResponseEntity cancelarExame(@RequestBody ConcluirExameDDO data){
        Exame exame = bd.getExame(data.exameId());
        boolean resposta = exame.getExameState().cancelar(exame, data.conclusao());
        bd.atualizarExame(exame);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("resposta", resposta);
        json.put("statusAtual", exame.getExameState().status());

        return ResponseEntity.ok(json);
    }
}

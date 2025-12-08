package br.com.ifsc.clinica.clinicateste.controller;

import br.com.ifsc.clinica.clinicateste.bd.FalsoBanco;
import br.com.ifsc.clinica.clinicateste.model.Paciente;
import br.com.ifsc.clinica.clinicateste.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IMCTest {

    private FalsoBanco bd;
    private PacienteController pc;

    @BeforeEach
    void setup() {
        pc = new PacienteController();
        bd = FalsoBanco.getInstance();
        bd.getAllPacientes().clear();

        Paciente p1 = Paciente.builder()
                .nome("A").sexo(Pessoa.Sexo.MASCULINO)
                .dataNascimento(LocalDate.now().minusYears(30))
                .peso(80f).altura(1.80f)
                .build();
        p1.setId(1L);
        bd.addPaciente(p1);

        Paciente p2 = Paciente.builder()
                .nome("B").sexo(Pessoa.Sexo.FEMININO)
                .dataNascimento(LocalDate.now().minusYears(25))
                .peso(60f).altura(1.65f)
                .build();
        p2.setId(2L);
        bd.addPaciente(p2);

        Paciente p3 = Paciente.builder()
                .nome("C").sexo(Pessoa.Sexo.MASCULINO)
                .dataNascimento(LocalDate.now().minusYears(40))
                .peso(110f).altura(1.90f)
                .build();
        p3.setId(3L);
        bd.addPaciente(p3);

        Paciente p4 = Paciente.builder()
                .nome("D").sexo(Pessoa.Sexo.FEMININO)
                .dataNascimento(LocalDate.now().minusYears(18))
                .peso(50f).altura(1.60f)
                .build();
        p4.setId(4L);
        bd.addPaciente(p4);
    }

    private double extractIMC(long id) {
        Map<String, Object> map =
                (Map<String, Object>) pc.getIMC(id).getBody();

        return (double) map.get("imc");
    }

    @Test
    void testIMC() {

        assertEquals(24.69, Math.round(extractIMC(1) * 100.0) / 100.0, 0.01);
        assertEquals(22.04, Math.round(extractIMC(2) * 100.0) / 100.0, 0.01);
        assertEquals(30.47, Math.round(extractIMC(3) * 100.0) / 100.0, 0.01);
        assertEquals(19.53, Math.round(extractIMC(4) * 100.0) / 100.0, 0.01);
    }
}

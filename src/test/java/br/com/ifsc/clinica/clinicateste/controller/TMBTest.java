package br.com.ifsc.clinica.clinicateste.controller;

import br.com.ifsc.clinica.clinicateste.bd.FalsoBanco;
import br.com.ifsc.clinica.clinicateste.model.Paciente;
import br.com.ifsc.clinica.clinicateste.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TMBTest {

    private FalsoBanco bd;
    private PacienteController pc;

    @BeforeEach
    void setup() {
        pc = new PacienteController();
        bd = FalsoBanco.getInstance();
        bd.getAllPacientes().clear();

        // Pacientes
        Paciente p1 = Paciente.builder()
                .nome("A").sexo(Pessoa.Sexo.MASCULINO)
                .dataNascimento(LocalDate.now().minusYears(30))
                .peso(80.0f).altura(1.80f)
                .build();
        p1.setId(1L);
        bd.addPaciente(p1);

        Paciente p2 = Paciente.builder()
                .nome("B").sexo(Pessoa.Sexo.FEMININO)
                .dataNascimento(LocalDate.now().minusYears(25))
                .peso(60.0f).altura(1.65f)
                .build();
        p2.setId(2L);
        bd.addPaciente(p2);

        Paciente p3 = Paciente.builder()
                .nome("C").sexo(Pessoa.Sexo.MASCULINO)
                .dataNascimento(LocalDate.now().minusYears(40))
                .peso(100.0f).altura(1.75f)
                .build();
        p3.setId(3L);
        bd.addPaciente(p3);

        Paciente p4 = Paciente.builder()
                .nome("D").sexo(Pessoa.Sexo.FEMININO)
                .dataNascimento(LocalDate.now().minusYears(18))
                .peso(50.0f).altura(1.60f)
                .build();
        p4.setId(4L);
        bd.addPaciente(p4);
    }

    @Test
    void testTMB() {
        assertEquals(1780.0, pc.tmb(1), 0.0); // masculino 30 anos, 80kg, 1.80m
        assertEquals(1345.25, pc.tmb(2), 0.0); // feminino 25 anos, 60kg, 1.65m
        assertEquals(1898.75, pc.tmb(3), 0.0); // masculino 40 anos, 100kg, 1.75m
        assertEquals(1249, pc.tmb(4), 0.0); // feminino 18 anos, 50kg, 1.60m
    }
}

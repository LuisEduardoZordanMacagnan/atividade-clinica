package br.com.ifsc.clinica.clinicateste.controller;

import br.com.ifsc.clinica.clinicateste.bd.FalsoBanco;
import br.com.ifsc.clinica.clinicateste.model.Paciente;
import br.com.ifsc.clinica.clinicateste.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GCDTest {

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
    void testGCD() throws Exception {
        assertEquals(2136.00, pc.gcd(1, 1), 0.1);

        assertEquals(2085.14, pc.gcd(2, 3), 0.1);

        assertEquals(3607.63, pc.gcd(3, 5), 0.1);

        assertEquals(1717.38, pc.gcd(4, 2), 0.1);
        }
    }

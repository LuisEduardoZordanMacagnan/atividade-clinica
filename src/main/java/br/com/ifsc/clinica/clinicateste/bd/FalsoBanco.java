package br.com.ifsc.clinica.clinicateste.bd;

import br.com.ifsc.clinica.clinicateste.model.Especialidade;
import br.com.ifsc.clinica.clinicateste.model.Exame;
import br.com.ifsc.clinica.clinicateste.model.Medico;
import br.com.ifsc.clinica.clinicateste.model.Paciente;

import java.util.ArrayList;

public class FalsoBanco {
    private static FalsoBanco instance;
    private ArrayList<Especialidade> especialidades;
    private int contadorEspecialidades;
    private ArrayList<Medico> medicos;
    private int contadorMedicos;
    private ArrayList<Exame> exames;
    private int contadorExames;
    private ArrayList<Paciente> pacientes;
    private int contadorPacientes;

    private FalsoBanco() {
        especialidades = new ArrayList<>();
        contadorEspecialidades = 0;
        medicos = new ArrayList<>();
        contadorMedicos = 0;
        exames = new ArrayList<>();
        contadorExames = 0;
        pacientes = new ArrayList<>();
        contadorPacientes = 0;
    }

    public static FalsoBanco getInstance() {
        if (instance == null) {
            instance = new FalsoBanco();
        }
        return instance;
    }

    public Especialidade addEspecialidade(Especialidade especialidade) {
        especialidade.setId(++contadorEspecialidades);
        especialidades.add(especialidade);
        return especialidade;
    }
    public void removeEspecialidade(Especialidade especialidade) { especialidades.remove(especialidade); }
    public ArrayList<Especialidade> getAllEspecialidades() { return especialidades; }
    public Especialidade getEspecialidade(long id) {
        for (Especialidade especialidade : especialidades) {
            if (especialidade.getId() == id) {
                return especialidade;
            }
        }
        return null;
    }
    public void atualizarEspecialidade(Especialidade especialidade) {
        especialidades.set(especialidades.indexOf(getEspecialidade(especialidade.getId())), especialidade);
    }

    public Medico addMedico(Medico medico) {
        medico.setId(++contadorMedicos);
        medicos.add(medico);
        return medico;
    }
    public void removeMedico(Medico medico) { medicos.remove(medico); }
    public ArrayList<Medico> getAllMedicos() { return medicos; }
    public Medico getMedico(long id) {
        for (Medico medico : medicos) {
            if (medico.getId() == id) {
                return medico;
            }
        }
        return null;
    }
    public void atualizarMedico(Medico medico) {
        medicos.set(medicos.indexOf(getMedico(medico.getId())), medico);
    }

    public Exame addExame(Exame exame) {
        exame.setId(++contadorExames);
        exames.add(exame);
        return exame;
    }
    public void removeExame(Exame exame) { exames.remove(exame); }
    public ArrayList<Exame> getAllExames() { return exames; }
    public Exame getExame(long id) {
        for (Exame exame : exames) {
            if (exame.getId() == id) {
                return exame;
            }
        }
        return null;
    }
    public void atualizarExame(Exame exame) {
        exames.set(exames.indexOf(getExame(exame.getId())), exame);
    }

    public Paciente addPaciente(Paciente paciente) {
        paciente.setId(++contadorPacientes);
        pacientes.add(paciente);
        return paciente;
    }
    public void removePaciente(Paciente paciente) { pacientes.remove(paciente); }
    public ArrayList<Paciente> getAllPacientes() { return pacientes; }
    public Paciente getPaciente(long id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }
    public void atualizarPaciente(Paciente paciente) {
        pacientes.set(pacientes.indexOf(getPaciente(paciente.getId())), paciente);
    }
}

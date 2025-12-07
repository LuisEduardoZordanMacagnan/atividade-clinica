package br.com.ifsc.clinica.clinicateste.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class Especialidade {
    private long id;
    private String especialidade;
    ArrayList<Medico> medicos;

    Especialidade() {
        medicos = new ArrayList<>();
    }

    public void adicionarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void removerMedico(Medico medico) {
        medicos.remove(medico);
    }

    public void notificarObservadores(Exame exame) {
        for(Medico medico : medicos){
            medico.notificarExame(exame);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Especialidade instance = new Especialidade();

        public Builder especialidade(String especialidade) {
            instance.setEspecialidade(especialidade);
            return this;
        }

        public Especialidade build() throws Exception {
            if (instance.getEspecialidade() == null || instance.getEspecialidade().isEmpty()) throw new Exception("Necessário um nome para especialidade");
            instance.setId(0);
            return instance;
        }
    }
}

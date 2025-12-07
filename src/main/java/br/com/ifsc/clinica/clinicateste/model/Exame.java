package br.com.ifsc.clinica.clinicateste.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Exame {
    private long id;
    private Especialidade especialidade;
    private String descricao;
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataExame;
    private String conclusao;
    private ExameState exameState;

    protected Exame() {}

    public interface ExameState {
        boolean escolherMedico(Exame exame, Medico medico);
        boolean agendar(Exame exame, LocalDateTime dataAgendamento);
        boolean concluir(Exame exame, String conclusao);
        boolean cancelar(Exame exame, String conclusao);
        String status();
    }

    public static class Inicio implements ExameState {

        @Override
        public boolean escolherMedico(Exame exame, Medico medico) {
            exame.setExameState(new EsperandoMedico(exame));
            return true;
        }

        @Override
        public boolean agendar(Exame exame, LocalDateTime dataAgendamento) {
            return false;
        }

        @Override
        public boolean concluir(Exame exame, String conclusao) {
            return false;
        }

        @Override
        public boolean cancelar(Exame exame, String conclusao) {
            exame.setConclusao(conclusao);
            exame.setExameState(new Cancelado());
            return true;
        }

        @Override
        public String status() {
            return "INICIADO";
        }
    }

    public static class Cancelado implements ExameState {

        @Override
        public boolean escolherMedico(Exame exame, Medico medico) {
            return false;
        }

        @Override
        public boolean agendar(Exame exame, LocalDateTime dataAgendamento) {
            return false;
        }

        @Override
        public boolean concluir(Exame exame, String conclusao) {
            return false;
        }

        @Override
        public boolean cancelar(Exame exame, String conclusao) {
            return false;
        }

        @Override
        public String status() {
            return "CANCELADO";
        }
    }

    public static class Concluido implements ExameState {

        @Override
        public boolean escolherMedico(Exame exame, Medico medico) {
            return false;
        }

        @Override
        public boolean agendar(Exame exame, LocalDateTime dataAgendamento) {
            return false;
        }

        @Override
        public boolean concluir(Exame exame, String conclusao) {
            return false;
        }

        @Override
        public boolean cancelar(Exame exame, String conclusao) {
            return false;
        }

        @Override
        public String status() {
            return "CONCLUÍDO";
        }
    }

    public static class EsperandoMedico implements ExameState {

        private EsperandoMedico(Exame exame) {
            exame.getEspecialidade().notificarObservadores(exame);
        }

        @Override
        public boolean escolherMedico(Exame exame, Medico medico) {
            exame.setExameState(new AguardandoData());
            exame.setMedico(medico);
            return true;
        }

        @Override
        public boolean agendar(Exame exame, LocalDateTime dataAgendamento) {
            return false;
        }

        @Override
        public boolean concluir(Exame exame, String conclusao) {
            return false;
        }

        @Override
        public boolean cancelar(Exame exame, String conclusao) {
            exame.setConclusao(conclusao);
            exame.setExameState(new Cancelado());
            return true;
        }

        @Override
        public String status() {
            return "AGUARDANDO A SELEÇÃO DE UM MÉDICO";
        }
    }

    public static class AguardandoData implements ExameState {

        @Override
        public boolean escolherMedico(Exame exame, Medico medico) {
            exame.setExameState(new EsperandoMedico(exame));
            return true;
        }

        @Override
        public boolean agendar(Exame exame, LocalDateTime dataAgendamento) {
            exame.setExameState(new Agendado());
            exame.setDataAgendamento(dataAgendamento);
            return true;
        }

        @Override
        public boolean concluir(Exame exame, String conclusao) {
            return false;
        }

        @Override
        public boolean cancelar(Exame exame, String conclusao) {
            exame.setConclusao(conclusao);
            exame.setExameState(new Cancelado());
            return true;
        }

        @Override
        public String status() {
            return "AGUARDANDO A SELEÇÃO DE UMA DATA";
        }
    }

    public static class Agendado implements ExameState {

        @Override
        public boolean escolherMedico(Exame exame, Medico medico) {
            return false;
        }

        @Override
        public boolean agendar(Exame exame, LocalDateTime dataAgendamento) {
            exame.setExameState(new AguardandoData());
            return true;
        }

        @Override
        public boolean concluir(Exame exame, String conclusao) {
            exame.setConclusao(conclusao);
            exame.setExameState(new Concluido());
            return true;
        }

        @Override
        public boolean cancelar(Exame exame, String conclusao) {
            exame.setConclusao(conclusao);
            exame.setExameState(new Cancelado());
            return true;
        }

        @Override
        public String status() {
            return "AGENDADO";
        }
    }

    public static Builder buider(){
        return new Builder();
    }

    public static class Builder {
        private Exame instance = new Exame();

        public Builder descricao(String descricao) {
            instance.setDescricao(descricao);
            return this;
        }

        public Builder paciente(Paciente paciente) {
            instance.setPaciente(paciente);
            return this;
        }

        public Builder especialidade(Especialidade especialidade) {
            instance.setEspecialidade(especialidade);
            return this;
        }

        public Exame build() throws Exception {
            if ( instance.getEspecialidade() == null ) throw new Exception("Necessário escolher uma especialidade");
            if ( instance.getPaciente() == null ) throw new Exception("Necessário escolher um paciente");

            instance.setExameState(new Inicio());
            instance.setId(0);

            return instance;
        }
    }
}

package br.com.ifsc.clinica.clinicateste.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Medico extends Pessoa {
    private long id;
    private String CRM;

    protected Medico() {}

    public void notificarExame(Exame exame) {
        System.out.println("Necessitando de um médico no exame: " + Long.toString(exame.getId()));
    }

    public static Builder builder(){
        return new Builder(new Medico());
    }

    public static class Builder extends Pessoa.Builder<Builder, Medico> {

        protected Builder(Medico medico) {
            super(medico);
        }

        public Builder CRM(String CRM) {
            instance.setCRM(CRM);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Medico build() throws Exception {
            if ( instance.getCRM() == null ||  instance.getCRM().isEmpty() ) throw new Exception("CRM não pode ser vazio");
            instance.setId(0);
            return instance;
        }
    }
}

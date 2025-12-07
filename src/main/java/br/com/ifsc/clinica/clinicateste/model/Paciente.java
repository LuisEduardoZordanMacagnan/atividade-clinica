package br.com.ifsc.clinica.clinicateste.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Paciente extends Pessoa {
    private long id;
    private float peso;
    private float altura;

    protected Paciente() {}

    public static Builder builder() {
        return new Builder(new Paciente());
    }

    public static class Builder extends Pessoa.Builder<Builder, Paciente> {
        protected Builder(Paciente paciente) {
            super(paciente);
        }

        public Builder peso(float peso) {
            instance.setPeso(peso);
            return this;
        }

        public Builder altura(float altura) {
            instance.setAltura(altura);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Paciente build() {
            instance.setId(0);
            return instance;
        }
    }
}
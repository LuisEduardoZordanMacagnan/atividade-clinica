package br.com.ifsc.clinica.clinicateste.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Pessoa {

    public enum Sexo {
        MASCULINO,
        FEMININO
    }

    private long id;
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String cpf;
    private String telefone;

    protected Pessoa() {}

    public static abstract class Builder<T extends Builder<T, K>, K extends Pessoa>{
        protected final K instance;

        protected Builder(K instance) {
            this.instance = instance;
        }

        protected abstract T self();

        public T nome(String nome) {
            instance.setNome(nome);
            return self();
        }

        public T dataNascimento(LocalDate dataNascimento) {
            instance.setDataNascimento(dataNascimento);
            return self();
        }

        public T sexo(Sexo sexo) {
            instance.setSexo(sexo);
            return self();
        }

        public T cpf(String cpf) {
            instance.setCpf(cpf.replaceAll("[^0-9]", ""));
            return self();
        }

        public T telefone(String telefone) {
            instance.setTelefone(telefone.replaceAll("[^0-9]", ""));
            return self();
        }

        public Pessoa build() throws Exception {
            if ( instance.getNome() == null ) throw new Exception("Nome não pode ser vazio");
            if ( instance.getCpf() == null ) throw new Exception("Cpf não pode ser vazio"); else
                if ( instance.getNome().length() != 11 ) throw new Exception("CPF deve conter 11 caracteres");
            if ( instance.getDataNascimento() == null ) throw new Exception("Data nascimento não pode ser vazio");
            instance.setId(0);
            return instance;
        }
    }
}
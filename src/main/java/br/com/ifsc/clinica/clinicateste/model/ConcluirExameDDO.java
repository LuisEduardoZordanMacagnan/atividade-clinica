package br.com.ifsc.clinica.clinicateste.model;

public record ConcluirExameDDO(
        Long exameId,
        String conclusao
) {
    public ConcluirExameDDO {
        if ( exameId == null )
            throw new IllegalArgumentException("exameId não pode ser nulo");

        if (conclusao == null || conclusao.isEmpty() )
            throw new IllegalArgumentException("conclusão não pode ser vazio");
    }
}

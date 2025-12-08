Luís Eduardo Zordan Macagnan - 202320004260
<br>
Guilherme Guimarães - 202320004703
<br>
Gabrieli Bacca - 202320002621 

[QUALIDADE E TESTE DE SOFTWARE]

A operação Taxa Metabólica Basal (TMB) recebe como entrada o peso do paciente (float), a altura em metros (float), 
a data de nascimento utilizada para calcular a idade (int) e o sexo (masculino ou feminino). O cálculo segue a fórmula de Harris-Benedict,
onde para homens: TMB = (10 * peso) + (6.25 * altura * 100) – (5 * idade) + 5, e para mulheres: TMB = (10 * peso) + (6.25 * altura * 100) – (5 * idade) – 161.
A saída da operação é um JSON contendo - "tmb": valor.

A operação Gasto Calórico Diário (GCD) utiliza como entrada a TMB já calculada internamente e um nível de atividade que
deve ser informado pelo usuário de 1 a 5. Cada nível corresponde a um fator: 1–1.2, 2–1.375, 3–1.55, 4–1.725, 5–1.9. O cálculo consiste
em multiplicar a TMB pelo fator correspondente, seguindo a fórmula GCD = TMB * fator. A saída retorna um JSON contendo - "gcd": valor.

A operação IMC recebe como entrada o peso (float) e a altura (float). O cálculo segue a fórmula IMC = peso / (altura * altura).
O resultado é arredondado para duas casas decimais antes de ser retornado. A operação devolve um JSON contendo - "imc": valor.

A operação Meta Calórica utiliza como entrada o valor do GCD (calculado internamente), o nível de atividade (int)
e o parâmetro booleano indicando o objetivo do paciente, onde true significa emagrecer e false significa ganhar peso.
O cálculo aplica uma regra simples: se for emagrecer, meta = GCD – 500; se for ganhar peso, meta = GCD + 300.
O valor final é arredondado e retornado em um JSON contendo - "metaCal": valor.
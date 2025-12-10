Luís Eduardo Zordan Macagnan - 202320004260
<br>
Guilherme Guimarães - 202320004703
<br>
Gabrieli Bacca - 202320002621 

[QUALIDADE E TESTE DE SOFTWARE]

Link vídeo YT: https://www.youtube.com/watch?v=QahYkH8yPT8

Informações do Projeto
----------------------------------------------------------
● Linguagem utilizada:
  - Java 

● Frameworks e bibliotecas utilizadas:
  - Lombok
  - Spring Boot (Web)
  - Java Time API (LocalDate, Period)
  - Map para guardar chave → valor
  - A estrutura própria de dados é baseada em arraylists
----------------------------------------------------------

----------------------------------------------------------
Operação – IMC (Índice de Massa Corporal)
----------------------------------------------------------
● Entrada esperada:
  - peso 
  - altura 

● Cálculo:
  imc = peso / (altura * altura)

● Saída:
  - valor do IMC
----------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------
Operação – TMB (Taxa Metabólica Basal)
----------------------------------------------------------
● Entrada esperada:
  - peso 
  - altura 
  - idade 
  - sexo (MASCULINO / FEMININO)

● Conversão interna:
  alturaCm = altura * 100

● Cálculo:
  Se sexo = MASCULINO:
      tmb = (10 * peso) + (6.25 * alturaCm) - (5 * idade) + 5

  Se sexo = FEMININO:
      tmb = (10 * peso) + (6.25 * alturaCm) - (5 * idade) - 161

● Saída:
  - valor numérico da TMB
----------------------------------------------------------------------------------------------------------------


----------------------------------------------------------
Operação – GCD (Gasto Calórico Diário)
----------------------------------------------------------
● Entrada esperada:
  - tmb 
  - nível de atividade física (de 1 a 5)

● Tabela de fatores:
  1 → 1.2
  2 → 1.375
  3 → 1.55
  4 → 1.725
  5 → 1.9

● Cálculo:
  gcd = tmb * fator

● Saída:
  - valor numérico do GCD
----------------------------------------------------------------------------------------------------------------

----------------------------------------------------------
Operação – Meta Calórica (emagrecer/ganhar peso)
----------------------------------------------------------
● Entrada esperada:
  - gcd 
  - nível de atividade física 
  - isEmagrecer

● Cálculo:
  Se isEmagrecer = true:
      metaCal = gcd - 500

  Caso contrário:
      metaCal = gcd + 300

● Arredondamento:
  metaCal = Math.round(metaCal * 100.0) / 100.0

● Saída:
  - meta diária de calorias recomendada
----------------------------------------------------------------------------------------------------------------



# Validação de número de cartão de credito

## Projeto pesssoal para praticar Kotlin, insipirado em: https://github.com/karan/Projects/

## Suporta bandeiras: Master, Visa, Amex, Elo

## Executar testes:
`./gradlew test`

## Regras:
```txt
Verficar se o cartão é valido segue a ordem
1. tamaho e prefixo (bandeira correta)
2. Lunh algoritmo

#1
bandera, prefix,                    tamanho
master   51..55                         16
visa     4                              13/16
amex     34/37                          15
elo      link_ext                       16

*link_ext=https://gist.github.com/gusribeiro/263a165db774f5d78251

#2
Considerando o número do cartao 192932372
da direita para esquerda, multiplique por dois cada digito de forma alternada
começando do segundo mais a direita. Quando for alternado deixe o número como esta.
ex:
2x1 7x2 3x1 2x2 ... 1x2
obs: 1 é neutro na multipliação então o número não vai mudar

Depois, para cada número se ele for maior que 9 deve ser subtraido 9, caso contrario o 
número fica como está.
ex:
do passo anterior: 2 14 3 4 ...
fica: 2 5 3 4 ...

Depois, soma todos os números. Caso a soma seja divisível por 10 então o cartão é válido
```
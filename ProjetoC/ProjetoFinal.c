//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M2LPBA
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
#include<stdio.h>
#include<stdlib.h>

int main(){
    int n[5];
    int i, soma = 0, multiplicacao = 1;

    //Este laço é para armazenar os valores digitados pelo usuário no vetor
    for(i = 0; i < 5; i++){
        printf("Digite um valor para a posicao %d do vetor: ", i+1);
        scanf("%d", &n[i]);
    }
    //este laço efetuará a soma e a multiplicação
    for(i = 0; i < 5; i++){
        soma = soma + n[i];
        multiplicacao = multiplicacao * n[i];
    }
    //O trecho abaixo irá exibir os resultados das operações
    printf("\n\n");
    printf("O resultado da soma eh: %d e eh ", soma);
    if(soma >= 0){
        printf("positivo\n");
    }else{
        printf("negativo\n");
    }
    printf("\n");
    printf("O resultado da multiplicacao eh: %d e eh ", multiplicacao);
    if(multiplicacao >= 0){
        printf("positivo\n");
    }else{
        printf("negativo\n");
    }

    return 0;
}

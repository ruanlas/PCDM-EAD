//******************************************************
//Instituto Federal de S�o Paulo - Campus Sert�ozinho
//Disciplina......: M2LPBA
//Programa��o de Computadores e Dispositivos M�veis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
#include<stdio.h>
#include<stdlib.h>

int main(){
    int n[5];
    int i, soma = 0, multiplicacao = 1;

    //Este la�o � para armazenar os valores digitados pelo usu�rio no vetor
    for(i = 0; i < 5; i++){
        printf("Digite um valor para a posicao %d do vetor: ", i+1);
        scanf("%d", &n[i]);
    }
    //este la�o efetuar� a soma e a multiplica��o
    for(i = 0; i < 5; i++){
        soma = soma + n[i];
        multiplicacao = multiplicacao * n[i];
    }
    //O trecho abaixo ir� exibir os resultados das opera��es
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

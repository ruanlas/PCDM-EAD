//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package Models;

/**
 * Created by Ruan on 18/06/2017.
 */

//Classe modelo
public class Pessoa {

    private int id;
    private String nome;
    private String cpf;
    private int idade;
    private String telefone;
    private String email;

    public int getId(){return  id;}

    public void setId(int id){ this.id = id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String pessoaDetails(){
        return "ID: "+id+"\n" +
                "Nome: "+nome+"\n"+
                "Idade: "+idade+"\n"+
                "CPF: "+cpf+"\n"+
                "Telefone: "+telefone+"\n"+
                "E-mail: "+email;
    }
    @Override
    public String toString() {
        return "ID: "+id+" | Nome: "+nome+"\n";
    }
}

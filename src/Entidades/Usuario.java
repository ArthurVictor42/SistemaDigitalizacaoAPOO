package Entidades;

public class Usuario {
    private String NomeUsuario;
    private String EmailUsuario;
    private String SenhaUsuario;
    private String cpf;
    private int id;
    private static int contID = 1;

    public Usuario(String nomeUsuario, String emailUsuario, String senhaUsuario, String cpf){
        this.NomeUsuario = nomeUsuario;
        this.EmailUsuario = emailUsuario;
        this.SenhaUsuario = senhaUsuario;
        this.cpf = cpf;
        this.id = contID++;
    }

    public void setNomeUsuario(String novonomeUsuario) {
        this.NomeUsuario = novonomeUsuario;
    }

    public String getNomeUsuario() {
        return this.NomeUsuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.EmailUsuario = emailUsuario;
    }

    public String getEmailUsuario() {
        return this.EmailUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.SenhaUsuario = senhaUsuario;
    }

    public String getSenhaUsuario() {
        return this.SenhaUsuario;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return "nome do Usuario=" + getNomeUsuario() + ", email do Usuario=" + getEmailUsuario()  + ", senha do Usuario=" + getSenhaUsuario() + ", cpf='" + getCpf();
    }
}

package Entidades;

public class Cliente {
    private String NomeCliente; 
    private String CPF; 
    private String tipoEmpresa; 

    public Cliente(String NomeCliente, String CPF, String tipo){
        this.NomeCliente = NomeCliente;
        this.CPF = CPF;
        this.tipoEmpresa = tipo;
    }

    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    public String getNomeCliente() {
        return this.NomeCliente;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getTipoEmpresa() {
        return this.tipoEmpresa;
    }

    @Override
    public String toString() {
        return "nome Cliente='" + getNomeCliente() + ", CPF='" + getCPF();
    }
}

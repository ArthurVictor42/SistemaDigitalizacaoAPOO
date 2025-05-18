package Entidades;

public class ClienteFisico extends Cliente {
    private String CPF;

    public ClienteFisico(int codigo, String nomeCliente, String CPF){
        super(codigo, nomeCliente);
        this.CPF = CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCPF() {
        return this.CPF;
    }

    @Override
    public String toString() {
        return "nome Cliente='" + getNomeCliente() + ", Codigo Cliente='" +  getCodigoCliente();
    }
}

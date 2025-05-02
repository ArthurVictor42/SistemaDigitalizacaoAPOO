package Entidades;

public class ClienteFisico extends Cliente {
    private String CPF;

    public ClienteFisico(String nomeCliente, int CodigoCliente, String CPF){
        super(nomeCliente, CodigoCliente);
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

package Entidades;

public class ClienteJuridico extends Cliente {
    private String CNPJ;

    public ClienteJuridico(int codigo, String nomeCliente, String CNPJ){
        super(codigo, nomeCliente);
        this.CNPJ = CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    @Override
    public String toString() {
        return "nome Cliente='" + getNomeCliente() + ", Codigo Cliente='" +  getCodigoCliente();
    }
}

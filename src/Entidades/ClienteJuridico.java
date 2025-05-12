package Entidades;

public class ClienteJuridico extends Cliente {
    private String CNPJ;

    public ClienteJuridico(String nomeCliente, int CodigoCliente, String CNPJ){
        super(nomeCliente, CodigoCliente);
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

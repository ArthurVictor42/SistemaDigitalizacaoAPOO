package Entidades;

public class Fornecedor {
    private String nomeFornecedor;
    private String CNPJ;
    private String enderecoFornecedor;

    public Fornecedor(String CNPJ, String nomeFornecedor,String enderecoFornecedor){
        this.CNPJ = CNPJ;
        this.nomeFornecedor = nomeFornecedor;
        this.enderecoFornecedor = enderecoFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    public void setEnderecoFornecedor(String enderecoFornecedor) {
        this.enderecoFornecedor = enderecoFornecedor;
    }

    public String getEnderecoFornecedor() {
        return this.enderecoFornecedor;
    }

    public void setCNPJ(String cNPJ) {
        this.CNPJ = cNPJ;
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    @Override
    public String toString() {
        return "Nome do Fornecedor= " + getNomeFornecedor() + ", Endereço do Fornecedor= " + getEnderecoFornecedor() + ", CNPJ= " + getCNPJ() + ""; 
    }    
}

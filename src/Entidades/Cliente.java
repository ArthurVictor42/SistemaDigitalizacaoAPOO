package Entidades;

public abstract class Cliente {
    private String NomeCliente; 
    private int CodigoCliente; 

    public Cliente(int codigo, String NomeCliente){
        this.NomeCliente = NomeCliente;
        this.CodigoCliente = codigo;
    }

    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    public String getNomeCliente() {
        return this.NomeCliente;
    }


    public void setCodigoClientee(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }

    public int getCodigoCliente() {
        return this.CodigoCliente;
    }

    @Override
    public String toString() {
        return "nome Cliente='" + getNomeCliente() + ", Codigo Cliente='" +  getCodigoCliente();
    }
}

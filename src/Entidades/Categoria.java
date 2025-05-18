package Entidades;

public class Categoria {
    private String nomeCategoria;
    private int id;

    public Categoria(int id, String nomeCategoria){
        this.nomeCategoria = nomeCategoria;
        this.id = id;
    }  

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return this.nomeCategoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Nome da categoria= " + getNomeCategoria() + ", codigo da categoria= " + getId();
    }
}

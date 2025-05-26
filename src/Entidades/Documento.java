package Entidades;

public class Documento {
    private String nomeDocumento;
    private String descricaoDocumento;
    private int idDocumento;
    private String caminhoArquivo;

    private byte[] arquivos;

    private Categoria categoria;
    private ClienteFisico clientefisico;
    private ClienteJuridico clientejuridico;
    private Fornecedor fornecedor;


    public Documento(int id, String nomeDocumento, String descricao, ClienteFisico clientefisico,
            ClienteJuridico clientejuridico, Categoria categoria, Fornecedor novofornecedor) {
        this.nomeDocumento = nomeDocumento;
        this.descricaoDocumento = descricao;
        this.idDocumento = id;
        this.categoria = categoria;
        this.clientefisico = clientefisico;
        this.clientejuridico = clientejuridico;
        this.fornecedor = novofornecedor;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public String getNomeDocumento() {
        return this.nomeDocumento;
    }

    public void setDescricaoDocumento(String descricaoDocumento) {
        this.descricaoDocumento = descricaoDocumento;
    }

    public String getDescricaoDocumento() {
        return this.descricaoDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getIdDocumento() {
        return this.idDocumento;
    }

    public void setClientefisico(ClienteFisico clientefisico) {
        this.clientefisico = clientefisico;
    }

    public ClienteFisico getClientefisico() {
        return this.clientefisico;
    }

    public void setClientejuridico(ClienteJuridico clientejuridico) {
        this.clientejuridico = clientejuridico;
    }

    public ClienteJuridico getClientejuridico() {
        return this.clientejuridico;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getCaminhoArquivo() {
        return this.caminhoArquivo;
    }

    public void setArquivos(byte[] arquivos) {
        this.arquivos = arquivos;
    }

    public byte[] getArquivos() {
        return this.arquivos;
    }
    @Override
    public String toString() {
        return "nome do Documento= " + getNomeDocumento() +
                ", descrição do documento= " + getDescricaoDocumento()
                + ", ID do Documento= " + getIdDocumento();
    }
}

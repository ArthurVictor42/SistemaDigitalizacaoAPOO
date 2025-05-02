package View;

import Entidades.*;
import Repositorio.*;
import Servico.*;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static Scanner teclado = new Scanner(System.in);
    
    public static ArrayList<Usuario> ListaUsuario = new ArrayList<>();

    public static UsuarioRepositorio Usurepo = new UsuarioRepositorio(ListaUsuario);
    public static UsuarioServico UsuServi = new UsuarioServico(Usurepo);

    public static ArrayList<Categoria> ListaCategoria = new ArrayList<>();

    public static CategoriaRepositorio CateRepo = new CategoriaRepositorio(ListaCategoria);
    public static CategoriaServico CateServi = new CategoriaServico(CateRepo);

    public static ArrayList<Documento> ListaDocumento = new ArrayList<>();

    public static DocumentoRepositorio DocuRepo = new DocumentoRepositorio(ListaDocumento);
    public static DocumentoServico DocuServi = new DocumentoServico(DocuRepo);

    public static ArrayList<Cliente> ListaEmpresa = new ArrayList<>();

    public static ClienteRepositorio EmpreRepo = new ClienteRepositorio(ListaEmpresa);
    public static ClienteServico EmpreServi = new ClienteServico(EmpreRepo);

    public static ArrayList<Fornecedor> ListaFornecedor = new ArrayList<>();

    public static FornecedorRepositorio FornRepo = new FornecedorRepositorio(ListaFornecedor);
    public static FornecedorServico FornServi = new FornecedorServico(FornRepo);

    public static void main(String[] args) {
        int opcao = 0;
        
        // Verifica se ele é cadastrado ou não
        while(opcao != 2){
            System.out.println("Você já é cadastrado?: ");
            System.out.println("1 - Acessa Menu Principal");
            System.out.print("2 - Fazer cadastro");
            switch (opcao) {
                case 1: // Caso ele seja cadastrado
                    MenuPrincipal();
                    break;
                case 2: // Caso Não seja Cadastrado
                    CadastraCliente();
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }

    }

    public static void MenuPrincipal(){
        int opcao = 0;

        while(opcao != 5){

            switch(opcao){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção Inválida.");
                    break;
            }
        }
    }

    public static void CadastraCliente(){

    }

}
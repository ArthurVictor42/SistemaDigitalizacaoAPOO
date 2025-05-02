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

    public static ArrayList<ClienteFisico> ListaClienteFisico = new ArrayList<>();

    public static ClienteFisicoRepositorio ClienteFisiRepo = new ClienteFisicoRepositorio(ListaClienteFisico);
    public static ClienteFisicoServico ClienteFisiServi = new ClienteFisicoServico(ClienteFisiRepo);

    public static ArrayList<Fornecedor> ListaFornecedor = new ArrayList<>();

    public static FornecedorRepositorio FornRepo = new FornecedorRepositorio(ListaFornecedor);
    public static FornecedorServico FornServi = new FornecedorServico(FornRepo);

    public static ArrayList<ClienteJuridico> ListaClienteJuridico = new ArrayList<>();

    public static ClienteJuridicoRepositorio ClienteJuriRepo = new ClienteJuridicoRepositorio(ListaClienteJuridico);
    public static ClienteJuridicoServico ClienteJuriServi = new ClienteJuridicoServico(ClienteJuriRepo);

    public static void main(String[] args) {
        int opcao = 0;

        // Verifica se o usuário é cadastrado
        while (opcao != 3) {
            System.out.println("\n Você já é cadastrado?");
            System.out.println("1 - Acessar Menu Principal");
            System.out.println("2 - Fazer Cadastro de Usuário");
            System.out.println("3 - Caso deseja encerra o Sistema");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    MenuPrincipal();
                    break;
                case 2:
                    cadastraUsuario();
                    break;
                case 3:
                    System.out.println("Sistema Encerrado com sucesso! ");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }

        }

        teclado.close();

    }

    public static void MenuPrincipal() {
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1 - Menu Cliente Físico");
            System.out.println("2 - Menu Cliente Jurídico");
            System.out.println("3 - Menu Categoria");
            System.out.println("4 - Menu Documento");
            System.out.println("5 - Menu Fornecedor");
            System.out.println("6 - Menu Usuário");
            System.out.println("7 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    menuClienteFisico();
                    break;
                case 2:
                    menuClienteJuridico();
                    break;
                case 3:
                    menuCategoria();
                    break;
                case 4:
                    menuDocumento();
                    break;
                case 5:
                    menuFornecedor();
                    break;
                case 6:
                    menuUsuario();
                    break;
                case 7:
                    System.out.println("Sistema Encerrado com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        teclado.close();
    }


    public static void menuClienteFisico(){
        int opcao = 0;

        System.out.println("\n==== Menu Cliente Fisico ====");
        System.out.println("1 - Cadastra Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Remover Cliente");
        System.out.println("4 - Altera Cliente");
        System.out.println("5 - Sair");
        System.out.print("Sua escolha: ");
        opcao = teclado.nextInt();
        teclado.nextLine();

        while(opcao != 5){
            switch (opcao) {
                case 1:
                    cadastraClienteFisico();
                    break;
                case 2:
                    listarClienteFisico();
                    break;
                case 3:
                    removeClienteFisico();
                    break;
                case 4:
                    alteraClienteFisico();
                    break;
                case 5:
                    System.out.println("Saindo do Menu");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        }

        teclado.close();
    }

    // CRUD completo de Cliente Fisico!

    public static void cadastraClienteFisico(){
        System.out.println("\n==== Cadastro de Cliente Físico ====");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF: ");
        String cpf = teclado.nextLine();
        System.out.print("Código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        ClienteFisico novoCliente = new ClienteFisico(nome, codigo, cpf);
        ClienteFisiServi.cadastra(novoCliente);
    
        System.out.println("Cliente cadastrado com sucesso!");
    }
    
    public static void listarClienteFisico(){
        System.out.println("\n==== Lista de Clientes Físicos ====");
        List<ClienteFisico> clientes = ClienteFisiServi.listar();
    
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            for (ClienteFisico c : clientes) {
                System.out.println("Código: " + c.getCodigoCliente());
                System.out.println("Nome: " + c.getNomeCliente());
                System.out.println("CPF: " + c.getCPF());
                System.out.println("---------------------------");
            }
        }
    }
    
    public static void removeClienteFisico(){
        System.out.println("\n==== Remover Cliente Físico ====");
        System.out.print("Informe o código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        boolean removido = ClienteFisiServi.excluir(codigo);
        if (removido) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado com esse código.");
        }
    }
    
    public static void alteraClienteFisico(){
        System.out.println("\n==== Alterar Cliente Físico ====");
        System.out.print("Informe o código do cliente a ser alterado: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        ClienteFisico cliente = ClienteFisiServi.buscarPorCodigo(codigo);
    
        if (cliente != null) {
            System.out.print("Novo nome (" + cliente.getNomeCliente() + "): ");
            String novoNome = teclado.nextLine();
            System.out.print("Novo CPF (" + cliente.getCPF() + "): ");
            String novoCpf = teclado.nextLine();
    
            cliente.setNomeCliente(novoNome);
            cliente.setCPF(novoCpf);
    
            ClienteFisiServi.alteraCliente(codigo, novoNome, novoCpf);
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void menuClienteJuridico(){
        int opcao = 0;
        System.out.println("\n==== Menu Cliente Juridico ====");
        System.out.println("1 - Cadastra Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Remover Cliente");
        System.out.println("4 - Altera Cliente");
        System.out.println("5 - Sair");
        System.out.print("Sua escolha: ");
        opcao = teclado.nextInt();
        teclado.nextLine();

        while(opcao != 5){
            switch (opcao) {
                case 1:
                    cadastraClienteJuridico();
                    break;
                case 2:
                    listarClienteJuridico();
                    break;
                case 3:
                    removeClienteJuridico();
                    break;
                case 4:
                    alteraClienteJuridico();
                    break;
                case 5:
                    System.out.println("Saindo do menu de Cliente!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        }

        teclado.close();
    }

    // CRUD de Cliente Juridico

    public static void cadastraClienteJuridico(){
        System.out.println("\n==== Cadastro de Cliente Físico ====");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("CPF: ");
        String cnpj = teclado.nextLine();
        System.out.print("Código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        ClienteJuridico novoCliente = new ClienteJuridico(nome, codigo, cnpj);
        ClienteJuriServi.cadastra(novoCliente);
    
        System.out.println("Cliente cadastrado com sucesso!");
    }
    
    public static void listarClienteJuridico(){
        System.out.println("\n==== Lista de Clientes Físicos ====");
        List<ClienteJuridico> clientes = ClienteJuriServi.listar();
    
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            for (ClienteJuridico c : clientes) {
                System.out.println("Código: " + c.getCodigoCliente());
                System.out.println("Nome: " + c.getNomeCliente());
                System.out.println("CPF: " + c.getCPF());
                System.out.println("---------------------------");
            }
        }
    }
    
    public static void removeClienteJuridico(){
        System.out.println("\n==== Remover Cliente Físico ====");
        System.out.print("Informe o código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        boolean removido = ClienteJuriServi.excluir(codigo);
        if (removido) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado com esse código.");
        }
    }
    
    public static void alteraClienteJuridico(){
        System.out.println("\n==== Alterar Cliente Físico ====");
        System.out.print("Informe o código do cliente a ser alterado: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        ClienteJuridico cliente = ClienteJuriServi.buscarPorCodigo(codigo);
    
        if (cliente != null) {
            System.out.print("Novo nome (" + cliente.getNomeCliente() + "): ");
            String novoNome = teclado.nextLine();
            System.out.print("Novo CPF (" + cliente.getCPF() + "): ");
            String novoCNPJ = teclado.nextLine();
    
            cliente.setNomeCliente(novoNome);
            cliente.setCNPJ(novoCNPJ);
    
            ClienteJuriServi.alteraCliente(codigo, novoNome, novoCNPJ);
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void menuCategoria(){
        int opcao = 0;
        System.out.println("\n==== Menu Categoria ====");
        System.out.println("1 - Cria Categoria");
        System.out.println("2 - Listar Categoria");
        System.out.println("3 - Remover Categoria");
        System.out.println("4 - Altera Categoria");
        System.out.println("5 - Sair");
        System.out.print("Sua escolha: ");
        opcao = teclado.nextInt();
        teclado.nextLine();

        while(opcao != 5){
            switch (opcao) {
                case 1:
                    cadastraCategoria();
                    break;
                case 2:
                    listarCategoria();
                    break;
                case 3:
                    removerCategoria();
                    break;
                case 4:
                    alteraCategoria();
                    break;
                case 5:
                    System.out.println("Saindo do menu de categorias! ");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        }

        teclado.close();
    }

    public static void cadastraCategoria() {
        System.out.println("\n==== Cadastrar Categoria ====");
    
        System.out.print("Nome da Categoria: ");
        String nome = teclado.nextLine();
    
        Categoria categoria = new Categoria(nome);
        CateServi.cadastra(categoria);
        System.out.println("Categoria cadastrada com sucesso!");
    }
    
    public static void listarCategoria() {
        System.out.println("\n==== Lista de Categorias ====");
        List<Categoria> categorias = CateServi.listar();
    
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada.");
        } else {
            for (Categoria categoria : categorias) {
                System.out.println("Código: " + categoria.getId());
                System.out.println("Nome: " + categoria.getNomeCategoria());
                System.out.println("---------------------------");
            }
        }
    }
    
    
    public static void removerCategoria() {
        System.out.println("\n==== Remover Categoria ====");
        System.out.print("Informe o ID da categoria a ser removida: ");
        int id = teclado.nextInt();
        teclado.nextLine();
    
        if (CateServi.excluir(id)) {
            System.out.println("Categoria removida com sucesso.");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
    
    public static void alteraCategoria() {
        System.out.println("\n==== Alterar Categoria ====");
        System.out.print("Informe o ID da categoria a ser alterada: ");
        int id = teclado.nextInt();
        teclado.nextLine();
    
        Categoria categoria = CateServi.buscarPorId(id);
        if (categoria != null) {
            System.out.print("Novo nome (" + categoria.getNomeCategoria() + "): ");
            String novoNome = teclado.nextLine();
    
    
            categoria.setNomeCategoria(novoNome);
            CateServi.alteraCategoria(id, novoNome);
            System.out.println("Categoria atualizada com sucesso.");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    public static void menuDocumento(){
        int opcao = 0;
        System.out.println("\n==== Menu Documento ====");
        System.out.println("1 - Salvar Documento");
        System.out.println("2 - Listar Documento");
        System.out.println("3 - Remover Documento");
        System.out.println("4 - Altera Documento");
        System.out.println("5 - Sair");
        System.out.print("Sua escolha: ");
        opcao = teclado.nextInt();
        teclado.nextLine();

        while(opcao != 5){
            switch (opcao) {
                case 1:
                    cadastraDocumento();
                    break;
                case 2:
                    listarDocumento();
                    break;
                case 3:
                    removeDocumento();
                    break;
                case 4:
                    alteraDocumento();
                    break;
                case 5:
                    System.out.println("Saindo do menu de documentos!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        }

        teclado.close();
    }

    public static void cadastraDocumento() {
        System.out.println("\n==== Cadastrar Documento ====");
        
        System.out.print("Informe o Nome do Documento: ");
        String nome = teclado.nextLine();
        
        System.out.print("Informe a Descrição do Documento: ");
        String descricao = teclado.nextLine();
    
        Documento novoDoc = new Documento(nome, descricao);
        DocuServi.cadastra(novoDoc);
        System.out.println("Documento cadastrado com sucesso!");
    }

    public static void listarDocumento() {
        System.out.println("\n==== Lista de Documentos ====");
        List<Documento> documentos = DocuServi.listar();
    
        if (documentos.isEmpty()) {
            System.out.println("Nenhum documento encontrado.");
        } else {
            for (Documento documento : documentos) {
                System.out.println("ID: " + documento.getIdDocumento());
                System.out.println("Nome: " + documento.getNomeDocumento());
                System.out.println("Descrição: " + documento.getDescricaoDocumento());
                System.out.println("---------------------------");
            }
        }
    }
    
    public static void removeDocumento() {
        System.out.println("\n==== Remover Documento ====");
        System.out.print("Informe o ID do Documento a ser removido: ");
        int id = teclado.nextInt();
        teclado.nextLine();
    
        if (DocuServi.excluir(id)) {
            System.out.println("Documento removido com sucesso.");
        } else {
            System.out.println("Documento não encontrado.");
        }
    }

    public static void alteraDocumento() {
        System.out.println("\n==== Alterar Documento ====");
        System.out.print("Informe o ID do Documento a ser alterado: ");
        int id = teclado.nextInt();
        teclado.nextLine();
    
        Documento documento = DocuServi.buscarPorId(id);
        if (documento != null) {
            System.out.print("Novo nome (" + documento.getNomeDocumento() + "): ");
            String novoNome = teclado.nextLine();
    
            System.out.print("Nova descrição (" + documento.getDescricaoDocumento() + "): ");
            String novaDescricao = teclado.nextLine();
    
            DocuServi.alteraDescricao(id, novaDescricao, novoNome);
            System.out.println("Documento atualizado com sucesso.");
        } else {
            System.out.println("Documento não encontrado.");
        }
    }
    
    public static void menuFornecedor(){
        int opcao = 0;
        System.out.println("\n==== Menu Fornecedor ====");
        System.out.println("1 - Cadastra Fornecedor");
        System.out.println("2 - Listar Fornecedor");
        System.out.println("3 - Remover Fornecedor");
        System.out.println("4 - Altera Fornecedor");
        System.out.println("5 - Sair");
        System.out.print("Sua escolha: ");
        opcao = teclado.nextInt();
        teclado.nextLine();

        while(opcao != 5){
            switch (opcao) {
                case 1:
                    cadastraFornecedor();
                    break;
                case 2:
                    listarFornecedor();
                    break;
                case 3:
                    removerFornecedor();
                    break;
                case 4:
                    alteraFornecedor();
                    break;
                case 5:
                    System.out.println("Saindo do menu fornecedores!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        }

        teclado.close();
    }

    public static void cadastraFornecedor() {
        System.out.println("\n==== Cadastrar Fornecedor ====");
    
        System.out.print("Nome do Fornecedor: ");
        String nome = teclado.nextLine();
    
        System.out.print("CNPJ do Fornecedor: ");
        String cnpj = teclado.nextLine();
    
        System.out.print("Email do Fornecedor: ");
        String email = teclado.nextLine();
    
        Fornecedor fornecedor = new Fornecedor(nome, cnpj, email);
        FornServi.cadastra(fornecedor);
        System.out.println("Fornecedor cadastrado com sucesso!");
    }
    
    public static void listarFornecedor() {
        System.out.println("\n==== Lista de Fornecedores ====");
        List<Fornecedor> fornecedores = FornServi.listar();
    
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado.");
        } else {
            for (Fornecedor fornecedor : fornecedores) {
                System.out.println("Nome: " + fornecedor.getNomeFornecedor());
                System.out.println("CNPJ: " + fornecedor.getCNPJ());
                System.out.println("Endereço: " + fornecedor.getEnderecoFornecedor());
                System.out.println("---------------------------");
            }
        }
    }

    public static void removerFornecedor() {
        System.out.println("\n==== Remover Fornecedor ====");
        System.out.print("Informe o CNPJ do fornecedor a ser removido: ");
        String cnpj = teclado.nextLine();
    
        if (FornServi.excluir(cnpj)) {
            System.out.println("Fornecedor removido com sucesso.");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    public static void alteraFornecedor() {
        System.out.println("\n==== Alterar Fornecedor ====");
        System.out.print("Informe o CNPJ do fornecedor a ser alterado: ");
        String cnpj = teclado.nextLine();
    
        Fornecedor fornecedor = FornServi.buscarPorCNPJ(cnpj);
        if (fornecedor != null) {
            System.out.print("Novo nome (" + fornecedor.getNomeFornecedor() + "): ");
            String novoNome = teclado.nextLine();
    
            System.out.print("Novo Endereço (" + fornecedor.getEnderecoFornecedor() + "): ");
            String novoEndereco = teclado.nextLine();
    
        
            FornServi.alteraFornecedor(cnpj, novoNome, novoEndereco);
            System.out.println("Fornecedor atualizado com sucesso.");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    public static void menuUsuario(){
        int opcao = 0;
        System.out.println("\n==== Menu Usuario ====");
        System.out.println("1 - Cadastra Usuario");
        System.out.println("2 - Listar Usuario");
        System.out.println("3 - Remover Usuario");
        System.out.println("4 - Altera Usuario");
        System.out.println("5 - Sair");
        System.out.print("Sua escolha: ");
        opcao = teclado.nextInt();
        teclado.nextLine();

        while(opcao != 5){
            switch (opcao) {
                case 1:
                    cadastraUsuario();
                    break;
                case 2:
                    listarUsuario();
                    break;
                case 3:
                    removerUsuario();
                    break;
                case 4:
                    alteraUsuario();
                    break;
                case 5:
                    System.out.println("Saindo do menu de usuarios!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        }

        teclado.close();
    }

    public static void cadastraUsuario() {
        System.out.println("\n==== Cadastrar Usuário ====");
        System.out.print(" Insira o seu CPF: ");
        String CPF = teclado.nextLine();
    
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
    
        System.out.print("Email: ");
        String email = teclado.nextLine();
    
        System.out.print("Senha: ");
        String senha = teclado.nextLine();
    
        Usuario usuario = new Usuario(nome, email, senha, CPF);
        UsuServi.cadastra(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }
    
    public static void listarUsuario() {
        System.out.println("\n==== Lista de Usuários ====");
        List<Usuario> usuarios = UsuServi.listar();
    
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nome: " + usuario.getNomeUsuario());
                System.out.println("Email: " + usuario.getEmailUsuario());
                System.out.println("Senha: " + usuario.getSenhaUsuario());
                System.out.println("CPF: " + usuario.getCpf());
                System.out.println("---------------------------");
            }
        }
    }
    
    
    public static void removerUsuario() {
        System.out.println("\n==== Remover Usuário ====");
        System.out.print("Informe o código do usuário a ser removido: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        if (UsuServi.excluir(codigo)) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
    
    public static void alteraUsuario() {
        System.out.println("\n==== Alterar Usuário ====");
        System.out.print("Informe o código do usuário a ser alterado: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();
    
        Usuario usuario = UsuServi.buscarPorId(codigo);
        if (usuario != null) {
            System.out.print("Novo nome (" + usuario.getNomeUsuario() + "): ");
            String novoNome = teclado.nextLine();
    
            System.out.print("Novo email (" + usuario.getEmailUsuario() + "): ");
            String novoEmail = teclado.nextLine();
    
            System.out.print("Nova senha: ");
            String novaSenha = teclado.nextLine();
            
            System.out.print("Novo CPF: ");
            String novoCPF = teclado.nextLine();

            UsuServi.alteraUsuario(codigo, novoNome, novoEmail, novaSenha, novoCPF);
            System.out.println("Usuário atualizado com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}
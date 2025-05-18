package View;

import Entidades.*;
import Repositorio.*;
import Servico.*;

import java.util.Scanner;
import java.util.List;

public class App {
    public static Scanner teclado = new Scanner(System.in);

    public static UsuarioRepositorio Usurepo = new UsuarioRepositorio();
    public static UsuarioServico UsuServi = new UsuarioServico(Usurepo);

    public static CategoriaRepositorio CateRepo = new CategoriaRepositorio();
    public static CategoriaServico CateServi = new CategoriaServico(CateRepo);

    public static DocumentoRepositorio DocuRepo = new DocumentoRepositorio();
    public static DocumentoServico DocuServi = new DocumentoServico(DocuRepo);

    public static ClienteFisicoRepositorio ClienteFisiRepo = new ClienteFisicoRepositorio();
    public static ClienteFisicoServico ClienteFisiServi = new ClienteFisicoServico(ClienteFisiRepo);

    public static FornecedorRepositorio FornRepo = new FornecedorRepositorio();
    public static FornecedorServico FornServi = new FornecedorServico(FornRepo);

    public static ClienteJuridicoRepositorio ClienteJuriRepo = new ClienteJuridicoRepositorio();
    public static ClienteJuridicoServico ClienteJuriServi = new ClienteJuridicoServico(ClienteJuriRepo);

    public static void main(String[] args) {
        int opcao = 0;

        // Verifica se o usuário é cadastrado
        while (opcao != 3) {
            System.out.println("\n Você já é cadastrado?");
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Fazer Cadastro de Usuário");
            System.out.println("3 - Caso deseja encerra o Sistema");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    login();
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

    private static void login() {
        System.out.println("\n======= Tela de Login! =======");

        System.out.print("Email: ");
        String email = teclado.nextLine();

        System.out.print("Senha: ");
        String senha = teclado.nextLine();

        boolean verificar = verificacaologin(email, senha);

        if (verificar) {
            System.out.println("Login feito com sucesso");
            MenuPrincipal();
        } else {
            System.out.println("Login não executado, senha ou email errados");
        }

    }

    private static boolean verificacaologin(String email, String senha) {
        List<Usuario> usuarios = UsuServi.listar();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmailUsuario().equals(email) || usuario.getSenhaUsuario().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public static void MenuPrincipal() {
        int opcao = 0;

        do {
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
        } while (opcao != 7);

    }

    public static void menuClienteFisico() {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Cliente Fisico ====");
            System.out.println("1 - Cadastra Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Remover Cliente");
            System.out.println("4 - Altera Cliente");
            System.out.println("5 - Buscar Cliente");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

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
                    buscarClienteFisico();
                    break;
                case 6:
                    System.out.println("Saindo do Menu");
                    break;
                default:
                    System.out.println("Opção Invalida!");

            }
        } while (opcao != 6);
    }

    // CRUD completo de Cliente Fisico!

    private static void cadastraClienteFisico() {
        System.out.println("\n==== Cadastro de Cliente Físico ====");

        System.out.print("Código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("CPF: ");
        String cpf = teclado.nextLine();

        if (nome.trim().isEmpty() || cpf.trim().isEmpty()) {
            System.out.println(
                    "Não foi possível cadastrar esse cliente, por favor, preencha todas as informações com campos validos");
            return;
        }

        try {
            ClienteFisico novoCliente = new ClienteFisico(codigo, nome, cpf);
            ClienteFisiServi.cadastra(novoCliente);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Cliente ");
        }
    }

    private static void listarClienteFisico() {
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

    private static void removeClienteFisico() {
        System.out.println("\n==== Remover Cliente Físico ====");
        System.out.print("Informe o código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Você deseja remove esse cliente? (S/N)");
        String resposta = teclado.nextLine();

        if (resposta.equals("S")) {
            boolean removido = ClienteFisiServi.excluir(codigo);
            System.out.println(removido ? "Cliente Fisico removido com sucesso!" : "Cliente Fisico não encontrado.");
        } else {
            System.out.println("Confirmação recusada");
        }

    }

    private static void alteraClienteFisico() {
        System.out.println("\n==== Alterar Cliente Físico ====");
        System.out.print("Informe o código do cliente a ser alterado: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        ClienteFisico cliente = ClienteFisiServi.buscarPorCodigo(codigo);

        if (cliente != null) {

            System.out.print("Novo nome: ");
            String novoNome = teclado.nextLine();
            System.out.print("Novo CPF: ");
            String novoCpf = teclado.nextLine();

            ClienteFisico clientes = new ClienteFisico(codigo, novoNome, novoCpf);
            ClienteFisiServi.alteraCliente(clientes);
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void buscarClienteFisico() {
        System.out.println("======= Buscar Cliente Fisico =======");
        System.out.print("Digite o codigo do cliente: ");
        int codigo = teclado.nextInt();

        ClienteFisico clientebuscar = ClienteFisiServi.buscarPorCodigo(codigo);

        if (clientebuscar != null) {
            System.out.println("Nome: " + clientebuscar.getNomeCliente());
            System.out.println("CPF: " + clientebuscar.getCPF());
            System.out.println("Codigo: " + clientebuscar.getCodigoCliente());
        } else {
            System.out.println("Cliente não encontrado!");
        }

    }

    public static void menuClienteJuridico() {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Cliente Juridico ====");
            System.out.println("1 - Cadastra Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Remover Cliente");
            System.out.println("4 - Altera Cliente");
            System.out.println("5 - Buscar Cliente");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

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
                    buscarClienteJuridico();
                    break;
                case 6:
                    System.out.println("Saindo do menu de Cliente!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (opcao != 6);
    }

    // CRUD de Cliente Juridico

    private static void cadastraClienteJuridico() {
        System.out.println("\n==== Cadastro de Cliente Físico ====");

        System.out.print("Código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = teclado.nextLine();

        if (nome.trim().isEmpty() || cnpj.trim().isEmpty()) {
            System.out.println(
                    "Não foi possível cadastrar esse cliente, por favor, preencha todas as informações com campos validos");
            return;
        }

        try {
            ClienteJuridico novoCliente = new ClienteJuridico(codigo, nome, cnpj);
            ClienteJuriServi.cadastra(novoCliente);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Cliente ");
        }

    }

    private static void listarClienteJuridico() {
        System.out.println("\n==== Lista de Clientes Físicos ====");
        List<ClienteJuridico> clientes = ClienteJuriServi.listar();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            for (ClienteJuridico c : clientes) {
                System.out.println("Código: " + c.getCodigoCliente());
                System.out.println("Nome: " + c.getNomeCliente());
                System.out.println("CPF: " + c.getCNPJ());
                System.out.println("---------------------------");
            }
        }
    }

    private static void removeClienteJuridico() {
        System.out.println("\n==== Remover Cliente Físico ====");
        System.out.print("Informe o código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Você deseja remove esse cliente? (S/N)");
        String escolha = teclado.nextLine();

        if (escolha.equals("S")) {
            boolean removido = ClienteJuriServi.excluir(codigo);
            System.out
                    .println(removido ? "Cliente Juridico removido com sucesso!" : "Cliente Juridico não encontrado.");
        } else {
            System.out.println("Confirmação recusada");
        }

    }

    private static void alteraClienteJuridico() {
        System.out.println("\n==== Alterar Cliente Físico ====");
        System.out.print("Informe o código do cliente a ser alterado: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        ClienteJuridico cliente = ClienteJuriServi.buscarPorCodigo(codigo);

        if (cliente != null) {
            System.out.print("Novo nome: ");
            String novoNome = teclado.nextLine();
            System.out.print("Novo CPF: ");
            String novoCNPJ = teclado.nextLine();

            ClienteJuridico clientes = new ClienteJuridico(codigo, novoNome, novoCNPJ);
            ClienteJuriServi.alteraCliente(clientes);
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void buscarClienteJuridico() {
        System.out.println("======= Buscar Cliente Juridico =======");
        System.out.print("Digite o codigo do cliente: ");
        int codigo = teclado.nextInt();

        ClienteJuridico clientebuscar = ClienteJuriServi.buscarPorCodigo(codigo);

        if (clientebuscar != null) {
            System.out.println("Nome: " + clientebuscar.getNomeCliente());
            System.out.println("CPF: " + clientebuscar.getCNPJ());
            System.out.println("Codigo: " + clientebuscar.getCodigoCliente());
        } else {
            System.out.println("Cliente não encontrado!");
        }

    }

    public static void menuCategoria() {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Categoria ====");
            System.out.println("1 - Cria Categoria");
            System.out.println("2 - Listar Categoria");
            System.out.println("3 - Remover Categoria");
            System.out.println("4 - Altera Categoria");
            System.out.println("5 - Buscar Categoria");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

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
                    buscarCategoria();
                    break;
                case 6:
                    System.out.println("Saindo do menu de categorias! ");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (opcao != 6);
    }

    private static void cadastraCategoria() {
        System.out.println("\n==== Cadastrar Categoria ====");

        System.out.print("Codigo da categoria: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nome da Categoria: ");
        String nome = teclado.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Por favor, preencha corretamente a informação");
            return;
        }

        try {
            Categoria categoria = new Categoria(codigo, nome);
            CateServi.cadastra(categoria);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Categoria");
        }
    }

    private static void listarCategoria() {
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

    private static void removerCategoria() {
        System.out.println("\n==== Remover Categoria ====");
        System.out.print("Informe o ID da categoria a ser removida: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Você Deseja remover essa categoria? (S/N)");
        String escolha = teclado.nextLine();

        if (escolha.equals("S")) {
            boolean sucesso = CateServi.excluir(id);
            System.out.println(sucesso ? "Categoria removida com sucesso!" : "Categoria não encontrada.");
        } else {
            System.out.println("Confirmação Recusada");
        }

    }

    private static void alteraCategoria() {
        System.out.println("\n==== Alterar Categoria ====");
        System.out.print("Informe o ID da categoria a ser alterada: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Categoria categoria = CateServi.buscarPorId(id);
        if (categoria != null) {
            System.out.print("Novo nome: ");
            String novoNome = teclado.nextLine();

            if (novoNome.trim().isEmpty()) {
                System.out.println("Por favor, preencha o nome corretamente");
                return;
            }

            Categoria categorias = new Categoria(id, novoNome);
            CateServi.alteraCategoria(categorias);
            System.out.println("Categoria atualizada com sucesso.");

        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    private static void buscarCategoria() {
        System.out.println("======= Buscar Categoria =======");
        System.out.print("Digite o codigo da categoria: ");
        int codigo = teclado.nextInt();

        Categoria categoriabuscar = CateServi.buscarPorId(codigo);

        if (categoriabuscar != null) {
            System.out.println("Nome da categoria: " + categoriabuscar.getNomeCategoria());
            System.out.println("ID: " + categoriabuscar.getId());
        } else {
            System.out.println("Categoria não encontrado!");
        }

    }

    public static void menuDocumento() {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Documento ====");
            System.out.println("1 - Salvar Documento");
            System.out.println("2 - Listar Documento");
            System.out.println("3 - Remover Documento");
            System.out.println("4 - Altera Documento");
            System.out.println("5 - Buscar Documento");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

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
                    buscarDocumento();
                    break;
                case 6:
                    System.out.println("Saindo do menu de documentos!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (opcao != 6);
    }

    private static void cadastraDocumento() {
        System.out.println("\n==== Cadastrar Documento ====");

        System.out.print("Informe o codigo do Documento: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Informe o Nome do Documento: ");
        String nome = teclado.nextLine();

        System.out.print("Informe a Descrição do Documento: ");
        String descricao = teclado.nextLine();

        if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
            System.out.println("Por favor, Preencha todas as informações corretamente");
            return;
        }

        Documento novoDoc = new Documento(codigo, nome, descricao);
        DocuServi.cadastra(novoDoc);
        System.out.println("Documento cadastrado com sucesso!");
    }

    private static void listarDocumento() {
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

    private static void removeDocumento() {
        System.out.println("\n==== Remover Documento ====");
        System.out.print("Informe o ID do Documento a ser removido: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Você deseja remove esse documento? (S/N)");
        String escolha = teclado.nextLine();

        if (escolha.equals("S")) {
            if (DocuServi.excluir(id)) {
                System.out.println("Documento removido com sucesso.");
            } else {
                System.out.println("Documento não encontrado.");
            }
        } else {
            System.out.println("Confirmação Recusada");
        }

    }

    private static void alteraDocumento() {
        System.out.println("\n==== Alterar Documento ====");
        System.out.print("Informe o ID do Documento a ser alterado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Documento documento = DocuServi.buscarPorId(id);
        if (documento != null) {
            System.out.print("Novo nome: ");
            String novoNome = teclado.nextLine();

            System.out.print("Nova descrição: ");
            String novaDescricao = teclado.nextLine();

            if (novoNome.trim().isEmpty() || novaDescricao.trim().isEmpty()) {
                System.out.println("Por favor, Digite todas as informações corretamente!");
                return;
            }

            Documento documentos = new Documento(id, novoNome, novaDescricao);
            DocuServi.alteraDescricao(documentos);
            System.out.println("Documento atualizado com sucesso.");
        } else {
            System.out.println("Documento não encontrado.");
        }
    }

    private static void buscarDocumento() {
        System.out.println("======= Buscar Documento =======");
        System.out.print("Digite o codigo do documento: ");
        int codigo = teclado.nextInt();

        Documento documentobuscar = DocuServi.buscarPorId(codigo);

        if (documentobuscar != null) {
            System.out.println("Nome do documento: " + documentobuscar.getNomeDocumento());
            System.out.println("Descrição do documento: " + documentobuscar.getDescricaoDocumento());
            System.out.println("Codigo do documento: " + documentobuscar.getIdDocumento());
        } else {
            System.out.println("Documento não encontrado!");
        }

    }

    public static void menuFornecedor() {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Fornecedor ====");
            System.out.println("1 - Cadastra Fornecedor");
            System.out.println("2 - Listar Fornecedor");
            System.out.println("3 - Remover Fornecedor");
            System.out.println("4 - Altera Fornecedor");
            System.out.println("5 - Buscar Fornecedor");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    listarFornecedor();
                    break;
                case 3:
                    removerFornecedor();
                    break;
                case 4:
                    alterarFornecedor();
                    break;
                case 5:
                    buscarFornecedor();
                    break;
                case 6:
                    System.out.println("Saindo do menu fornecedores!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (opcao != 6);
    }

    private static void cadastrarFornecedor() {
        System.out.println("\n==== Cadastrar Fornecedor ====");

        teclado.nextLine(); // Quebra de linha

        System.out.print("Nome do Fornecedor: ");
        String nome = teclado.nextLine();

        System.out.print("CNPJ do Fornecedor: ");
        String cnpj = teclado.nextLine();

        System.out.print("Endereço do Fornecedor: ");
        String endereco = teclado.nextLine();

        if (nome.trim().isEmpty() || cnpj.trim().isEmpty() || endereco.trim().isEmpty()) {
            System.out.println("Por favor, digite todas as informações corretamente.");
            return;
        }

        try {
            Fornecedor fornecedor = new Fornecedor(nome, cnpj, endereco);
            FornServi.cadastra(fornecedor);
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao tentar cadastrar fornecedor.");
        }
    }

    private static void listarFornecedor() {
        System.out.println("\n==== Lista de Fornecedores ====");
        List<Fornecedor> fornecedores = FornServi.listar();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado.");
        } else {
            for (Fornecedor f : fornecedores) {
                System.out.println("Nome: " + f.getNomeFornecedor());
                System.out.println("CNPJ: " + f.getCNPJ());
                System.out.println("Endereço: " + f.getEnderecoFornecedor());
                System.out.println("---------------------------");
            }
        }
    }

    private static void removerFornecedor() {
        System.out.println("\n==== Remover Fornecedor ====");
        System.out.print("Informe o CNPJ do fornecedor a ser removido: ");
        String cnpj = teclado.nextLine();

        System.out.print("Você deseja remover esse fornecedor? (S/N): ");
        String escolha = teclado.nextLine();

        if (escolha.equalsIgnoreCase("S")) {
            boolean sucesso = FornServi.excluir(cnpj);
            System.out.println(sucesso ? "Fornecedor removido com sucesso!" : "Fornecedor não encontrado.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private static void alterarFornecedor() {
        System.out.println("\n==== Alterar Fornecedor ====");
        System.out.print("Informe o CNPJ do fornecedor a ser alterado: ");
        String cnpj = teclado.nextLine();

        Fornecedor fornecedorExistente = FornServi.buscarPorCNPJ(cnpj);
        if (fornecedorExistente != null) {
            System.out.print("Novo nome: ");
            String novoNome = teclado.nextLine();

            System.out.print("Novo endereço: ");
            String novoEndereco = teclado.nextLine();

            if (novoNome.trim().isEmpty() || novoEndereco.trim().isEmpty()) {
                System.out.println("Por favor, preencha todas as informações corretamente.");
                return;
            }

            Fornecedor atualizado = new Fornecedor(novoNome, cnpj, novoEndereco);
            FornServi.alteraFornecedor(atualizado);
            System.out.println("Fornecedor atualizado com sucesso.");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    private static void buscarFornecedor() {
        System.out.println("\n==== Buscar Fornecedor ====");
        System.out.print("Digite o CNPJ do fornecedor: ");
        String cnpj = teclado.nextLine();

        Fornecedor f = FornServi.buscarPorCNPJ(cnpj);

        if (f != null) {
            System.out.println("Nome do Fornecedor: " + f.getNomeFornecedor());
            System.out.println("CNPJ: " + f.getCNPJ());
            System.out.println("Endereço do Fornecedor: " + f.getEnderecoFornecedor());
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    public static void menuUsuario() {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Usuario ====");
            System.out.println("1 - Cadastra Usuario");
            System.out.println("2 - Listar Usuario");
            System.out.println("3 - Remover Usuario");
            System.out.println("4 - Altera Usuario");
            System.out.println("5 - Buscar Usuario");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

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
                    buscarUsuario();
                    break;
                case 6:
                    System.out.println("Saindo do menu de usuarios!");
                    break;
                default:
                    System.out.println("Opção Invalida!");
                    break;
            }
        } while (opcao != 6);
    }

    private static void cadastraUsuario() {
        System.out.println("==== Cadastrar Usuário ====");

        System.out.print("Insira o id do usuario: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Insira o seu CPF: ");
        String CPF = teclado.nextLine();

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("Email: ");
        String email = teclado.nextLine();

        System.out.print("Senha: ");
        String senha = teclado.nextLine();

        if (CPF.trim().isEmpty() || nome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty()) {
            System.out.println("Por favor, preencha todas as informações corretamente");
            return;
        }

        try {
            Usuario usuario = new Usuario(id, nome, email, senha, CPF);
            UsuServi.cadastra(usuario);
        } catch (Exception e) {
            System.out.println("Erro ao tenta cadastrar Usuario");
        }

    }

    private static void listarUsuario() {
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

    private static void removerUsuario() {
        System.out.println("\n==== Remover Usuário ====");
        System.out.print("Informe o código do usuário a ser removido: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Deseja realmente remove esse usuario? (S/N)");
        String escolha = teclado.nextLine();
        if (escolha.equals("S")) {
            boolean sucesso = UsuServi.excluir(codigo);
            System.out.println(sucesso ? "Usuario removido com sucesso!" : "Usuario não encontrado.");
        } else {
            System.out.println("Confirmação recusada");
        }

    }

    private static void alteraUsuario() {
        System.out.println("\n==== Alterar Usuário ====");
        System.out.print("Informe o código do usuário a ser alterado: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        Usuario usuario = UsuServi.buscarPorId(codigo);
        if (usuario != null) {
            System.out.print("Novo nome: ");
            String novoNome = teclado.nextLine();

            System.out.print("Novo email: ");
            String novoEmail = teclado.nextLine();

            System.out.print("Nova senha: ");
            String novaSenha = teclado.nextLine();

            System.out.print("Novo CPF: ");
            String novoCPF = teclado.nextLine();

            if (novoNome.trim().isEmpty() || novoEmail.trim().isEmpty() || novaSenha.trim().isEmpty()
                    || novoCPF.trim().isEmpty()) {
                System.out.println("Por favor, preencha todos os campos corretamente");
                return;
            }

            Usuario usuarios = new Usuario(codigo, novoNome, novoEmail, novaSenha, novoCPF);
            UsuServi.alteraUsuario(usuarios);
            System.out.println("Usuário atualizado com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void buscarUsuario() {
        System.out.println("======= Buscar Usuario =======");
        System.out.print("Digite o codigo do usuario: ");
        int codigo = teclado.nextInt();

        Usuario usuariobuscar = UsuServi.buscarPorId(codigo);

        if (usuariobuscar != null) {
            System.out.println("Nome do usuario: " + usuariobuscar.getNomeUsuario());
            System.out.println("CPF: " + usuariobuscar.getCpf());
            System.out.println("Codigo: " + usuariobuscar.getId());
            System.out.println("Email: " + usuariobuscar.getEmailUsuario());
        } else {
            System.out.println("Cliente não encontrado!");
        }

    }
}
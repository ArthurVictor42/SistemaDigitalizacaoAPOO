package View;

import Entidades.*;
import Repositorio.*;
import Servico.*;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class App {
    public static Scanner teclado = new Scanner(System.in);
    private static Usuario usuarioLogado = null;

    public static UsuarioRepositorioJBDC Usurepo = new UsuarioRepositorioJBDC();
    public static UsuarioServico UsuServi = new UsuarioServico(Usurepo);

    public static CategoriaRepositorioJDBC CateRepo = new CategoriaRepositorioJDBC();
    public static CategoriaServico CateServi = new CategoriaServico(CateRepo);

    public static DocumentoRepositorioJDBC DocuRepo = new DocumentoRepositorioJDBC();
    public static DocumentoServico DocuServi = new DocumentoServico(DocuRepo);

    public static ClienteFisicoRepositorioJDBC ClienteFisiRepo = new ClienteFisicoRepositorioJDBC();
    public static ClienteFisicoServico ClienteFisiServi = new ClienteFisicoServico(ClienteFisiRepo);

    public static FornecedorRepositorioJDBC FornRepo = new FornecedorRepositorioJDBC();
    public static FornecedorServico FornServi = new FornecedorServico(FornRepo);

    public static ClienteJuridicoRepositorioJDBC ClienteJuriRepo = new ClienteJuridicoRepositorioJDBC();
    public static ClienteJuridicoServico ClienteJuriServi = new ClienteJuridicoServico(ClienteJuriRepo);

    public static void main(String[] args) {
        int opcao = 0;

        // Verifica se o usuário é cadastrado
        while (opcao != 3) {
            System.out.println("\nVocê já é cadastrado?");
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

        usuarioLogado = verificacaologin(email, senha);

        if (usuarioLogado != null) {
            System.out.println("Login feito com sucesso");
            MenuPrincipal();
        } else {
            System.out.println("Login não executado, senha ou email errados");
        }
    }

    private static Usuario verificacaologin(String email, String senha) {
        List<Usuario> usuarios = UsuServi.listar();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmailUsuario().equals(email) && usuario.getSenhaUsuario().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public static void MenuPrincipal() {
        int opcao = 0;
        String tipo = usuarioLogado.getTipoUsuario();

        do {
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1 - Menu Cliente Físico");
            System.out.println("2 - Menu Cliente Jurídico");
            System.out.println("3 - Menu Categoria");
            System.out.println("4 - Menu Documento");
            System.out.println("5 - Menu Fornecedor");
            if (tipo.equals("Funcionario") || tipo.equals("ADM")) {
                System.out.println("6 - Menu Usuário");
            }
            System.out.println("7 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    menuClienteFisico(tipo);
                    break;
                case 2:
                    menuClienteJuridico(tipo);
                    break;
                case 3:
                    menuCategoria(tipo);
                    break;
                case 4:
                    menuDocumento(tipo);
                    break;
                case 5:
                    menuFornecedor(tipo);
                    break;
                case 6:
                    if (tipo.equals("Funcionario") || tipo.equals("ADM")) {
                        menuUsuario(tipo);
                    } else {
                        System.out.println("Acesso negado!");
                    }
                    break;
                case 7:
                    System.out.println("Sistema Encerrado com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

    public static void menuClienteFisico(String tipoUsuario) {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Cliente Físico ====");
            System.out.println("1 - Cadastrar Cliente");
            if (!tipoUsuario.equals("Cliente")) {
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Remover Cliente");
                System.out.println("4 - Alterar Cliente");
            }
            System.out.println("5 - Buscar Cliente");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastraClienteFisico();
                    break;
                case 2:
                    if (!tipoUsuario.equals("Cliente")) {
                        listarClienteFisico();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 3:
                    if (!tipoUsuario.equals("Cliente")) {
                        removeClienteFisico();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 4:
                    if (!tipoUsuario.equals("CLIENTE")) {
                        alteraClienteFisico();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 5:
                    buscarClienteFisico();
                    break;
                case 6:
                    System.out.println("Saindo do Menu");
                    break;
                default:
                    System.out.println("Opção Inválida!");
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

        System.out.print("Você deseja remover esse cliente? (S/N): ");
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

            if(novoNome.trim().isEmpty() || novoCpf.trim().isEmpty()){
                System.out.println("Verifique os dados informados antes de salvar");
            }

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

    public static void menuClienteJuridico(String tipoUsuario) {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Cliente Jurídico ====");
            System.out.println("1 - Cadastrar Cliente");
            if (!tipoUsuario.equals("Cliente")) {
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Remover Cliente");
                System.out.println("4 - Alterar Cliente");
            }
            System.out.println("5 - Buscar Cliente");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastraClienteJuridico();
                    break;
                case 2:
                    if (!tipoUsuario.equals("Cliente")) {
                        listarClienteJuridico();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 3:
                    if (!tipoUsuario.equals("Cliente")) {
                        removeClienteJuridico();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 4:
                    if (!tipoUsuario.equals("Cliente")) {
                        alteraClienteJuridico();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 5:
                    buscarClienteJuridico();
                    break;
                case 6:
                    System.out.println("Saindo do menu de Cliente!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
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
                    "Não foi possível cadastrar esse cliente, por favor, preencha todas as informações");
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
            System.out.println("Nenhum cliente Juridico encontrado.");
        } else {
            for (ClienteJuridico c : clientes) {
                System.out.println("Código: " + c.getCodigoCliente());
                System.out.println("Nome: " + c.getNomeCliente());
                System.out.println("CNPJ: " + c.getCNPJ());
                System.out.println("---------------------------");
            }
        }
    }

    private static void removeClienteJuridico() {
        System.out.println("\n==== Remover Cliente Físico ====");
        System.out.print("Informe o código do cliente: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("“Deseja remover este Cliente Jurídico? (S/N): ");
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

            System.out.print("Novo CNPJ: ");
            String novoCNPJ = teclado.nextLine();

            if(novoNome.trim().isEmpty() || novoCNPJ.trim().isEmpty()){
                System.out.println("“Por favor, verifique se todas as informações estão corretas");
                return;
            }
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

    public static void menuCategoria(String tipoUsuario) {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Categoria ====");
            System.out.println("1 - Criar Categoria");
            if (!tipoUsuario.equals("Cliente")) {
                System.out.println("2 - Listar Categoria");
                System.out.println("3 - Remover Categoria");
                System.out.println("4 - Alterar Categoria");
            }
            System.out.println("5 - Buscar Categoria");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastraCategoria();
                    break;
                case 2:
                    if (!tipoUsuario.equals("Cliente")) {
                        listarCategoria();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 3:
                    if (!tipoUsuario.equals("Cliente")) {
                        removerCategoria();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 4:
                    if (!tipoUsuario.equals("Cliente")) {
                        alteraCategoria();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 5:
                    buscarCategoria();
                    break;
                case 6:
                    System.out.println("Saindo do menu de categorias!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
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
            System.out.println("Por favor, preencha todas as informações");
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

        System.out.print("Você deseja remover essa categoria? (S/N): ");
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

    public static void menuDocumento(String tipoUsuario) {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Documento ====");
            System.out.println("1 - Salvar Documento");
            if (!tipoUsuario.equals("Cliente")) {
                System.out.println("2 - Listar Documento");
                System.out.println("3 - Remover Documento");
                System.out.println("4 - Alterar Documento");
            }
            System.out.println("5 - Buscar Documento");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastraDocumento();
                    break;
                case 2:
                    if (!tipoUsuario.equals("Cliente")) {
                        listarDocumento();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 3:
                    if (!tipoUsuario.equals("Cliente")) {
                        removeDocumento();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 4:
                    if (!tipoUsuario.equals("Cliente")) {
                        alteraDocumento();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 5:
                    buscarDocumento();
                    break;
                case 6:
                    System.out.println("Saindo do menu de documentos!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcao != 6);
    }

    private static void cadastraDocumento() {
        System.out.println("\n==== Cadastrar Documento ====");

        System.out.print("Informe o código do Documento: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Informe o Nome do Documento: ");
        String nome = teclado.nextLine();

        System.out.print("Informe a Descrição do Documento: ");
        String descricao = teclado.nextLine();

        if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
            System.out.println("Preencha corretamente todos os campos.");
            return;
        }

        System.out.print("Informe o ID da Categoria: ");
        int idCategoria = teclado.nextInt();
        teclado.nextLine();
        Categoria categoria = CateServi.buscarPorId(idCategoria);

        System.out.print("Digite 1 para Cliente Físico ou 2 para Jurídico: ");
        int tipoCliente = teclado.nextInt();
        teclado.nextLine();

        ClienteFisico clienteFisico = null;
        ClienteJuridico clienteJuridico = null;

        if (tipoCliente == 1) {
            System.out.print("Digite o código do Cliente Físico: ");
            int idClienteFisico = teclado.nextInt();
            teclado.nextLine();
            clienteFisico = ClienteFisiServi.buscarPorCodigo(idClienteFisico);
        } else if (tipoCliente == 2) {
            System.out.print("Digite o código do Cliente Jurídico: ");
            int idClienteJuridico = teclado.nextInt();
            teclado.nextLine();
            clienteJuridico = ClienteJuriServi.buscarPorCodigo(idClienteJuridico);
        }

        System.out.print("Digite o CNPJ do Fornecedor: ");
        String cnpj = teclado.nextLine();
        Fornecedor fornecedor = FornServi.buscarPorCNPJ(cnpj);

        if (categoria == null || fornecedor == null ||
                (clienteFisico == null && clienteJuridico == null)) {
            System.out.println("Preencha corretamente todos os campos");
            return;
        }

        Documento novoDoc = new Documento(codigo, nome, descricao, clienteFisico, clienteJuridico, categoria,
                fornecedor);

        System.out.print("Digite o caminho completo do arquivo a ser enviado: ");
        String caminhoOrigem = teclado.nextLine();

        Path origem = Paths.get(caminhoOrigem);
        if (!Files.exists(origem)) {
            System.out.println("Arquivo não encontrado no caminho informado.");
            return;
        }

        String nomeArquivo = nome + "_" + origem.getFileName();
        Path destino = Paths.get("ProjetoSistemaDigitalizacao\\documentos\\" + nomeArquivo);

        try {
            Files.createDirectories(destino.getParent());
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
            novoDoc.setCaminhoArquivo(destino.toString());
        
        } catch (IOException e) {
            System.out.println("Erro ao copiar o arquivo:");
            e.printStackTrace();
            return;
        }

        DocuServi.cadastra(novoDoc);
    }

    private static void listarDocumento() {
        System.out.println("\n==== Lista de Documentos ====");
        List<Documento> documentos = DocuServi.listar();

        if (documentos.isEmpty()) {
            System.out.println("Nenhum documento encontrado.");
        } else {
            for (Documento documento : documentos) {
                System.out.println("\n====== Informações do documento ====");
                System.out.println("ID: " + documento.getIdDocumento());
                System.out.println("Nome: " + documento.getNomeDocumento());
                System.out.println("Descrição: " + documento.getDescricaoDocumento());

                // Nome da categoria
                if (documento.getCategoria() != null) {
                    System.out.println("====== Categoria ======");
                    System.out.println("Nome da Categoria: " + documento.getCategoria().getNomeCategoria());
                }

                // Nome do cliente (pode ser físico ou jurídico)
                if (documento.getClientefisico() != null) {
                    System.out.println("====== Informação do cliente Fisico ======");
                    System.out.println("Cliente Físico: " + documento.getClientefisico().getNomeCliente());
                    System.out.println("CPF: " +  documento.getClientefisico().getCPF());
                    System.out.println("Codigo do cliente: " + documento.getClientefisico().getCodigoCliente());
                } else if (documento.getClientejuridico() != null) {
                    System.out.println("====== Informação do cliente Juridico ======");
                    System.out.println("Cliente Jurídico: " + documento.getClientejuridico().getNomeCliente());
                    System.out.println("CNPJ: " + documento.getClientejuridico().getCNPJ());
                    System.out.println("Codigo do cliente: " + documento.getClientejuridico().getCodigoCliente());
                }

                // Nome do fornecedor
                if (documento.getFornecedor() != null) {
                    System.out.println("====== Informação do Fornecedor ======");
                    System.out.println("Fornecedor: " + documento.getFornecedor().getNomeFornecedor());
                }

                System.out.println("---------------------------");
            }
        }
    }

    private static void removeDocumento() {
        System.out.println("\n==== Remover Documento ====");
        System.out.print("Informe o ID do Documento a ser removido: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Documento doc = DocuServi.buscarPorId(id);
        if (doc == null) {
            System.out.println("Documento não encontrado.");
            return;
        }

        System.out.print("Tem certeza que deseja excluir este documento? (S/N): ");
        String escolha = teclado.nextLine();

        if (escolha.equalsIgnoreCase("S")) {
            if (DocuServi.excluir(id)) {
                System.out.println("Documento removido com sucesso.");
            } else {
                System.out.println("Erro ao remover documento.");
            }
        } else {
            System.out.println("Operação cancelada.");
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
                System.out.println("Não foi possível alterar esse Documento, por favor, preencha todas as informações");
                return;
            }

            System.out.print("Informe o ID da Categoria: ");
            int idCategoria = teclado.nextInt();
            teclado.nextLine();
            Categoria categoria = CateServi.buscarPorId(idCategoria);

            System.out.print("Digite 1 para Cliente Físico ou 2 para Jurídico: ");
            int tipoCliente = teclado.nextInt();
            teclado.nextLine();

            ClienteFisico clienteFisico = null;
            ClienteJuridico clienteJuridico = null;

            if (tipoCliente == 1) {
                System.out.print("Digite o código do Cliente Físico: ");
                int idClienteFisico = teclado.nextInt();
                teclado.nextLine();
                clienteFisico = ClienteFisiServi.buscarPorCodigo(idClienteFisico);
            } else if (tipoCliente == 2) {
                System.out.print("Digite o código do Cliente Jurídico: ");
                int idClienteJuridico = teclado.nextInt();
                teclado.nextLine();
                clienteJuridico = ClienteJuriServi.buscarPorCodigo(idClienteJuridico);
            }

            System.out.print("Digite o CNPJ do Fornecedor: ");
            String cnpj = teclado.nextLine();
            Fornecedor fornecedor = FornServi.buscarPorCNPJ(cnpj);

            // Validação básica
            if (categoria == null || fornecedor == null ||
                    (clienteFisico == null && clienteJuridico == null)) {
                System.out.println("Erro: Categoria, fornecedor ou cliente inválido.");
                return;
            }

            Documento documentos = new Documento(id, novoNome, novaDescricao, clienteFisico, clienteJuridico, categoria,
                    fornecedor);
            DocuServi.alteraDocumento(documentos);
            System.out.println("Documento atualizado com sucesso.");
        } else {
            System.out.println("Documento não encontrado.");
        }
    }

    private static void buscarDocumento() {
        System.out.println("======= Buscar Documento =======");
        System.out.print("Digite o código do documento: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        Documento documento = DocuServi.buscarPorId(codigo);

        if (documento != null) {
            System.out.println("\n====== Informações do documento ====");
                System.out.println("ID: " + documento.getIdDocumento());
                System.out.println("Nome: " + documento.getNomeDocumento());
                System.out.println("Descrição: " + documento.getDescricaoDocumento());

                // Nome da categoria
                if (documento.getCategoria() != null) {
                    System.out.println("====== Categoria ======");
                    System.out.println("Nome da Categoria: " + documento.getCategoria().getNomeCategoria());
                }

                // Nome do cliente (pode ser físico ou jurídico)
                if (documento.getClientefisico() != null) {
                    System.out.println("====== Informação do cliente Fisico ======");
                    System.out.println("Cliente Físico: " + documento.getClientefisico().getNomeCliente());
                    System.out.println("CPF: " +  documento.getClientefisico().getCPF());
                    System.out.println("Codigo do cliente: " + documento.getClientefisico().getCodigoCliente());
                } else if (documento.getClientejuridico() != null) {
                    System.out.println("====== Informação do cliente Juridico ======");
                    System.out.println("Cliente Jurídico: " + documento.getClientejuridico().getNomeCliente());
                    System.out.println("CNPJ: " + documento.getClientejuridico().getCNPJ());
                    System.out.println("Codigo do cliente: " + documento.getClientejuridico().getCodigoCliente());
                }

                // Nome do fornecedor
                if (documento.getFornecedor() != null) {
                    System.out.println("====== Informação do Fornecedor ======");
                    System.out.println("Fornecedor: " + documento.getFornecedor().getNomeFornecedor());
                }

                System.out.println("---------------------------");

            System.out.print("Deseja fazer download do arquivo? (S/N): ");
            String opcao = teclado.nextLine();

            if (opcao.equalsIgnoreCase("S")) {

                String caminhoArquivo = documento.getCaminhoArquivo();

                if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
                    System.out.println("Erro: O caminho do arquivo não está salvo no documento.");
                    return;
                }
                System.out.print("Digite o caminho de destino (Ex.: C:\\Users\\SeuUsuario\\Downloads): ");
                String destinoStr = teclado.nextLine();

                Path origem = Paths.get(documento.getCaminhoArquivo());
                Path destino = Paths.get(destinoStr, origem.getFileName().toString());

                try {
                    Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Arquivo baixado com sucesso.");
                } catch (IOException e) {
                    System.out.println("Erro ao baixar o arquivo: " + e.getMessage());
                }
            }

        } else {
            System.out.println("Documento não encontrado.");
        }
    }

    public static void menuFornecedor(String tipoUsuario) {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Fornecedor ====");
            System.out.println("1 - Cadastrar Fornecedor");
            if (!tipoUsuario.equals("Cliente")) {
                System.out.println("2 - Listar Fornecedor");
                System.out.println("3 - Remover Fornecedor");
                System.out.println("4 - Alterar Fornecedor");
            }
            System.out.println("5 - Buscar Fornecedor");
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    if (!tipoUsuario.equals("Cliente")) {
                        listarFornecedor();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 3:
                    if (!tipoUsuario.equals("Cliente")) {
                        removerFornecedor();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 4:
                    if (!tipoUsuario.equals("Cliente")) {
                        alterarFornecedor();
                    } else {
                        System.out.println("Acesso negado.");
                    }
                    break;
                case 5:
                    buscarFornecedor();
                    break;
                case 6:
                    System.out.println("Saindo do menu fornecedores!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        } while (opcao != 6);
    }

    private static void cadastrarFornecedor() {
        System.out.println("\n==== Cadastrar Fornecedor ====");

        teclado.nextLine();

        System.out.print("CNPJ do Fornecedor: ");
        String cnpj = teclado.nextLine();

        System.out.print("Nome do Fornecedor: ");
        String nome = teclado.nextLine();

        System.out.print("Endereço do Fornecedor: ");
        String endereco = teclado.nextLine();

        if (nome.trim().isEmpty() || cnpj.trim().isEmpty() || endereco.trim().isEmpty()) {
            System.out.println("“Verifique se todas as informações do fornecedor estão corretas ou completas");
            return;
        }

        try {
            Fornecedor fornecedor = new Fornecedor(cnpj, nome, endereco);
            FornServi.cadastra(fornecedor);

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

        teclado.nextLine();

        System.out.print("Informe o CNPJ do fornecedor a ser removido: ");
        String cnpj = teclado.nextLine();

        System.out.print("Tem certeza que deseja remover este fornecedor? (S/N): ");
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

        teclado.nextLine();

        System.out.print("Informe o CNPJ do fornecedor a ser alterado: ");
        String cnpj = teclado.nextLine();

        Fornecedor fornecedorExistente = FornServi.buscarPorCNPJ(cnpj);
        if (fornecedorExistente != null) {

            System.out.print("Novo nome: ");
            String novoNome = teclado.nextLine();

            System.out.print("Novo endereço: ");
            String novoEndereco = teclado.nextLine();

            if (novoNome.trim().isEmpty() || novoEndereco.trim().isEmpty()) {
                System.out.println("Por favor, Verifique os dados informado.");
                return;
            }

            Fornecedor atualizado = new Fornecedor(cnpj, novoNome, novoEndereco);
            FornServi.alteraFornecedor(atualizado);
            System.out.println("Fornecedor atualizado com sucesso.");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    private static void buscarFornecedor() {
        System.out.println("\n==== Buscar Fornecedor ====");

        teclado.nextLine();

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

    public static void menuUsuario(String tipoUsuario) {
        int opcao = 0;

        do {
            System.out.println("\n==== Menu Usuario ====");
            System.out.println("1 - Cadastra Usuario");
            if (!tipoUsuario.equals("Funcionario")) {
                System.out.println("2 - Listar Usuario");
                System.out.println("3 - Remover Usuario");
                System.out.println("4 - Altera Usuario");
                System.out.println("5 - Buscar Usuario");
            }
            System.out.println("6 - Sair");
            System.out.print("Sua escolha: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastraUsuario();
                    break;
                case 2:
                    if(!tipoUsuario.equals("Funcionario")){
                        listarUsuario();
                    } else {
                        System.out.println("Acesso negado");
                    }
                    break;
                case 3:
                    if(!tipoUsuario.equals("Funcionario")){
                        removerUsuario();
                    } else {
                        System.out.println("Sem permissão");
                    }
                    break;
                case 4:
                    if(!tipoUsuario.equals("Funcionario")){
                        alteraUsuario();
                    } else {
                        System.out.println("Sem permissão");
                    }
                    break;
                case 5:
                    if(!tipoUsuario.equals("Funcionario")){
                        buscarUsuario();
                    } else {
                        System.out.println("Sem permissão");
                    }
                    break;
                case 6:
                    System.out.println("Saindo do menu de usuarios....!");
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

        System.out.print("Tipo de usuario(Cliente/Funcionario/ADM):");
        String tipo = teclado.nextLine();

        if (CPF.trim().isEmpty() || nome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty()
                || tipo.trim().isEmpty()) {
            System.out.println("Não foi possível cadastrar esse Usuario, por favor, preencha todas as informações");
            return;
        }

        try {
            Usuario usuario = new Usuario(id, nome, email, senha, CPF, tipo);
            UsuServi.cadastra(usuario);
        } catch (Exception e) {
            System.out.println("");
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
                System.out.println("Tipo de Usuario: " + usuario.getTipoUsuario());
                System.out.println("---------------------------");
            }
        }
    }

    private static void removerUsuario() {
        System.out.println("\n==== Remover Usuário ====");
        System.out.print("Informe o código do usuário a ser removido: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("“Tem certeza que deseja remover este usuário? (S/N): ");
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

            System.out.println("Novo tipo de usuario: ");
            String novoTipo = teclado.nextLine();

            if (novoNome.trim().isEmpty() || novoEmail.trim().isEmpty() || novaSenha.trim().isEmpty()
                    || novoCPF.trim().isEmpty()) {
                System.out.println("Por favor, Verifique se todas as informações estão corretas ou completas");
                return;
            }

            Usuario usuarios = new Usuario(codigo, novoNome, novoEmail, novaSenha, novoCPF, novoTipo);
            UsuServi.alteraUsuario(usuarios);

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
            System.out.println("======= Informação do Usuario =======");
            System.out.println("Nome do usuario: " + usuariobuscar.getNomeUsuario());
            System.out.println("CPF: " + usuariobuscar.getCpf());
            System.out.println("Codigo: " + usuariobuscar.getId());
            System.out.println("Email: " + usuariobuscar.getEmailUsuario());
            System.out.println("Tipo de acesso: " + usuariobuscar.getTipoUsuario());
        } else {
            System.out.println("Usuario não encontrado!");
        }

    }
}
import java.io.*;
import java.util.*;

public class Restaurante {
    private String nome;
    private List<Prato> cardapio;
    private List<Pedido> pedidos;
    private int proximoNumeroPedido;

    public Restaurante(String nome) {
        this.nome = nome;
        this.cardapio = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.proximoNumeroPedido = 1;
    }

    public String getNome() {
        return nome;
    }

    public List<Prato> getCardapio() {
        return cardapio;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // Métodos para gerenciar o cardápio
    public void adicionarPrato(Prato prato) {
        cardapio.add(prato);
        System.out.println("Prato adicionado ao cardápio: " + prato);
    }

    public void removerPrato(Prato prato) {
        cardapio.remove(prato);
        System.out.println("Prato removido do cardápio: " + prato);
    }

    public Prato buscarPrato(String nome) {
        for (Prato prato : cardapio) {
            if (prato.getNome().equalsIgnoreCase(nome)) {
                return prato;
            }
        }
        return null;
    }

    // Métodos para gerenciar pedidos
    public Pedido criarPedido(Cliente cliente) {
        Pedido novoPedido = new Pedido(proximoNumeroPedido++, cliente);
        pedidos.add(novoPedido);
        System.out.println("Pedido criado: " + novoPedido);
        return novoPedido;
    }

    public Pedido buscarPedido(int numeroPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido() == numeroPedido) {
                return pedido;
            }
        }
        return null;
    }

    public void atualizarStatusPedido(int numeroPedido, StatusPedido novoStatus) {
        Pedido pedido = buscarPedido(numeroPedido);
        if (pedido != null) {
            pedido.setStatus(novoStatus);
            System.out.println("Status do pedido #" + numeroPedido + " atualizado para: " + novoStatus);
        } else {
            System.out.println("Pedido #" + numeroPedido + " não encontrado");
        }
    }

    public void salvarDadosEmArquivo(String nomeArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Salvar cardápio
            for (Prato prato : cardapio) {
                writer.write("Prato:" + prato + "\n");
            }
            
            // Salvar pedidos
            for (Pedido pedido : pedidos) {
                writer.write("Pedido:" + pedido + "\n");
            }
        }
    }

    public void carregarDadosDeArquivo(String nomeArquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        }
    }

    public void imprimirCardapio() {
        System.out.println("\n===== CARDÁPIO - " + nome + " =====");
        for (Prato prato : cardapio) {
            System.out.println(prato.getNome() + " - R$ " + String.format("%.2f", prato.getPreco()));
            System.out.println("Descrição: " + prato.getDescricao());
            System.out.println("------------------------------");
        }
    }

    public void imprimirRelatorioPedidos() {
        System.out.println("\n===== RELATÓRIO DE PEDIDOS - " + nome + " =====");
        
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos registrados");
            return;
        }
        
        double valorTotal = 0;
        int totalPedidos = pedidos.size();
        
        for (Pedido pedido : pedidos) {
            System.out.println("Pedido #" + pedido.getNumeroPedido() + 
                              " - Cliente: " + pedido.getCliente().getNome() + 
                              " - Valor: R$ " + String.format("%.2f", pedido.getTotal()) +
                              " - Status: " + pedido.getStatus());
            valorTotal += pedido.getTotal();
        }
        
        System.out.println("------------------------------");
        System.out.println("Total de pedidos: " + totalPedidos);
        System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal));
    }
}

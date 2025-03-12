import java.io.IOException;

public class SistemaRestaurante {
    public static void main(String[] args) {
        // Criar restaurante
        Restaurante restaurante = new Restaurante("Sabor Brasileiro");

        // Adicionar pratos ao cardápio
        Prato prato1 = new Prato("Feijoada Completa", 45.90, "Feijoada tradicional com arroz, couve, laranja e farofa");
        Prato prato2 = new Prato("Moqueca de Peixe", 52.50, "Moqueca de peixe com arroz, pirão e farofa");
        Prato prato3 = new Prato("Bobó de Camarão", 58.90, "Bobó de camarão com arroz e farofa");
        Prato prato4 = new Prato("Picanha na Brasa", 68.90, "Picanha grelhada com batatas, arroz e vinagrete");

        restaurante.adicionarPrato(prato1);
        restaurante.adicionarPrato(prato2);
        restaurante.adicionarPrato(prato3);
        restaurante.adicionarPrato(prato4);

        // Criar clientes
        Cliente cliente1 = new Cliente("João Silva", "(11) 99999-8888", "Rua das Flores, 123");
        Cliente cliente2 = new Cliente("Maria Oliveira", "(11) 97777-6666", "Av. Paulista, 1000");

        // Imprimir cardápio
        restaurante.imprimirCardapio();

        // Criar pedidos
        Pedido pedido1 = restaurante.criarPedido(cliente1);
        pedido1.adicionarItem(prato1, 2);
        pedido1.adicionarItem(prato4, 1);
        pedido1.finalizarPedido();
        restaurante.atualizarStatusPedido(pedido1.getNumeroPedido(), StatusPedido.PREPARANDO);

        Pedido pedido2 = restaurante.criarPedido(cliente2);
        pedido2.adicionarItem(prato2, 1);
        pedido2.adicionarItem(prato3, 1);
        pedido2.finalizarPedido();
        restaurante.atualizarStatusPedido(pedido2.getNumeroPedido(), StatusPedido.PRONTO);

        // Imprimir detalhes dos pedidos
        System.out.println("\n===== DETALHES DOS PEDIDOS =====");
        System.out.println(pedido1.visualizarPedido());
        System.out.println("\n------------------------------");
        System.out.println(pedido2.visualizarPedido());

        // Imprimir relatório de pedidos
        restaurante.imprimirRelatorioPedidos();

        // Salvar dados em arquivo
        try {
            restaurante.salvarDadosEmArquivo("restaurante.txt");
            restaurante.carregarDadosDeArquivo("restaurante.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

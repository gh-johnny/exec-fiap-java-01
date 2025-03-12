import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {
    private Pedido pedido;
    private Cliente cliente;
    private Prato prato1;
    private Prato prato2;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Cliente Teste", "1234-5678", "Endereço Teste");
        pedido = new Pedido(1, cliente);
        prato1 = new Prato("Prato Teste 1", 25.0, "Descrição Teste 1");
        prato2 = new Prato("Prato Teste 2", 35.0, "Descrição Teste 2");
    }

    @Test
    public void testAdicionarItem() {
        pedido.adicionarItem(prato1, 2);
        assertEquals(1, pedido.getItensPedido().size());
        assertEquals(50.0, pedido.getTotal());
    }

    @Test
    public void testAdicionarItemExistente() {
        pedido.adicionarItem(prato1, 2);
        pedido.adicionarItem(prato1, 1);
        assertEquals(1, pedido.getItensPedido().size());
        assertEquals(75.0, pedido.getTotal());
        assertEquals(3, pedido.getItensPedido().get(0).getQuantidade());
    }

    @Test
    public void testRemoverItem() {
        pedido.adicionarItem(prato1, 2);
        pedido.adicionarItem(prato2, 1);
        pedido.removerItem(prato1);
        assertEquals(1, pedido.getItensPedido().size());
        assertEquals(35.0, pedido.getTotal());
    }

    @Test
    public void testCalcularTotal() {
        pedido.adicionarItem(prato1, 2);
        pedido.adicionarItem(prato2, 1);
        assertEquals(85.0, pedido.getTotal());
    }

    @Test
    public void testFinalizarPedido() {
        pedido.adicionarItem(prato1, 2);
        pedido.finalizarPedido();
        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());
    }

    @Test
    public void testFinalizarPedidoSemItens() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            pedido.finalizarPedido();
        });
        assertTrue(exception.getMessage().contains("Não é possível finalizar um pedido sem itens"));
    }

    @Test
    public void testVisualizarPedido() {
        pedido.adicionarItem(prato1, 2);
        String visualizacao = pedido.visualizarPedido();
        assertTrue(visualizacao.contains("Pedido #1"));
        assertTrue(visualizacao.contains("Cliente: Cliente Teste"));
        assertTrue(visualizacao.contains("2x Prato Teste 1"));
        assertTrue(visualizacao.contains("Total: R$ 50.00"));
    }
}

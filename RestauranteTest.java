import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {
    private Restaurante restaurante;
    private Prato prato1;
    private Prato prato2;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante("Teste Restaurante");
        prato1 = new Prato("Prato Teste 1", 25.0, "Descrição Teste 1");
        prato2 = new Prato("Prato Teste 2", 35.0, "Descrição Teste 2");
        cliente = new Cliente("Cliente Teste", "1234-5678", "Endereço Teste");
        
        restaurante.adicionarPrato(prato1);
        restaurante.adicionarPrato(prato2);
    }

    @Test
    public void testAdicionarPrato() {
        Prato novoPrato = new Prato("Novo Prato", 30.0, "Nova Descrição");
        restaurante.adicionarPrato(novoPrato);
        assertTrue(restaurante.getCardapio().contains(novoPrato));
        assertEquals(3, restaurante.getCardapio().size());
    }

    @Test
    public void testRemoverPrato() {
        restaurante.removerPrato(prato1);
        assertFalse(restaurante.getCardapio().contains(prato1));
        assertEquals(1, restaurante.getCardapio().size());
    }

    @Test
    public void testBuscarPrato() {
        Prato encontrado = restaurante.buscarPrato("Prato Teste 1");
        assertNotNull(encontrado);
        assertEquals(prato1.getNome(), encontrado.getNome());
        
        Prato naoEncontrado = restaurante.buscarPrato("Prato Inexistente");
        assertNull(naoEncontrado);
    }

    @Test
    public void testCriarPedido() {
        Pedido pedido = restaurante.criarPedido(cliente);
        assertNotNull(pedido);
        assertEquals(1, pedido.getNumeroPedido());
        assertEquals(cliente, pedido.getCliente());
        assertTrue(restaurante.getPedidos().contains(pedido));
    }

    @Test
    public void testBuscarPedido() {
        Pedido pedido = restaurante.criarPedido(cliente);
        Pedido encontrado = restaurante.buscarPedido(1);
        assertEquals(pedido, encontrado);
        
        Pedido naoEncontrado = restaurante.buscarPedido(999);
        assertNull(naoEncontrado);
    }

    @Test
    public void testAtualizarStatusPedido() {
        Pedido pedido = restaurante.criarPedido(cliente);
        restaurante.atualizarStatusPedido(pedido.getNumeroPedido(), StatusPedido.PREPARANDO);
        assertEquals(StatusPedido.PREPARANDO, pedido.getStatus());
    }
}

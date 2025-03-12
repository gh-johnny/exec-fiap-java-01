import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemPedidoTest {
    private Prato prato;
    private ItemPedido itemPedido;

    @BeforeEach
    public void setUp() {
        prato = new Prato("Prato Teste", 25.0, "Descrição Teste");
        itemPedido = new ItemPedido(prato, 2);
    }

    @Test
    public void testCalcularSubtotal() {
        assertEquals(50.0, itemPedido.getSubtotal());
    }

    @Test
    public void testAlterarQuantidade() {
        itemPedido.setQuantidade(3);
        assertEquals(3, itemPedido.getQuantidade());
        assertEquals(75.0, itemPedido.getSubtotal());
    }
}

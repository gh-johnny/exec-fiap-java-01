import java.util.*;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private List<ItemPedido> itensPedido;
    private double total;
    private Date dataPedido;
    private StatusPedido status;

    public Pedido(int numeroPedido, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itensPedido = new ArrayList<>();
        this.total = 0.0;
        this.dataPedido = new Date();
        this.status = StatusPedido.ABERTO;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public double getTotal() {
        return total;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void adicionarItem(Prato prato, int quantidade) {
        // Verificar se o item já existe no pedido
        for (ItemPedido item : itensPedido) {
            if (item.getPrato().getNome().equals(prato.getNome())) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                calcularTotal();
                return;
            }
        }
        
        // Se não existe, adiciona novo item
        ItemPedido novoItem = new ItemPedido(prato, quantidade);
        itensPedido.add(novoItem);
        calcularTotal();
    }

    public void removerItem(Prato prato) {
        Iterator<ItemPedido> iterator = itensPedido.iterator();
        while (iterator.hasNext()) {
            ItemPedido item = iterator.next();
            if (item.getPrato().getNome().equals(prato.getNome())) {
                iterator.remove();
                calcularTotal();
                return;
            }
        }
    }

    public void calcularTotal() {
        total = 0.0;
        for (ItemPedido item : itensPedido) {
            total += item.getSubtotal();
        }
    }

    public void finalizarPedido() {
        if (itensPedido.isEmpty()) {
            throw new IllegalStateException("Não é possível finalizar um pedido sem itens");
        }
        setStatus(StatusPedido.FINALIZADO);
    }

    public String visualizarPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(numeroPedido).append("\n");
        sb.append("Cliente: ").append(cliente.getNome()).append("\n");
        sb.append("Data: ").append(dataPedido).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Itens:\n");
        
        for (ItemPedido item : itensPedido) {
            sb.append(" - ")
              .append(item.getQuantidade())
              .append("x ")
              .append(item.getPrato().getNome())
              .append(" (R$ ")
              .append(String.format("%.2f", item.getPrato().getPreco()))
              .append(" cada) - Subtotal: R$ ")
              .append(String.format("%.2f", item.getSubtotal()))
              .append("\n");
        }
        
        sb.append("Total: R$ ").append(String.format("%.2f", total));
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente=" + cliente +
                ", total=" + total +
                ", dataPedido=" + dataPedido +
                ", status=" + status +
                '}';
    }
}

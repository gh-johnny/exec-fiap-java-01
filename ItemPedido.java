public class ItemPedido {
    private Prato prato;
    private int quantidade;
    private double subtotal;

    public ItemPedido(Prato prato, int quantidade) {
        this.prato = prato;
        this.quantidade = quantidade;
        calcularSubtotal();
    }

    public Prato getPrato() {
        return prato;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal();
    }

    private void calcularSubtotal() {
        this.subtotal = prato.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "prato=" + prato +
                ", quantidade=" + quantidade +
                ", subtotal=" + subtotal +
                '}';
    }
}

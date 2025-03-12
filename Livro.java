public class Livro {
    private String titulo;
    private String autor;
    private String ISBN;

    public Livro(String titulo, String autor, String ISBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return ISBN;
    }

//    Esse trecho de código é importante porque sobrescreve o método toString() da classe Object,
//    que é a classe base de todas as classes em Java. O método toString() é chamado implicitamente
//    quando você tenta imprimir o objeto ou quando você precisa de uma representação em string do objeto,
//    como ao salvar dados em um arquivo ou ao fazer log de informações.
//    Sem sobrescrever o método toString(), a chamada para System.out.println(livro) resultaria na impressão do
//    nome da classe seguido pelo hash code do objeto, algo como Livro@1a2b3c. Ao sobrescrever toString(),
//    você obtém uma representação legível e mais informativa do objeto.
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
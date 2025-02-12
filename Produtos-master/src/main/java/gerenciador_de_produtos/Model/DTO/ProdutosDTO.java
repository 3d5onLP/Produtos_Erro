// Declaração do pacote onde a classe está localizada
package gerenciador_de_produtos.Model.DTO;

// Importações necessárias para usar anotações do JPA (Java Persistence API)
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Anotação @Entity indica que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados
@Entity
@Table(name = "produtos")
public class ProdutosDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidade")
    private int quantidade; // Campo que armazena a quantidade do produto em estoque

    // Anotação @Column para o campo 'categoria', que será mapeado para a coluna "categoria" no banco de dados
    @Column(name = "categoria")
    private String categoria; // Campo que armazena a categoria do produto

    // Anotação @Column para o campo 'descricao', que será mapeado para a coluna "descricao" no banco de dados
    @Column(name = "descricao")
    private String descricao; // Campo que armazena a descrição do produto

    // Anotação @Column para o campo 'imagem', que será mapeado para a coluna "imagem" no banco de dados
    @Column(name = "imagem")
    private String imagem; // Campo que armazena a URL da imagem do produto

    // Construtor sem parâmetros, necessário para o JPA
    public ProdutosDTO() {
    }

    // Construtor com parâmetros, que inicializa todos os campos, exceto o 'id'
    public ProdutosDTO(String nome, double preco, int quantidade, String categoria, String descricao, String imagem) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    // Construtor com parâmetros, que inicializa todos os campos, incluindo o 'id'
    public ProdutosDTO(Integer id, String nome, double preco, int quantidade, String categoria, String descricao,
            String imagem) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    // Métodos Getters e Setters para os campos da classe

    // Getter para o campo 'categoria'
    public String getCategoria() {
        return categoria;
    }

    // Setter para o campo 'categoria'
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter para o campo 'descricao'
    public String getDescricao() {
        return descricao;
    }

    // Setter para o campo 'descricao'
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para o campo 'imagem'
    public String getImagem() {
        return imagem;
    }

    // Setter para o campo 'imagem'
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    // Getter para o campo 'id'
    public Integer getId() {
        return id;
    }

    // Getter para o campo 'nome'
    public String getNome() {
        return nome;
    }

    // Setter para o campo 'nome'
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o campo 'preco'
    public Double getPreco() {
        return preco;
    }

    // Setter para o campo 'preco'
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Getter para o campo 'quantidade'
    public int getQuantidade() {
        return quantidade;
    }

    // Setter para o campo 'quantidade'
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Sobrescrita do método toString() para fornecer uma representação em string do objeto
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + String.format("%.2f", preco) + // Formata o preço com duas casas decimais
                ", quantidade=" + quantidade +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
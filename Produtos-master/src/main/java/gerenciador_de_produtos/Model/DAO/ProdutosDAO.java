package gerenciador_de_produtos.Model.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import static org.hibernate.cfg.JdbcSettings.FORMAT_SQL;
import static org.hibernate.cfg.JdbcSettings.HIGHLIGHT_SQL;
import static org.hibernate.cfg.JdbcSettings.PASS;
import static org.hibernate.cfg.JdbcSettings.SHOW_SQL;
import static org.hibernate.cfg.JdbcSettings.URL;
import static org.hibernate.cfg.JdbcSettings.USER;

import gerenciador_de_produtos.Model.DTO.ProdutosDTO;

public class ProdutosDAO {

    // Cria a conexão com o banco de dados usando Hibernate
    private SessionFactory configuration = new Configuration()
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
            .addAnnotatedClass(ProdutosDTO.class) // Produto é uma entidade mapeada
            .setProperty(URL, "jdbc:mysql://localhost:3306/banco_produtos")
            .setProperty(USER, "root")
            .setProperty(PASS, "")
            .setProperty("hibernate.agroal.maxSize", 20)
            .setProperty(SHOW_SQL, true)
            .setProperty(FORMAT_SQL, true)
            .setProperty(HIGHLIGHT_SQL, true)
            .buildSessionFactory();

    // Método para adicionar um produto no banco de dados
    public void addProduto(ProdutosDTO produto) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Persiste o produto na base de dados
            session.persist(produto);
            // Comita a transação para salvar as mudanças no banco
            session.getTransaction().commit();
        }
    }

    // Método para excluir um produto pelo seu ID
    public void deleteProduto(Integer id) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Busca o produto com o ID fornecido
            ProdutosDTO produto = session.get(ProdutosDTO.class, id);
            // Se o produto existir, realiza a exclusão
            if (produto != null) {
                session.delete(produto);
            }
            // Comita a transação para salvar a exclusão no banco
            session.getTransaction().commit();
        }
    }

    // Método para obter um produto pelo seu ID
    public ProdutosDTO getProduto(Integer id) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Retorna o produto com o ID fornecido, se existir
            return session.get(ProdutosDTO.class, id);
        }
    }

    // Método para atualizar um produto no banco de dados
    public ProdutosDTO updateProduto(Integer id, ProdutosDTO produto) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Busca o produto existente pelo ID
            ProdutosDTO produtoExistente = session.get(ProdutosDTO.class, id);
            // Se o produto existir, atualiza seus dados
            if (produtoExistente != null) {
                produtoExistente.setNome(produto.getNome());
                produtoExistente.setPreco(produto.getPreco());
                produtoExistente.setQuantidade(produto.getQuantidade());
                // Atualiza o produto no banco de dados
                session.update(produtoExistente);
            }
            // Comita a transação para salvar as mudanças
            session.getTransaction().commit();
            return produtoExistente;
        }
    }

    // Método para listar todos os produtos cadastrados
    public List<ProdutosDTO> listAllProdutos() {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Consulta todos os produtos no banco de dados
            List<ProdutosDTO> produtos = session.createQuery("FROM ProdutosDTO", ProdutosDTO.class).getResultList();
            // Comita a transação
            session.getTransaction().commit();
            return produtos;
        }
    }

    // Método para buscar produtos pelo nome (parcial)
    public List<ProdutosDTO> searchProdutoByName(String nome) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Consulta produtos cujo nome contenha o termo fornecido
            List<ProdutosDTO> produtos = session.createQuery("FROM ProdutosDTO WHERE nome LIKE :nome", ProdutosDTO.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
            // Comita a transação
            session.getTransaction().commit();
            return produtos;
        }
    }
}

# Gerenciador de Produtos

O **Gerenciador de Produtos** é uma aplicação desenvolvida em Java, utilizando Hibernate para interação com o banco de dados, que permite realizar operações básicas de CRUD (Criar, Ler, Atualizar e Deletar) em uma tabela de produtos.

Este projeto foi desenvolvido para demonstrar uma implementação simples de um sistema de gerenciamento de produtos, utilizando Hibernate para persistência de dados e integração com um banco de dados MySQL.

## 🧑‍💻 Equipe de Desenvolvimento

### 👑 Membro 1
![Foto do Membro 1](https://avatars.githubusercontent.com/u/105525805?v=4.jpg)
**Michael Douglas**  
🎓 Graduando em Licenciatura em Computação.  
🛠️ Função: Desenvolvedor Front-Back-end.  
- GitHub: [MichaelDouglasCA](https://github.com/MichaelDouglasCA)

### 👑 Membro 2
![Foto do Membro 2](https://avatars.githubusercontent.com/u/116564293?v=4)
**Edson Lopes**  
🎓 Graduando em Análise e Desenvolvimento de Sistemas.  
🛠️ Função: Desenvolvedor Front-Back-end.  
- GitHub: [3d5onLP](https://github.com/3d5onLP)
- 
### 👑 Membro 3
![Foto do Membro 3](https://avatars.githubusercontent.com/u/93807789?v=4.jpg)
**Klaven Castro**  
🎓 Graduando em Análise e Desenvolvimento de Sistemas.  
🛠️ Função: Desenvolvedor Front-Back-end.  
- GitHub: [Klayverq](https://github.com/Klayverq)

### 👑 Membro 4
![Foto do Membro 4](https://avatars.githubusercontent.com/u/130615855?v=4)
**Igor Gabriel**  
🎓 Graduando em Análise e Desenvolvimento de Sistemas.  
🛠️ Função: Desenvolvedor Front-Back-end.  
- GitHub: [IgorGabrod](https://github.com/IgorGabrod)

  ## Modelo MVC

O projeto segue o padrão **MVC (Model-View-Controller)** para melhor organização do código:

## Estrutura do Projeto

O projeto segue o padrão MVC (Model-View-Controller), organizado da seguinte forma:

```
Gerenciador_Produtos/
│-- src/
│   │-- main/
│   │   │-- java/
│   │   │   │-- gerenciador_de_produtos/
│   │   │   │   │-- model/                 # Contém as classes de modelo (Entidades)
|   │   │   |   |    │-- dao/              # Contém as classes de acesso a dados e manipulação do banco de dados
│   │   │   │   │     │-- dto/             # Contém as classes de acesso a dados comum
│   │   │   │   │-- controller/            # Contém as classes responsáveis pela lógica do sistema
│   │   │   │   │-- view/                  # Contém as classes responsáveis pela interface do usuário
│   │   │   │   |-- App/Main.java          # Classe principal para executar a aplicação
│   |   |   │-- resources/                 # Contém arquivos de configuração
|   |   |   |   |--META-INF                # Armazenar metadados de configuração
|   |   |   |   |  |-- persistence.xml     # Configura a conexão com o banco de dados (driver, URL, usuário e senha)
│   │   |   │-- hibernate.cfg.xml          # Configuração do Hibernate
│-- README.md                              # Documentação do projeto
```

## Funcionalidades

- **Adicionar Produtos**: Cria novos produtos com informações como nome, preço e quantidade.
- **Excluir Produtos**: Remove produtos existentes do banco de dados.
- **Atualizar Produtos**: Permite a edição de dados dos produtos, como nome, preço e quantidade.
- **Listar Produtos**: Exibe todos os produtos cadastrados na base de dados.
- **Pesquisar Produtos**: Busca produtos pelo nome, com suporte para pesquisa parcial.

## Tecnologias Utilizadas

- **Java**: Linguagem principal para desenvolvimento da aplicação.
- **Hibernate**: Framework ORM (Object-Relational Mapping) utilizado para mapear objetos Java para tabelas do banco de dados.
- **MySQL**: Banco de dados utilizado para armazenar as informações dos produtos.
- **JPA (Jakarta Persistence API)**: API para gerenciamento de dados relacionais, integrada com o Hibernate.
- **SQL**: Linguagem de consulta usada para interagir com o banco de dados.

## Estrutura do Projeto

O projeto é composto pelas seguintes classes principais:

- **Produto.java**: Representa a entidade `Produto`, com os atributos `id`, `nome`, `preco` e `quantidade`. Esta classe está anotada com JPA para mapear os atributos para a tabela `produtos` no banco de dados.
- **GerenciadorEstoque.java**: Responsável pela lógica de interação com o banco de dados, implementando as operações CRUD para a classe `Produto`.

## Requisitos

Para rodar este projeto localmente, você precisará de:

- **Java 8 ou superior** instalado.
- **MySQL** instalado e configurado.
- **XAMPP**: Para facilitar a instalação do MySQL e Apache, baixe em: [https://www.apachefriends.org/pt_br/download.html](https://www.apachefriends.org/pt_br/download.html)
- **IDE** (como IntelliJ IDEA, VSCode, Eclipse e etc.) para compilar e executar o código.
- Dependências do Hibernate configuradas no `pom.xml` (se utilizando Maven) ou `build.gradle` (se utilizando Gradle).

## Como Rodar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/3d5onLP/Produtos_Erro.git
   ```

2. **Configuração do Banco de Dados**:
   - Crie um banco de dados no MySQL chamado `banco_produtos` ou altere a URL de conexão no código para o nome do seu banco de dados.
   - Exemplo de configuração no código:
     ```java
     .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/banco_produtos")
     ```

3. **Adicione as dependências**:
   - Se estiver utilizando Maven, adicione as dependências do Hibernate no `pom.xml`.
   - Se estiver utilizando Gradle, adicione as dependências no `build.gradle`.

4. **Compile e execute o projeto**:
   - Compile o código utilizando sua IDE ou com o comando Maven/Gradle.
   - Execute a classe `GerenciadorEstoque` para iniciar a aplicação.

## Exemplos de Uso

### Adicionar um Produto
```java
Produto novoProduto = new Produto("Produto Exemplo", 25.50, 10);
gerenciador.addProduto(novoProduto);
```

### Atualizar um Produto
```java
Produto produtoExistente = new Produto(1, "Produto Atualizado", 30.00, 15);
gerenciador.updateProduto(1, produtoExistente);
```

### Excluir um Produto
```java
gerenciador.deleteProduto(1);
```

### Listar Todos os Produtos
```java
List<Produto> produtos = gerenciador.listAllProdutos();
produtos.forEach(System.out::println);
```

### Pesquisar Produtos por Nome
```java
List<Produto> produtosEncontrados = gerenciador.searchProdutoByName("Produto");
produtosEncontrados.forEach(System.out::println);
```

## Contribuição

Se você quiser contribuir para o desenvolvimento deste projeto, sinta-se à vontade para abrir uma **pull request** com melhorias ou sugestões. Toda contribuição é bem-vinda!

## Licença

Este projeto é licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---


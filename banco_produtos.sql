-- Banco de dados: `banco_produtos`
CREATE DATABASE IF NOT EXISTS `banco_produtos`;
USE `banco_produtos`;

-- --------------------------------------------------------

-- Estrutura para tabela `produtos`
CREATE TABLE `produtos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `imagem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) -- Definindo a chave primária
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Despejando dados para a tabela `produtos`

INSERT INTO `produtos` (`id`, `nome`, `preco`, `quantidade`, `categoria`, `descricao`, `imagem`) VALUES
(1, 'Farinha de Trigo - Com fermento', 5.30, 250, 'Alimentos', 'Farinha de trigo para pães, bolos e massas em geral.', 'https://example.com/farinha_trigo-fermento.jpg'),
(2, 'Arroz Integral', 10.99, 200, 'Alimentos', 'Arroz integral de alta qualidade, ideal para dietas saudáveis.', 'https://example.com/arroz_integral.jpg'),
(3, 'Feijão Preto', 8.50, 150, 'Alimentos', 'Feijão preto, ótimo para feijão tropeiro ou caldo de feijão.', 'https://example.com/feijao_preto.jpg'),
(4, 'Açúcar Cristal', 3.75, 300, 'Alimentos', 'Açúcar cristal para adoçar bebidas e preparos culinários.', 'https://example.com/acucar_cristal.jpg'),
(5, 'Óleo de Soja', 5.40, 120, 'Alimentos', 'Óleo de soja para uso culinário, excelente para frituras e refogados.', 'https://example.com/oleo_soja.jpg'),
(6, 'Macarrão Instantâneo', 2.20, 500, 'Alimentos', 'Macarrão instantâneo, rápido e fácil de preparar em qualquer momento.', 'https://example.com/macarrao_instantaneo.jpg'),
(7, 'Suco de Laranja', 4.99, 250, 'Bebidas', 'Suco de laranja natural, sem conservantes.', 'https://example.com/suco_laranja.jpg'),
(8, 'Maçã', 3.50, 100, 'Alimentos', 'Maçãs frescas e doces, ideais para lanches.', 'https://example.com/maca.jpg'),
(9, 'Banana', 2.30, 180, 'Alimentos', 'Bananas maduras e saborosas, ricas em potássio.', 'https://example.com/banana.jpg'),
(10, 'Pão de Forma', 4.00, 350, 'Alimentos', 'Pão de forma macio e fofinho, perfeito para lanches.', 'https://example.com/pao_de_forma.jpg'),
(11, 'Leite Integral', 5.90, 200, 'Bebidas', 'Leite integral, rico em nutrientes para toda a família.', 'https://example.com/leite_integral.jpg'),
(12, 'Café em Pó', 6.80, 150, 'Bebidas', 'Café moído, ideal para preparar uma bebida forte e saborosa.', 'https://example.com/cafe_em_po.jpg'),
(13, 'Batata Doce', 4.50, 180, 'Alimentos', 'Batata doce rica em fibras, ideal para receitas saudáveis.', 'https://example.com/batata_doce.jpg'),
(14, 'Alface', 2.20, 100, 'Alimentos', 'Alface fresca, perfeita para saladas leves.', 'https://example.com/alface.jpg'),
(15, 'Tomate', 4.10, 120, 'Alimentos', 'Tomates maduros e saborosos para suas receitas.', 'https://example.com/tomate.jpg'),
(16, 'Peito de Frango', 13.99, 80, 'Alimentos', 'Peito de frango fresco, ideal para grelhar ou assar.', 'https://example.com/peito_de_frango.jpg'),
(17, 'Picanha', 49.90, 60, 'Alimentos', 'Picanha macia, perfeita para um churrasco.', 'https://example.com/picanha.jpg'),
(18, 'Queijo Mussarela', 10.50, 150, 'Alimentos', 'Queijo mussarela fresco e delicioso, ótimo para pizzas.', 'https://example.com/queijo_mussarela.jpg'),
(19, 'Presunto', 8.40, 180, 'Alimentos', 'Presunto fatiado, ideal para sanduíches e aperitivos.', 'https://example.com/presunto.jpg'),
(20, 'Chocolates Sortidos', 12.50, 90, 'Doces', 'Chocolate sortido com diversos sabores e recheios.', 'https://example.com/chocolates_sortidos.jpg'),
(21, 'Biscoito de Polvilho', 3.00, 250, 'Doces', 'Biscoito de polvilho crocante e saboroso, ideal para o lanche.', 'https://example.com/biscoito_polvilho.jpg'),
(22, 'Bolacha Maria', 2.90, 300, 'Doces', 'Bolacha Maria clássica, deliciosa com café ou chá.', 'https://example.com/bolacha_maria.jpg'),
(23, 'Cereal Matinal', 7.50, 130, 'Alimentos', 'Cereal matinal nutritivo, ideal para o café da manhã.', 'https://example.com/cereal_matinal.jpg'),
(24, 'Macarrão Parafuso', 3.60, 400, 'Alimentos', 'Macarrão paracuso, excelente para saladas frias ou molhos.', 'https://example.com/macarrao_parafuso.jpg'),
(25, 'Ketchup', 2.50, 200, 'Alimentos', 'Ketchup de tomate, para acompanhar hambúrgueres e fritas.', 'https://example.com/ketchup.jpg'),
(26, 'Mostarda', 2.80, 150, 'Alimentos', 'Mostarda tradicional, ideal para temperar sanduíches e pratos diversos.', 'https://example.com/mostarda.jpg'),
(27, 'Maionese', 3.50, 180, 'Alimentos', 'Maionese cremosa para acompanhar lanches e saladas.', 'https://example.com/maionese.jpg'),
(28, 'Iogurte Natural', 6.90, 120, 'Bebidas', 'Iogurte natural sem açúcar, ótimo para dietas balanceadas.', 'https://example.com/iogurte_natural.jpg'),
(29, 'Margarina', 4.20, 200, 'Alimentos', 'Margarina cremosa para passar no pão ou cozinhar.', 'https://example.com/margarina.jpg'),
(30, 'Farinha de Trigo', 5.30, 250, 'Alimentos', 'Farinha de trigo para pães, bolos e massas em geral.', 'https://example.com/farinha_trigo.jpg'),
(31, 'Farinha de Mandioca', 3.90, 180, 'Alimentos', 'Farinha de mandioca ideal para farofas e receitas típicas.', 'https://example.com/farinha_mandioca.jpg'),
(32, 'Sal Refinado', 1.90, 300, 'Alimentos', 'Sal refinado para temperar suas receitas do dia a dia.', 'https://example.com/sal_refinado.jpg'),
(33, 'Pimenta-do-Reino', 7.40, 100, 'Temperos', 'Pimenta-do-reino moída, ideal para dar um toque especial aos pratos.', 'https://example.com/pimenta_reino.jpg'),
(34, 'Orégano', 3.20, 120, 'Temperos', 'Orégano seco, perfeito para pizzas, massas e carnes.', 'https://example.com/oregano.jpg'),
(35, 'Vinagre de Maçã', 5.10, 130, 'Alimentos', 'Vinagre de maçã natural, ótimo para temperar saladas.', 'https://example.com/vinagre_maca.jpg'),
(36, 'Molho de Tomate', 4.70, 150, 'Alimentos', 'Molho de tomate pronto para deixar suas massas mais saborosas.', 'https://example.com/molho_tomate.jpg'),
(37, 'Ervilha em Lata', 3.80, 200, 'Alimentos', 'Ervilha enlatada para adicionar em receitas variadas.', 'https://example.com/ervilha.jpg'),
(38, 'Milho Verde em Lata', 3.90, 190, 'Alimentos', 'Milho verde enlatado, pronto para consumo.', 'https://example.com/milho_verde.jpg'),
(39, 'Azeitona Verde', 6.50, 140, 'Alimentos', 'Azeitonas verdes selecionadas, ótimas para saladas e petiscos.', 'https://example.com/azeitona_verde.jpg'),
(40, 'Sardinha em Lata', 4.80, 120, 'Alimentos', 'Sardinha enlatada em óleo, rica em ômega 3.', 'https://example.com/sardinha.jpg'),
(41, 'Atum em Lata', 7.60, 110, 'Alimentos', 'Atum sólido em óleo, ideal para saladas e sanduíches.', 'https://example.com/atum.jpg'),
(42, 'Refrigerante Cola', 6.00, 300, 'Bebidas', 'Refrigerante sabor cola, refrescante e saboroso.', 'https://example.com/refrigerante_cola.jpg'),
(43, 'Refrigerante de Guaraná', 5.50, 280, 'Bebidas', 'Refrigerante de guaraná com sabor natural.', 'https://example.com/refrigerante_guarana.jpg'),
(44, 'Água Mineral 500ml', 1.50, 500, 'Bebidas', 'Água mineral sem gás, ideal para hidratação diária.', 'https://example.com/agua_mineral.jpg'),
(45, 'Água com Gás', 2.20, 300, 'Bebidas', 'Água gaseificada para acompanhar refeições.', 'https://example.com/agua_com_gas.jpg'),
(46, 'Suco de Uva Integral', 9.50, 150, 'Bebidas', 'Suco de uva 100% natural, sem conservantes.', 'https://example.com/suco_uva.jpg'),
(47, 'Bebida Energética', 7.90, 100, 'Bebidas', 'Bebida energética para dar mais disposição no dia a dia.', 'https://example.com/bebida_energetica.jpg'),
(48, 'Chocolate em Barra', 5.80, 170, 'Doces', 'Chocolate ao leite cremoso e saboroso.', 'https://example.com/chocolate_barra.jpg'),
(49, 'Pipoca para Micro-ondas', 3.30, 220, 'Doces', 'Pipoca de micro-ondas, pronta em poucos minutos.', 'https://example.com/pipoca_microondas.jpg'),
(50, 'Sorvete de Chocolate', 14.90, 80, 'Doces', 'Sorvete cremoso de chocolate para sobremesas irresistíveis.', 'https://example.com/sorvete_chocolate.jpg');

-- --------------------------------------------------------

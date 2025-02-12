-- Banco de dados: `banco_produtos`
CREATE DATABASE IF NOT EXISTS `banco_produtos`;
USE `banco_produtos`;

-- --------------------------------------------------------

-- Estrutura para tabela `produtos`

CREATE TABLE `produtos` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `imagem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Despejando dados para a tabela `produtos`

INSERT INTO `produtos` (`id`, `nome`, `preco`, `quantidade`, `categoria`, `descricao`, `imagem`) VALUES
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
(25, 'Ketchup', 2.50, 200, 'Alimentos', 'Ketchup de tomate, para acompanhar hambúrgueres e fritas.', 'https://example.com/ketchup.jpg');

-- --------------------------------------------------------

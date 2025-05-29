CREATE DATABASE `projetoapoo`

CREATE TABLE `clientefisico` (
  `cod_cliente` int NOT NULL,
  `nome_cliente` varchar(65) DEFAULT NULL,
  `cpf` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `clientefisico` VALUES (1,'cliente teste alteracao','15476537819');

CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL,
  `nome_usuario` varchar(65) DEFAULT NULL,
  `email_usuario` varchar(70) DEFAULT NULL,
  `senha_usuario` varchar(50) DEFAULT NULL,
  `cpf_usuario` varchar(14) DEFAULT NULL,
  `tipo_usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` VALUES (1,'ADM','emailteste@gmail.com','testelogin','12345678901','ADM'),
(2,'Funcionario 1','emailfuncionario@gmail.com','funcionariobunito','128183718381',NULL),
(3,'Cliente','testelogin1@gmail.com','testelogin1','12342678910','Cliente'),
(4,'Funcionario','testelogin2@gmail.com','testelogin2','12345648910','Funcionario');

CREATE TABLE `fornecedor` (
  `cnpj` varchar(20) NOT NULL,
  `nome_fornecedor` varchar(65) DEFAULT NULL,
  `endereco_fornecedor` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`cnpj`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `fornecedor` VALUES ('12131415161718','Loja do seu ze','Rua Severino, Bloco B');

CREATE TABLE `documento` (
  `cod_documento` int NOT NULL,
  `nome_documento` varchar(65) DEFAULT NULL,
  `descricao` varchar(150) DEFAULT NULL,
  `cod_cliente` int DEFAULT NULL,
  `cod_clienteJ` int DEFAULT NULL,
  `id_cate` int DEFAULT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  `arquivo` longblob,
  `caminho_arquivo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`cod_documento`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `cod_clienteJ` (`cod_clienteJ`),
  KEY `id_cate` (`id_cate`),
  KEY `cnpj` (`cnpj`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clientejuridico` (
  `cod_clienteJ` int NOT NULL,
  `nome_clienteJ` varchar(65) DEFAULT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod_clienteJ`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `clientejuridico` VALUES (1,'cliente teste j','122132121'),(2,'Luciano do arrocha','82173817382');

CREATE TABLE `categoria` (
  `id_cate` int NOT NULL,
  `nome_cate` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`id_cate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `categoria` VALUES (1,'Pagamento'),(2,'Boletos Bancarios'),(3,'Teste categoria');

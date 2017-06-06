/*Mapeamento do modelo ER para modelo relacional
	Vendas (*Codigo, Data, Valor_Total, CPF_Vendedor)
	Produtos (*Codigo, Nome, Quant_Estoque, Preço)
	Itens_venda (*Cod_Prod, *Cod_venda, Quantidade)
*/

/*criação do banco*/
CREATE DATABASE Loja;

USE Loja;

/*criação das tabelas*/
CREATE TABLE Vendas
(
	Codigo smallint auto_increment,
	Data date NOT NULL,
	Valor_Total double NOT NULL,
	CPF_Vendedor varchar(11) NOT NULL,
	PRIMARY KEY (codigo)
);

CREATE TABLE Produtos
(
	Codigo smallint auto_increment,
	Nome varchar(128) NOT NULL UNIQUE,
	Quant_Estoque INT UNSIGNED NOT NULL,
	Preco double UNSIGNED NOT NULL,
	PRIMARY KEY (codigo)
);

CREATE TABLE Itens_Venda
(
	Cod_Prod smallint,
	Cod_venda smallint,
	Quantidade int UNSIGNED NOT NULL,
	CONSTRAINT fk_codProd FOREIGN KEY (Cod_Prod) REFERENCES Produtos(codigo) ON UPDATE CASCADE,
	CONSTRAINT fk_codVenda FOREIGN KEY (Cod_venda) REFERENCES Vendas(codigo) ON UPDATE CASCADE
);

/*Inserindo elementos*/
INSERT INTO Produtos(Nome, Quant_Estoque, Preco) VALUES
	('Sensor Utrasonico', 20, 16.50), 
	('Micro Servo Motor 9g SG90', 10, 23.95),
	('Módulo Bluetooth RS232 HC-05', 10, 25.50),
	('Placa Nano V3.0', 15, 80.00);

INSERT INTO Vendas (Data, Valor_Total, CPF_Vendedor) VALUES
	('1995/05/03', 0, 16502334232), 
	('2017/02/20', 0, 23953422342),
	('1995/12/27', 0, 15503422342);

/*Criação do Trigger*/
DROP TRIGGER IF EXISTS TRG_checa_atualiza_estoque;

DROP PROCEDURE IF EXISTS PRC_AtualizaEstoque;
/*Extra para manter a integridade do banco em relação ao Valor 
total de uma venda. Sempre que um novo item é inserido é 
necessário atualizar o preço total da venda*/
DROP PROCEDURE IF EXISTS PRC_AtualizaValorTotal;

DELIMITER $$
CREATE PROCEDURE PRC_AtualizaEstoque (idProduto smallint, quantidade int)
BEGIN
    DECLARE msg VARCHAR(64);
	DECLARE auxQtd int;
	
	SELECT Quant_Estoque
    INTO auxQtd
    FROM Produtos 
    WHERE Codigo = idProduto;
	
	IF (auxQtd >= quantidade) THEN
		UPDATE Produtos
			SET Quant_Estoque = Quant_Estoque - quantidade
			WHERE Codigo = idProduto;
	ELSE
		SET msg = "Quantidade Insuficiente em Estoque!";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE PRC_AtualizaEstoqueRemove (idProduto smallint, quantidade int)
BEGIN
	DECLARE auxQtd int;
	
	SELECT Quant_Estoque
    INTO auxQtd
    FROM Produtos 
    WHERE Codigo = idProduto;
		UPDATE Produtos
			SET Quant_Estoque = Quant_Estoque + quantidade
			WHERE Codigo = idProduto;
END $$
DELIMITER ;

/*Procedimento EXTRA não foi pedido mais é necessário 
para manter a integridade*/
DELIMITER $$
CREATE PROCEDURE PRC_AtualizaValorTotal (idProduto smallint, idVenda smallint, quantidade int)
BEGIN
	DECLARE auxPreco double;

    SELECT Preco
    INTO auxPreco
    FROM Produtos 
    WHERE Codigo = idProduto;
	
	UPDATE Vendas
		SET Valor_Total = Valor_Total + (auxPreco*quantidade)
		WHERE Codigo = idVenda;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE PRC_AtualizaValorTotalRemove (idProduto smallint, idVenda smallint, quantidade int)
BEGIN
	DECLARE auxPreco double;

    SELECT Preco
    INTO auxPreco
    FROM Produtos 
    WHERE Codigo = idProduto;
	
	UPDATE Vendas
		SET Valor_Total = Valor_Total - (auxPreco*quantidade)
		WHERE Codigo = idVenda;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER TRG_checa_atualiza_estoque BEFORE INSERT ON Itens_Venda FOR EACH ROW
BEGIN
	CALL PRC_AtualizaEstoque(new.Cod_Prod, new.Quantidade);
	/*EXTRA esta chamada não foi pedido mas necessário para
	manter a integridade do banco*/
	CALL PRC_AtualizaValorTotal (new.Cod_Prod, new.Cod_venda, new.Quantidade);
END$$ 
DELIMITER ;

DELIMITER $$
CREATE TRIGGER TRG_checa_atualiza_estoqueRemove AFTER DELETE ON Itens_Venda FOR EACH ROW
BEGIN
	CALL PRC_AtualizaEstoqueRemove(old.Cod_Prod, old.Quantidade);
	/*EXTRA esta chamada não foi pedido mas necessário para
	manter a integridade do banco*/
	CALL PRC_AtualizaValorTotal (old.Cod_Prod, old.Cod_venda, old.Quantidade);
END$$ 
DELIMITER ;

/*Procedimentos*/
DELIMITER $$
CREATE PROCEDURE vendas_por_vendedor (CPF VARCHAR(11), dataInicial date, dataFinal date)
BEGIN
DECLARE msg VARCHAR(16);
DECLARE dataAtual date;
	IF ((CPF IS NULL) || (dataInicial IS NULL)) THEN
		SET msg = "Dados Faltando!";
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	ELSE
		IF (dataFinal IS NULL) THEN
			SET dataAtual = CURDATE();
			SELECT count(*) FROM Vendas WHERE CPF_Vendedor = CPF and Data BETWEEN dataInicial AND dataAtual;
		ELSE
			SELECT count(*) FROM Vendas WHERE CPF_Vendedor = CPF and Data BETWEEN dataInicial AND dataFinal;
		END IF;
	END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE insere_produto (nome varchar(128), quant_estoque int, preco double)
BEGIN
	DECLARE msg VARCHAR (128);
	IF ( (nome is NULL ) || (quant_estoque IS NULL) || (preco IS NULL) ) THEN
		SET msg = "Dados para inserção inválidos. Reveja os valores";
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	ELSE
		INSERT INTO Produtos (Nome, Quant_Estoque, Preco) VALUES (nome, quant_estoque, preco);
	END IF;
END $$
DELIMITER ;

/*Testando os trigger*/
	
INSERT INTO Itens_Venda VALUES
	(1, 1, 1),
	(2, 3, 1),
	(3, 2, 1);

/*Testando os procedimentos*/
CALL vendas_por_vendedor (23953422342, '1995-12-20', '1995-12-25');
/*count 0*/
CALL vendas_por_vendedor (23953422342, '1995-12-20', NULL);
/*count 1*/
CALL insere_produto ('Produto Novo', 10, 19.2);
/*a tabela Produtos é afetada (uma nova linha é inserida)*/

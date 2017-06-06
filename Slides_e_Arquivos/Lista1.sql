//Questão 1
CREATE DATABASE loja;

CREATE TABLE vendas(
	codigo int NOT NULL,
	data date NOT NULL,
	valor_total real NOT NULL,
	CPF_vendedor char(11) NOT NULL,
	PRIMARY KEY (codigo)
);

CREATE TABLE produtos(
	codigo int NOT NULL,
	nome varchar(100) unique NOT NULL,
	quant_estoque int NOT NULL,
	preco real NOT NULL,
	check (preco > 0),
	PRIMARY KEY (codigo)
);

CREATE TABLE itens_venda(
	cod_vendas int NOT NULL,
	cod_produtos int NOT NULL,
	quantidade int NOT NULL,
	PRIMARY KEY (cod_vendas, cod_produtos),
	FOREIGN KEY (cod_vendas) REFERENCES vendas(codigo) ON UPDATE CASCADE,
	FOREIGN KEY (cod_produtos) REFERENCES produtos(codigo) ON UPDATE CASCADE
);


//Questão 2
INSERT INTO vendas VALUES (1,'2017-02-14', 155.10, '12315454881');
INSERT INTO vendas VALUES (2,'2017-01-16', 15.15, '62598714535');
INSERT INTO vendas VALUES (3,'2017-02-05', 11.25, '41987778512');

INSERT INTO produtos VALUES (11,'Camiseta Polo', 10, 55.90);
INSERT INTO produtos VALUES (12,'Calça Jeans', 155, 100.50);
INSERT INTO produtos VALUES (13,'Tênis Azul', 16, 159.90);
INSERT INTO produtos VALUES (14,'Meia', 200, 25.00);



//Questão 3

DELIMITER $$
CREATE TRIGGER atualiza_produto AFTER INSERT 
ON itens_venda
FOR EACH ROW 
BEGIN
	IF EXISTS (SELECT * FROM produtos WHERE produtos.quant_estoque < NEW.quantidade AND produtos.codigo = NEW.cod_produtos) THEN
		CALL FAIL('Quantidade insuficiente no estoque');
	ELSE
		UPDATE produtos 
		SET quant_estoque = quant_estoque - NEW.quantidade
		WHERE codigo = NEW.cod_produtos;
	END IF;
END$$
DELIMITER ;

//Testando
INSERT INTO itens_venda VALUES (2,11,20);


//Questão 4
DELIMITER $$
CREATE PROCEDURE vendas_por_vendedor(IN data_Ini date, data_Fin date, cpf char(11))
BEGIN
	IF((data_Ini is NOT NULL) && (cpf is NOT NULL) && data_Fin is NOT NULL) THEN
		SELECT sum(valor_total) FROM vendas v WHERE v.CPF_vendedor = cpf AND v.data BETWEEN data_Ini AND data_Fin;
	ELSE IF((data_Fin is NULL)) THEN
		SET data_Fin = current_date();
		SELECT sum(valor_total) FROM vendas v WHERE v.CPF_vendedor = cpf AND v.data BETWEEN data_Ini AND data_Fin;
	ELSE
		SELECT 'Digite o CPF e a data inicial' AS Msg;
	END IF;
	END IF;
END$$
DELIMITER ;



//Questão 5
DELIMITER $$
CREATE PROCEDURE insere_produto (IN var_codigo int,	var_nome varchar(100), var_quant_estoque int, var_preco real)
BEGIN
	
	IF ((var_codigo is NOT NULL) && (var_nome is NOT NULL) && (var_quant_estoque is NOT NULL) && (var_preco is NOT NULL)) THEN
    	INSERT INTO produtos VALUES (var_codigo, var_nome, var_quant_estoque, var_preco);
    ELSE
    	SELECT 'Não digite valores nulos' AS Msg;
    END IF;
END$$
DELIMITER ;



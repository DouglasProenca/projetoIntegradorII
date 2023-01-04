CREATE DATABASE cr7imports
GO

USE cr7imports
GO

CREATE TABLE rc_user(
    id INT PRIMARY KEY IDENTITY,
    [user] VARCHAR(100) UNIQUE NOT NULL,
    [password] VARCHAR(100) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    mailpassword VARCHAR(100) NOT NULL,
    [data] date not null,
)
GO

CREATE TABLE rc_pais( -- references https://gist.github.com/thiagosilr
    paisId int NOT NULL,
    paisNome VARCHAR(50) NOT NULL,
    paisName VARCHAR(50) NOT NULL
GO

CREATE TABLE rc_marca(
    id INT PRIMARY KEY IDENTITY(10,20),
    marca VARCHAR(100) UNIQUE NOT NULL,
    pais INT NOT NULL,
    [date] DATE NOT NULL,
    [user] INT NOT NULL
)
GO

ALTER TABLE rc_marca ADD CONSTRAINT fk_marca_user FOREIGN KEY ([user]) REFERENCES rc_user (id)
ALTER TABLE rc_marca ADD CONSTRAINT fk_marca_pais FOREIGN KEY ([pais]) REFERENCES rc_pais (paisId)

CREATE TABLE rc_categoria(
    id INT PRIMARY KEY IDENTITY (0,10),
    categoria VARCHAR UNIQUE NOT NULL,
    [data] DATE NOT NULL,
    [user] INT NOT NULL
)
GO

ALTER TABLE rc_categoria ADD CONSTRAINT fk_categoria_user FOREIGN KEY ([user]) REFERENCES rc_user (id)

CREATE TABLE rc_produto(
    id INT PRIMARY KEY IDENTITY,
    nome VARCHAR(200) NOT NULL,
    marca INT NOT NULL,
    valor FLOAT NOT NULL,
    quantidade INT NOT NULL,
    [date] DATE NOT NULL,
    [user] INT NOT NULL,
    categoria INT NOT NULL
)
GO

ALTER TABLE rc_produto ADD CONSTRAINT fk_produto_user FOREIGN KEY ([user]) REFERENCES rc_user (id)
ALTER TABLE rc_produto ADD CONSTRAINT fk_produto_categoria FOREIGN KEY ([categoria]) REFERENCES rc_categoria (id)
ALTER TABLE rc_produto ADD CONSTRAINT fk_produto_marca FOREIGN KEY ([marca]) REFERENCES rc_marca (id)


CREATE TABLE rc_cliente(
    id INT PRIMARY KEY IDENTITY,
    nome VARCHAR(100) NOT NULL,
    cpf char(14) UNIQUE NOT NULL,
    [user] INT NOT NULL,
    [data] DATE NOT NULL,
)
GO

ALTER TABLE rc_cliente ADD CONSTRAINT fk_cliente_user FOREIGN KEY ([user]) REFERENCES rc_user (id)

CREATE TABLE rc_venda(
    id INT PRIMARY KEY IDENTITY(10,100),
    id_cliente INT NOT NULL,
    total FLOAT NOT NULL,
    [data] DATE NOT NULL,
    [user] INT NOT NULL,
)
GO

ALTER TABLE rc_venda ADD CONSTRAINT fk_venda_cliente FOREIGN KEY (id_cliente) REFERENCES rc_cliente(id)
ALTER TABLE rc_venda ADD CONSTRAINT fk_venda_user FOREIGN KEY ([user]) REFERENCES rc_user(id)

CREATE TABLE rc_lista_venda(
    id_venda INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    valor FLOAT NOT NULL,
    [data] DATE NOT NULL,
    [user] INT NOT NULL,
)
GO

ALTER TABLE rc_lista_venda ADD CONSTRAINT fk_listaVenda_venda FOREIGN KEY (id_venda) REFERENCES rc_venda(id)
ALTER TABLE rc_lista_venda ADD CONSTRAINT fk_listaVenda_produto FOREIGN KEY (id_produto) REFERENCES rc_produto(id)  
ALTER TABLE rc_lista_venda ADD CONSTRAINT fk_listaVenda_user FOREIGN KEY ([user]) REFERENCES rc_user(id)

CREATE TABLE rc_endereco_cliente(
    id INT PRIMARY KEY IDENTITY,
    id_cliente INT NOT NULL,
    cep int NOT NULL,
    logradouro VARCHAR(500),
    bairro VARCHAR(500),
    localidade VARCHAR(500),
    uf CHAR(2) NOT NULL,
    numero INT NOT NULL,
    complemento VARCHAR(500),
    [data] DATE NOT NULL,
    [user] INT NOT NULL,
)
GO

ALTER TABLE rc_endereco_cliente ADD CONSTRAINT fk_endereco_cliente FOREIGN KEY (id_cliente) REFERENCES rc_cliente(id)

CREATE PROCEDURE sp_insert_lista_venda (@id_produto int, @quantidade int, @valor float,@user int)
AS
BEGIN

UPDATE rc_produto SET quantidade = quantidade - @quantidade
WHERE id = @id_produto

DECLARE @id_venda INT = (SELECT MAX(id) FROM rc_venda)

INSERT INTO rc_lista_venda VALUES (@id_venda,@id_produto, @quantidade,@valor,GETDATE(),@user)


END
GO
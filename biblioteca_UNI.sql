-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema biblioteca_uni
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca_uni
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca_uni` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `biblioteca_uni` ;

-- -----------------------------------------------------
-- Table `biblioteca_uni`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_uni`.`autor` (
  `id_autor` INT NOT NULL,
  `nome` VARCHAR(20) NULL DEFAULT NULL,
  `nacionalidade` VARCHAR(20) NULL DEFAULT NULL,
  `sexo` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_autor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_uni`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_uni`.`categoria` (
  `cod_categoria` INT NOT NULL,
  `nome_categoria` VARCHAR(40) NOT NULL,
  `descricao` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`cod_categoria`),
  UNIQUE INDEX `nome_categoria` (`nome_categoria` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_uni`.`editora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_uni`.`editora` (
  `id_editora` INT NOT NULL,
  `nome_editora` VARCHAR(40) NOT NULL,
  `endereco` VARCHAR(60) NULL DEFAULT NULL,
  `contato` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id_editora`),
  UNIQUE INDEX `nome_editora` (`nome_editora` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_uni`.`livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_uni`.`livro` (
  `ISBN` INT NOT NULL,
  `titulo` VARCHAR(40) NULL DEFAULT NULL,
  `ano` VARCHAR(4) NULL DEFAULT NULL,
  `cod_categoria` INT NOT NULL,
  `id_editoria` INT NOT NULL,
  PRIMARY KEY (`ISBN`),
  INDEX `cod_categoria_idx` (`cod_categoria` ASC) VISIBLE,
  INDEX `id_editora_idx` (`id_editoria` ASC) VISIBLE,
  CONSTRAINT `cod_categoria`
    FOREIGN KEY (`cod_categoria`)
    REFERENCES `biblioteca_uni`.`categoria` (`cod_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_editora`
    FOREIGN KEY (`id_editoria`)
    REFERENCES `biblioteca_uni`.`editora` (`id_editora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_uni`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_uni`.`usuario` (
  `CPF` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(40) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `data_nascimento` DATE NOT NULL,
  `endereco` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`CPF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_uni`.`emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_uni`.`emprestimo` (
  `cod_emprestimo` INT NOT NULL,
  `data_emprestimo` DATE NOT NULL,
  `data_devolucao` DATE NOT NULL,
  `ISBN` INT NOT NULL,
  `CPF` VARCHAR(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cod_emprestimo`, `ISBN`),
  INDEX `ISBN_idx` (`ISBN` ASC) VISIBLE,
  INDEX `CPF_idx` (`CPF` ASC) VISIBLE,
  CONSTRAINT `ISBN`
    FOREIGN KEY (`ISBN`)
    REFERENCES `biblioteca_uni`.`livro` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CPF`
    FOREIGN KEY (`CPF`)
    REFERENCES `biblioteca_uni`.`usuario` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_uni`.`livro_autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_uni`.`livro_autor` (
  `ISBN` INT NOT NULL,
  `id_autor` INT NOT NULL,
  INDEX `id_autor_idx` (`id_autor` ASC) VISIBLE,
  CONSTRAINT `ISBN_LA`
    FOREIGN KEY (`ISBN`)
    REFERENCES `biblioteca_uni`.`livro` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_autor_LA`
    FOREIGN KEY (`id_autor`)
    REFERENCES `biblioteca_uni`.`autor` (`id_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

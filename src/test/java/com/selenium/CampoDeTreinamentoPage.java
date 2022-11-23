package com.selenium;

import org.openqa.selenium.WebDriver;

public class CampoDeTreinamentoPage {

    public DSL dsl;

    public CampoDeTreinamentoPage(WebDriver driver){
        dsl = new DSL(driver);
    }

    public void setNome(String nome){
        dsl.escrever("elementosForm:nome", nome);

    }
    public void setSobreNome(String sobrenome){
        dsl.escrever("elementosForm:nome", sobrenome);

    }
    public void setTexto(String texto){
        dsl.escrever("elementosForm:sugestoes",texto);
    }
    public void setSexoMasculino(){
        dsl.clicarRadio("elementosForm:sexo:0");
    }
    public void setComidaPizza(){
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
    }
    public void setEscolaridade(String valor){
        dsl.selecionarCombo("elementosForm:escolaridade", valor);

    }
    public void setEsportes(String valor){
        dsl.selecionarCombo("elementosForm:esportes",valor);

    }
    public void cadastrar(){
        dsl.clicarBotao("elementosForm:cadastrar");
    }
    public String obterResultadoCadastro(){
        return  dsl.obterTexto("resultado");
    }
    public String nomeCadastro(){
        return  dsl.obterTexto("descNome");
    }
    public String sobreNomeCadastro(){
        return  dsl.obterTexto("descSobrenome");
    }
    public void setSexoFeminino(){
      dsl.clicarRadio("elementosForm:sexo:1");

    }
}

package com.selenium;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TestarPaginaSelenium {
    static String site = "file:///C:/Users/ThiagoPaesLandimLima/Desktop/componentes.html";
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.get(site);
        driver.manage().window().maximize();
        dsl = new DSL(driver);
    }

    public void finaliza() {
        driver.quit();
    }

    @Test
    public void enviarInfoParaCampos() {

        dsl.escreve("elementosForm:nome", "nome qualquer");
        dsl.escreve("elementosForm:sobrenome", "SobrenomeQualquer");

        Assert.assertEquals("nome qualquer", dsl.obterValorCampo("elementosForm:nome"));
        Assert.assertEquals("SobrenomeQualquer", dsl.obterValorCampo("elementosForm:sobrenome"));
    }

    @Test
    public void deveInteragirComRadioButtom() {

        dsl.clicarCampos("elementosForm:sexo:0");
        Assert.assertTrue(dsl.radioIsMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComComboBox() {

        dsl.selecionarCombo("elementosForm:escolaridade", "mestrado");
        Assert.assertEquals("mestrado", dsl.obterValorCampo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresCombo() {

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }

    @Test
    public void deveVerificarSelecionarCombo() {

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Corrida");

        List<WebElement> allselectOption = combo.getAllSelectedOptions();
        Assert.assertEquals(1, allselectOption.size());


    }

    @Test
    public void clickButton() {

        driver.findElement(By.id("buttonSimple")).click();

    }
}



package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
    private final WebDriver driver;
    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String id_campo, String texto){
        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public String obterValorCampo(String id_campo){
        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }
    public void clicarCampos(String id){
         driver.findElement(By.id(id)).click();
    }
    public  boolean radioIsMarcado(String id){
        return driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
    }
    public void selecionarCombo(String id, String valor){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);

       // combo.selectByIndex(Integer.parseInt(valor));
        combo.selectByValue(valor);
    }
    public String obterValorCombo(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();


    }
}

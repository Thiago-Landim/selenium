package com.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TestarPaginaSelenium {
    static WebDriver driver = new ChromeDriver();
    static  String  site = "file:///C:/Users/ThiagoPaesLandimLima/Desktop/componentes.html";
    public static void main(String[] args) {

    }

    @Test
    public  void enviarInfoParaCampos(){

        driver.get(site);
        driver.findElement(By.id("elementosForm:nome")).sendKeys("teste");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("teste2");

        Assert.assertEquals("teste", driver.findElement(By.id(
                "elementosForm:nome"
        )).getAttribute("value"));

        Assert.assertEquals("teste2", driver.findElement(By.id(
                "elementosForm:sobrenome"
        )).getAttribute("value"));
    }

    @Test
    public void deveInteragirComRadioButtom(){
        driver.get(site);
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void deveInteragirComComboBox(){
        driver.get(site);
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);


        combo.selectByIndex(6);
       // combo.selectByValue("mestrado");
       // combo.getFirstSelectedOption();

        Assert.assertEquals("Mestrado",combo.getFirstSelectedOption().getText());

    }

    @Test
    public void deveVerificarValoresCombo(){
        driver.get(site);
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for(WebElement option: options) {
            if(option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }



    }



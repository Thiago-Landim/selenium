package com.selenium;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;

	@Before
	public void inicializa(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/ThiagoPaesLandimLima/Desktop/componentes.html");
		dsl = new DSL(driver);
		page = new CampoDeTreinamentoPage(driver);
	}

	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void testeTextField(){
		page.setNome("Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void testTextFieldDuplo(){
		page.setNome("Thiago");
		Assert.assertEquals("Thiago", dsl.obterValorCampo("elementosForm:nome"));
		page.setSobreNome("Paes Landim");
		Assert.assertEquals("Paes Landim", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void deveIntegarirComTextArea(){
		page.setTexto("Deu certo");
		Assert.assertEquals("Deu certo", dsl.obterValorCampo("elementosForm:sugestoes"));
	}

	@Test
	public void deveIntegarirComRadioButton(){
		page.setSexoMasculino();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void deveIntegarirComCheckbox(){
		page.setComidaPizza();
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void deveIntegarirComCombo(){
		page.setEscolaridade("2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

	@Test
	public void deveVerificarValoresCombo(){
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}

	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());

		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}



	@Test
	public void deveinteragirComLinks(){
		dsl.clicarLink("Voltar");

		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void deveBuscarTextosNaPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				dsl.obterTexto(By.className("facilAchar")));
	}

}



package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pages.CadastroPage;

import static org.junit.jupiter.api.Assertions.*;

public class CadastroTest {
    private WebDriver driver;
    private CadastroPage cadastroPage;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        cadastroPage = new CadastroPage(driver);
        faker = new Faker();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCadastrarComDadosValidos() {
        cadastroPage.preencherNome("José da Silva");
        cadastroPage.preencherEmail("jose@email.com");
        cadastroPage.preencherTelefone("(11) 98765-4321");
        cadastroPage.clicarCadastrar();

        assertTrue(cadastroPage.obterMensagemSucesso().contains("sucesso"));
    }

    @Test
    public void testCadastrarComFaker() {
        cadastroPage.preencherNome(faker.name().fullName());
        cadastroPage.preencherEmail(faker.internet().emailAddress());
        cadastroPage.preencherTelefone("(11) 99999-9999"); // exemplo com formato fixo
        cadastroPage.clicarCadastrar();

        assertTrue(cadastroPage.obterMensagemSucesso().contains("sucesso"));
    }

}

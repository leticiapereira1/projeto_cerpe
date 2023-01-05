package robots.cerpe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ResultExamsCerpe {

    public WebDriver roboDriver;


    public ResultExamsCerpe(WebDriver roboDriver) {
        this.roboDriver = roboDriver;
    }

    public void acessSite() {


        roboDriver.get("https://nav.dasa.com.br/login?mvj=MTUwNTE5NDQ");
        roboDriver.manage().window().maximize();
        roboDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    public void loginSite() throws InterruptedException {

        roboDriver.findElement(By.cssSelector
                ("#custom-route-container > div.Container-lsw_auth__sc-ge812e-0.enUgdD > main > section > div > div.StyledForm-lsw_auth__sc-1dw59sn-0.kQGxvw > form > div.form__login > div:nth-child(1) > input")).sendKeys("6171688055");

        roboDriver.findElement(By.cssSelector
                ("#custom-route-container > div.Container-lsw_auth__sc-ge812e-0.enUgdD > main > section > div > div.StyledForm-lsw_auth__sc-1dw59sn-0.kQGxvw > form > div.form__login > div:nth-child(2) > input")).sendKeys("A40209");

        roboDriver.findElement(By.cssSelector
                ("#custom-route-container > div.Container-lsw_auth__sc-ge812e-0.enUgdD > main > section > div > div.StyledForm-lsw_auth__sc-1dw59sn-0.kQGxvw > form > div.form__login > div:nth-child(3) > input")).sendKeys("09/10/1972");

        Thread.sleep(5000);

        roboDriver.findElement
                (By.cssSelector("#custom-route-container > div.Container-lsw_auth__sc-ge812e-0.enUgdD > main > section > div > div.StyledForm-lsw_auth__sc-1dw59sn-0.kQGxvw > form > div.form__login > button")).click();

        roboDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


    }
    public void searchExamsCerpe() throws InterruptedException {

        //acessando local onde ficam os exames
        roboDriver.findElement(By.cssSelector
                ("#root > div > div.SideBarContent-lsw_container__sc-1cuma7b-3.fObGhR.css-3hl09r > div > div:nth-child(5) > a:nth-child(2) > span")).click();


        //selecionando ano dos exames realizados e baixando

        roboDriver.findElement(By.cssSelector("#wrapperList > div > div.sc-iNGGcK.rpZhb.css-wjlxvr > div.sc-iNGGcK.jqbjxB.css-zaboiu > div.sc-iNGGcK.DivButtonsActions-lsw_exames__vgnw1s-0.lgPwMW.css-vurnku > button:nth-child(5)")).click();
        Thread.sleep(5000);

        String label = roboDriver.findElement(By.cssSelector("#chipBadge > button > p.sc-jeraig.efEgaS")).getText();
        label = label.toLowerCase();


        //arquivo a ser movido da pasta temp para a pasta final

        File arquivos = new File("C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp_cerpe\\Laudo Completo 19_02_2021.pdf");
        File dir = new File("C:\\ExamesLaboratoriais_cerpe");
        boolean ok = arquivos.renameTo(new File(dir, label + "_" + arquivos.getName()));// move o arquivo para o novo diretório
        if (ok)

           try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        File folder = new File("C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp_cerpe\\Laudo Completo 19_02_2021.pdf");
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
            Thread.sleep(5000);
        }

            roboDriver.quit();

        }


    public ResultExamsCerpe() throws IOException {

            System.setProperty("chromedriver", "C:\\Users\\leticia.pereira\\drivers");

            //modificar o diretório padrão da pasta downloads para outra pasta
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();

            // criação da pasta 'temp'
            File file = new File("C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp_cerpe");
            file.mkdir();

            //criar pasta final que ficarão salvos os arquivos
            file = new File("C:\\ExamesLaboratoriais_cerpe");
            file.mkdir();

            //salvamento dos arquivos da pasta temp
            prefs.put("download.default_directory", "C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp_cerpe");
            //File temp = File.createTempFile("arquivo-temporario", ".tmp");
            //System.out.println("Temp file: " + temp.getAbsolutePath());

            //passar arquivos da temp para a pasta definitiva e renomear os arquivos
            options.setExperimentalOption("prefs", prefs);

  /*      // arquivo a ser movido
        File arquivos = new File("C:\Users\leticia.pereira\AppData\Local\Temp\LabTemp");
        File dir = new File("C:\\ExamesLaboratoriais");
        boolean ok = arquivos.renameTo(new File(dir, arquivos.getName()));// move o arquivo para o novo diretório
        if (ok)

       //renomeia o arquivo na pasta final

            dir = new File("C:\\ExamesLaboratoriais");
            arquivos = new File(dir, "laudo.pdf");
            File arquivo2 = new File(dir, "laudo_teste.pdf");
            boolean statusRename = arquivos.renameTo(arquivo2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        File folder = new File("C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp");
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }

        }

   */

            //o que estiver aqui será executado no new (classe executora)
            WebDriverManager.chromedriver().setup();
            roboDriver = new ChromeDriver(options);
//return roboDriver;
        }

}

package robots.unimed.recife;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.directory;

public class ResultExams {


    public WebDriver roboDriver;


    public ResultExams(WebDriver roboDriver) {
        this.roboDriver = roboDriver;
    }

    public void acessSite() {


        roboDriver.get("https://laudos.unimedrecife.com.br:8443/portal-laudos/#/login/");
        roboDriver.manage().window().maximize();
        roboDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }


    public void loginSite() {

        roboDriver.findElement(By.cssSelector("input.form-control:nth-child(4)")).sendKeys("1841537");

        roboDriver.findElement(By.cssSelector("input.form-control:nth-child(5)")).sendKeys("767348");

        roboDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[2]/div/div/div[2]/button")).click();


    }


    public void searchExams() throws InterruptedException {
        //campo data inicial - limpar a data/
        roboDriver.findElement(By.cssSelector("div.col-md-3:nth-child(1) > div:nth-child(2) > span:nth-child(2) > button:nth-child(1) > i:nth-child(1)")).click();
        roboDriver.findElement(By.cssSelector(".btn-danger")).click();

        //campo data final - limpar a data
        roboDriver.findElement(By.cssSelector(".page-header > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > span:nth-child(2) > button:nth-child(1) > i:nth-child(1)")).click();
        roboDriver.findElement(By.cssSelector(".btn-danger")).click();

        //clicar no botão 'pesquisar'
        roboDriver.findElement(By.cssSelector("button.pull-right")).click();

        //clicar nos exames localizados na página


        List<WebElement> lista = roboDriver.findElements(By.cssSelector("body > div > div.container > div > div > div.col-md-9.col-sm-8 > div.slide-top > div.slide-top > ul > li"));

        for (int i = 0; i < lista.size(); i++) {
            String label = roboDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div[2]/div[3]/ul/li["+(i+1)+"]/div/h5/a")).getText();
            label = label.replace(" ", "");
            label = label.replace("-", "_");
            label = label.replace(":", "");
            label = label.toLowerCase();
            roboDriver.findElement(By.cssSelector("body > div > div.container > div > div > div.col-md-9.col-sm-8 > div.slide-top > div.slide-top > ul > li:nth-child(" + (i + 1) + ") > div > h5 > a")).click();
            roboDriver.findElement(By.cssSelector("body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button:nth-child(2)")).click();
            roboDriver.findElement(By.cssSelector("body > div.modal.fade.ng-isolate-scope.in > div > div > div.modal-footer.ng-scope > button.btn.btn-error.ng-binding")).click();
            Thread.sleep(5000);

            // arquivo a ser movido
            File arquivos = new File("C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp\\laudo.pdf");

            //pasta final
            File dir = new File("C:\\ExamesLaboratoriais");
            boolean ok = arquivos.renameTo(new File(dir, label + "_" + arquivos.getName() + ".pdf"));// move o arquivo para o novo diretório
            if (ok);
            Thread.sleep(5000);

        }

        roboDriver.quit();
    }


    public ResultExams() throws IOException {
        System.setProperty("chromedriver", "C:\\Users\\leticia.pereira\\drivers");

        //modificar o diretório padrão da pasta downloads para outra pasta
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();

        // criação da pasta 'temp'
        File file = new File("C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp");
        file.mkdir();

        //criar pasta final que ficarão salvos os arquivos
        file = new File("C:\\ExamesLaboratoriais");
        file.mkdir();

        //salvamento dos arquivos da pasta temp
        prefs.put("download.default_directory", "C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp");
        //File temp = File.createTempFile("arquivo-temporario", ".tmp");
        //System.out.println("Temp file: " + temp.getAbsolutePath());

        //passar arquivos da temp para a pasta definitiva e renomear os arquivos
        options.setExperimentalOption("prefs", prefs);

  /*      // arquivo a ser movido
        File arquivos = new File("C:\\Users\\leticia.pereira\\AppData\\Local\\Temp\\LabTemp\\laudo.pdf");
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




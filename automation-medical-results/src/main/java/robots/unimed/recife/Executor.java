package robots.unimed.recife;


import java.awt.*;
import java.io.IOException;

public class Executor {
    public static void main(String[] args) throws AWTException, IOException {
        ResultExams resultExams = new ResultExams();



        resultExams.acessSite();
        resultExams.loginSite();
        try {
            resultExams.searchExams();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        

}}



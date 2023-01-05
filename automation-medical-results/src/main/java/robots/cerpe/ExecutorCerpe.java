package robots.cerpe;


import java.awt.*;
import java.io.IOException;

public class ExecutorCerpe {

        public static void main(String[] args) throws AWTException, IOException {
            ResultExamsCerpe resultExamsCerpe = new ResultExamsCerpe();



            resultExamsCerpe.acessSite();
            try {
                resultExamsCerpe.loginSite();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                resultExamsCerpe.searchExamsCerpe();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }





        }


}

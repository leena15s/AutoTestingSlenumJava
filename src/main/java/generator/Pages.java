package generator;

import org.openqa.selenium.WebDriver;


    public abstract class Pages {
        public final WebDriver driver;
      public final  actAide aide;

        public Pages(WebDriver driver, actAide aide) {
            this.driver = driver;
            this.aide = aide;
        }
    }


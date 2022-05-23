import java.util.logging.Logger;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.By.*;

public class OzdilekApp extends OzdilekBaseTest {
    final static Logger logger= Logger.getLogger((OzdilekApp.class.getName()));

    @Step("<second> kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
        logger.info("Belirtilen saniye kadar bekletildi");
    }

    @Step("<Key> İd'li elemente tıkla")
    public void clickElementByid(String Key) {
        appiumDriver.findElement(id(Key)).click();
        logger.info("idli elemente tıklama başarılı");
    }
    @Step("<Key> İd'li elemente 2 kez tıkla")
    public void clickElementBackButton(String Key) {
        for(int i=0;i<2;i++){
        appiumDriver.findElement(id(Key)).click();
            logger.info("idli elemente 2 kez tıklama başarılı");
    }}
    @Step("<xpath> xpathli elemente tıkla")
    public void clickElementXpath(String xpath){
        appiumDriver.findElement(xpath(xpath)).click();
    }
    @Step("<Key> İd'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByid(String Key, String keywordc) {
        appiumDriver.findElement(id(Key)).sendKeys(keywordc);
        logger.info("Keyword degeri eklenildi.");
    }
    public List<MobileElement> listElements() {
        return appiumDriver.findElements(xpath("//*[@class='androidx.cardview.widget.CardView']"));
    }
    @Step("Rastgele ürün seç ve <second> saniye bekle")
    public void randomClick(int second) throws InterruptedException {
        Random random = new Random();
        listElements().get(random.nextInt(listElements().size())).click();;
        Thread.sleep(1000*second);
        logger.info("Rastgele urun secimi yapildi ve belirtilen sn kadar bekletildi.");

    }
    @Step("Elementi <key> bul ve <keyword> değerini kontrol et")
    public void dogrula(String key, String keyword){
        System.out.println("Text değeri " + appiumDriver.findElement(id(key)).getText());
        Assert.assertTrue("Text değeri bulunmamadı " ,appiumDriver.findElement(id(key)).getText().equals(keyword));
    }
    @Step("Sayfayı yukarı kaydır")
    public void swipeUpI() {
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Boyutu " + dims);
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder = 5;
        final int PRESS_TIME = 200; // ms
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("pointOptionStart " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        System.out.println("pointOptionEnd " + pointOptionEnd);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }

}

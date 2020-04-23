//package ru.stqa.litecart.tests;
//
//import org.jetbrains.annotations.NotNull;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.Color;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import ru.stqa.litecart.BaseTest;
//
//import java.util.List;
//
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;
//
//public class LitecartCustomersModeTest extends BaseTest {
//
//    private String regularPriceColor;
//    private String regularPriceColorHex;
//    private String regularPriceDecor;
//    private String salePriceColor;
//    private String salePriceColorHex;
//    private Integer salePriceBoldValue;
//    private Double regularPriceSize;
//    private Double salePriceSize;
//
//    @BeforeMethod
//    public void mainPage() {
//        openMainPage();
//    }
//
//    @Test(description = "Задание 8. Сделайте сценарий, проверяющий наличие стикеров у товаров")
//    public void testOneGoodOneSticker() {
//        List<WebElement> goods = driver.findElements(By.className("product"));
//        for (WebElement good : goods) {
//            int stickerSize = good.findElements(By.className("sticker")).size();
//            assertEquals(stickerSize, 1);
//        }
//    }
//
//    @Test(description = "Задание 10. Проверить, что открывается правильная страница товара")
//    public void testGoodPageOpensRight() {
//        String goodNameMain = getText("#box-campaigns .name");
//        String regularPriceMain = getText("#box-campaigns .regular-price");
//        String salePriceMain = getText("#box-campaigns .campaign-price");
//
//        driver.findElement(By.cssSelector("#box-campaigns a")).click();
//
//        String goodName = getText("#box-product .title");
//        String regularPrice = getText("#box-product .regular-price");
//        String salePrice = getText("#box-product .campaign-price");
//
//        assertEquals(goodNameMain, goodName);
//        assertEquals(regularPriceMain, regularPrice);
//        assertEquals(salePriceMain, salePrice);
//    }
//
//    @Test(description = "Задание 10. Проверить, что оформление текста на главной странице соответствует ожидаемому")
//    public void testMainPageFonts() {
//        regularPriceColor = getAttributeValue("#box-campaigns .regular-price", "color");
//        String[] regularPriceColors = convertColorValueToStringArray(regularPriceColor);
//
//        assertEquals(regularPriceColors[0], regularPriceColors[1]);
//        assertEquals(regularPriceColors[1], regularPriceColors[2]);
//
//        regularPriceDecor = getAttributeValue("#box-campaigns .regular-price", "text-decoration")
//                .substring(0, 12);
//        assertEquals(regularPriceDecor, "line-through");
//
//        salePriceColor = getAttributeValue("#box-campaigns .campaign-price", "color");
//        String[] salePriceColors = convertColorValueToStringArray(salePriceColor);
//
//        assertEquals(salePriceColors[1], "0");
//        assertEquals(salePriceColors[2], "0");
//
//        salePriceBoldValue = Integer.valueOf(getAttributeValue("#box-campaigns .campaign-price", "font-weight"));
//        assertTrue(salePriceBoldValue > 600);
//
//        regularPriceSize = getFontSize("#box-campaigns .regular-price");
//        salePriceSize = getFontSize("#box-campaigns .campaign-price");
//        assertTrue(regularPriceSize < salePriceSize);
//
//    }
//
//    @Test(description = "Задание 10. Проверить, что оформление текста на странице товара соответствует ожидаемому")
//    public void testGoodPageFonts() {
//        driver.findElement(By.cssSelector("#box-campaigns a")).click();
//
//        regularPriceColor = getAttributeValue(".regular-price", "color");
//        String[] regularPriceColors = convertColorValueToStringArray(regularPriceColor);
//
//        assertEquals(regularPriceColors[0], regularPriceColors[1]);
//        assertEquals(regularPriceColors[1], regularPriceColors[2]);
//
//        regularPriceDecor = getAttributeValue(".regular-price", "text-decoration")
//                .substring(0, 12);
//        assertEquals(regularPriceDecor, "line-through");
//
//        salePriceColor = getAttributeValue(".campaign-price", "color");
//        String[] salePriceColors = convertColorValueToStringArray(salePriceColor);
//
//        assertEquals(salePriceColors[1], "0");
//        assertEquals(salePriceColors[2], "0");
//
//        salePriceBoldValue = Integer.valueOf(getAttributeValue(".campaign-price", "font-weight"));
//        assertTrue(salePriceBoldValue > 600);
//
//        regularPriceSize = getFontSize(".regular-price");
//        salePriceSize = getFontSize(".campaign-price");
//        assertTrue(regularPriceSize < salePriceSize);
//    }
//
//    @NotNull
//    private String[] convertColorValueToStringArray(String salePriceColor) {
//        return Color.fromString(salePriceColor).asRgb()
//                .replace("rgb(", "")
//                .replace(")", "")
//                .split(", ");
//    }
//}

package com.myCompany.SwagLabs.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class HomePage {
     WebDriver driver;
     CartPage cartPage;

    // Elemen-elemen halaman yang diidentifikasi menggunakan anotasi @FindBy
    @FindBy(css = ".inventory_item_name") // Daftar nama produk di halaman
    private List<WebElement> list_productName;

    @FindBy(css = ".btn.btn_inventory") // Tombol 'Add to Cart' untuk setiap produk
    private List<WebElement> list_btnAddtochart;

    @FindBy(css = ".app_logo") // Header teks di halaman beranda
    private WebElement headerText_Home;

    @FindBy(css = ".shopping_cart_link") // Tombol untuk membuka halaman keranjang belanja
    private WebElement btn_cart;

    @FindBy(css = ".shopping_cart_badge") // Elemen yang menunjukkan jumlah produk di keranjang
    private WebElement span_CountProductCart;

    @FindBy(id = "react-burger-menu-btn") // Tombol menu burger di header
    private WebElement btn_burgerMenuHeader;

    @FindBy(id = "logout_sidebar_link") // Tautan untuk logout dari menu sidebar
    private WebElement a_Logout;

    // Konstruktor untuk menginisialisasi elemen menggunakan PageFactory
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Mendapatkan teks dari header halaman dashboard
    public String getHomeHeaderTextDashboard() {
        return headerText_Home.getText();
    }

    // Menambahkan produk ke keranjang berdasarkan nama produk
    public void addProducttoCartbyName(String[] itemsNeeded) {
        List ItemsNeededList = Arrays.asList(itemsNeeded);
        int j = 0;
        for (int i = 0; i < list_productName.size(); i++) {
            if (ItemsNeededList.contains(list_productName.get(i).getText())) {
                j++; // Menambah hitungan produk yang sudah ditemukan
                list_btnAddtochart.get(i).click(); // Klik tombol 'Add to Cart'
                if (j == itemsNeeded.length) {
                    break; // Berhenti jika semua produk telah ditambahkan
                }
            }
        }
    }

    // Mendapatkan jumlah produk yang ada di keranjang
    public String getCountProductCart() {
        return span_CountProductCart.getText();
    }

    // Menghapus produk dari keranjang berdasarkan nama produk
    public void removeProductFormCartbyName(String[] itemsNeeded) {
        List ItemsNeededList = Arrays.asList(itemsNeeded);
        int j = 0;
        for (int i = 0; i < list_productName.size(); i++) {
            if (ItemsNeededList.contains(list_productName.get(i).getText())) {
                j++; // Menambah hitungan produk yang sudah ditemukan
                list_btnAddtochart.get(i).click(); // Klik tombol 'Remove' dari keranjang
                if (j == itemsNeeded.length) {
                    break; // Berhenti jika semua produk telah dihapus
                }
            }
        }
    }

    // Klik tombol menu burger pada header
    public void click_btnburgerMenuHeader() {
        btn_burgerMenuHeader.click();
    }

    // Klik tautan logout dari menu sidebar
    public void click_a_Logout() {
        a_Logout.click();
    }

    // Logout menggunakan tombol menu burger dan tautan logout
    public void goLogout() {
        btn_burgerMenuHeader.click(); // Buka menu burger
        a_Logout.click(); // Klik tautan logout
    }

    // Navigasi ke halaman keranjang belanja
    public CartPage goToCart() {
        btn_cart.click(); // Klik tombol keranjang
        return cartPage = new CartPage(driver); // Mengembalikan instance CartPage
    }
}

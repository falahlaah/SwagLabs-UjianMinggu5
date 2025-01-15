package com.myCompany.SwagLabs.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartPage {
    WebDriver driver;


    // Elemen-elemen halaman yang diidentifikasi menggunakan anotasi @FindBy
    @FindBy(css = ".inventory_item_name") // Daftar nama produk dalam keranjang
            List<WebElement> list_ProductCart;

    @FindBy(css = ".btn.cart_button") // Daftar tombol "Remove" untuk menghapus produk
    private List<WebElement> list_btnRemoveProduct;

    @FindBy(id = "checkout") // Tombol "Checkout" untuk melanjutkan ke halaman checkout
    WebElement btn_checkOut;

    // Konstruktor untuk menginisialisasi elemen halaman menggunakan PageFactory
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Mendapatkan daftar nama produk yang ada di dalam keranjang
    public List<String> getProductNameinCart() {
        List<String> IteminCart = new ArrayList<>(); // List untuk menyimpan nama produk
        for (int i = 0; i < list_ProductCart.size(); i++) {
            String productName = list_ProductCart.get(i).getText(); // Mendapatkan teks nama produk
            IteminCart.add(productName); // Menambahkan nama produk ke list
        }
        return IteminCart; // Mengembalikan list nama produk
    }

    // Menghapus produk dari keranjang berdasarkan nama
    public void removeProductFormCartbyName(String[] itemsRemove) {
        List ItemsRemoveList = Arrays.asList(itemsRemove); // Konversi array produk yang akan dihapus menjadi list
        int j = 0; // Counter untuk produk yang berhasil dihapus
        for (int i = 0; i < list_ProductCart.size(); i++) {
            if (ItemsRemoveList.contains(list_ProductCart.get(i).getText())) { // Periksa apakah produk ada di list untuk dihapus
                j++;
                list_btnRemoveProduct.get(i).click(); // Klik tombol "Remove" untuk menghapus produk
                if (j == itemsRemove.length) { // Hentikan jika semua produk yang dihapus telah ditemukan
                    break;
                }
            }
        }
    }

    // Mengklik tombol "Checkout" untuk melanjutkan ke halaman checkout
    public CheckOutPage goToCheckOut() {
        btn_checkOut.click(); // Klik tombol "Checkout"
        CheckOutPage checkOutPage = new CheckOutPage(driver); // Buat instance halaman checkout
        return checkOutPage; // Kembalikan instance halaman checkout
    }
}

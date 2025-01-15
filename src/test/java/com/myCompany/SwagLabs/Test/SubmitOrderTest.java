package com.myCompany.SwagLabs.Test;

import com.myCompany.SwagLabs.PageObjects.CartPage;
import com.myCompany.SwagLabs.PageObjects.CheckOutPage;
import com.myCompany.SwagLabs.PageObjects.HomePage;
import com.myCompany.SwagLabs.TestComponent.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    // Array produk yang dibutuhkan untuk pengujian
    String[] productNeeded = {"Sauce Labs Backpack", "Sauce Labs Bike Light"};

    // Pengujian validasi penambahan produk ke keranjang berhasil
    @Test
    public void TC1_ValidateAddtoCartSuccessful() {
        HomePage homePage = loginPage.validLogin("standard_user", "secret_sauce"); // Login dengan kredensial valid
        homePage.addProducttoCartbyName(productNeeded); // Tambahkan produk ke keranjang
        Assert.assertEquals(homePage.getCountProductCart(), "2"); // Verifikasi jumlah produk di keranjang
    }

    // Pengujian validasi produk yang ditambahkan ke keranjang
    @Test(dependsOnMethods = {"TC1_ValidateAddtoCartSuccessful"})
    public void TC2_ValidateProductAddedtoCart() {
        List<String> listProduct = new ArrayList<>(Arrays.asList(productNeeded)); // Konversi array produk ke list
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.goToCart(); // Akses halaman keranjang
        Assert.assertEquals(cartPage.getProductNameinCart(), listProduct); // Verifikasi produk dalam keranjang
    }

    // Validasi gagal checkout dengan semua data kosong
    @Test(dependsOnMethods = {"TC2_ValidateProductAddedtoCart"})
    public void TC3_ValidateCheckoutUNsuccessfullWithAllDataNull() {
        CartPage cartPage = new CartPage(driver);
        CheckOutPage checkOutPage = cartPage.goToCheckOut(); // Akses halaman checkout
        checkOutPage.clickbtnContinue(); // Klik tombol "Continue"
        Assert.assertEquals(checkOutPage.getErrorMessage(), "Error: First Name is required"); // Verifikasi pesan kesalahan
    }

    // Validasi gagal checkout dengan nama depan kosong
    @Test(dependsOnMethods = {"TC2_ValidateProductAddedtoCart"})
    public void TC4_ValidateCheckoutUNsuccessfullWithFirstNameNull() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.enterLastName("pekerti"); // Isi nama belakang
        checkOutPage.enterPostalCode("12345"); // Isi kode pos
        checkOutPage.clickbtnContinue(); // Klik tombol "Continue"
        Assert.assertEquals(checkOutPage.getErrorMessage(), "Error: First Name is required"); // Verifikasi pesan kesalahan
    }

    // Validasi gagal checkout dengan nama belakang kosong
    @Test(dependsOnMethods = {"TC2_ValidateProductAddedtoCart"})
    public void TC5_ValidateCheckoutUNsuccessfullWithLastNameNull() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.clearAlldataCheckout(); // Hapus semua data input
        checkOutPage.enterFirstName("Budi"); // Isi nama depan
        checkOutPage.enterPostalCode("12345"); // Isi kode pos
        checkOutPage.clickbtnContinue(); // Klik tombol "Continue"
        Assert.assertEquals(checkOutPage.getErrorMessage(), "Error: Last Name is required"); // Verifikasi pesan kesalahan
    }

    // Validasi gagal checkout dengan kode pos kosong
    @Test(dependsOnMethods = {"TC2_ValidateProductAddedtoCart"})
    public void TC6_ValidateCheckoutUNsuccessfullWithPostalCodeNull() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.clearAlldataCheckout(); // Hapus semua data input
        checkOutPage.enterFirstName("Budi"); // Isi nama depan
        checkOutPage.enterLastName("Pekerti"); // Isi nama belakang
        checkOutPage.clickbtnContinue(); // Klik tombol "Continue"
        Assert.assertEquals(checkOutPage.getErrorMessage(), "Error: Postal Code is required"); // Verifikasi pesan kesalahan
    }

    // Validasi checkout berhasil dengan data valid
    @Test(dependsOnMethods = {"TC2_ValidateProductAddedtoCart"})
    public void TC7_ValidateCheckoutSuccessfullwithValidData() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.clearAlldataCheckout(); // Hapus semua data input
        checkOutPage.validDataCheckout("Budi", "Pekerti", "123456"); // Masukkan data valid
        checkOutPage.clickbtnFinish(); // Klik tombol "Finish"
        Assert.assertEquals(checkOutPage.getConfirmationOrder(), "Thank you for your order!"); // Verifikasi pesan konfirmasi
    }

    // Validasi penghapusan produk dari keranjang berhasil
    @Test(dependsOnMethods = {"TC7_ValidateCheckoutSuccessfullwithValidData"})
    public void TC8_ValidateRemoveProductformCartSuccessfull() {
        String[] productNeeded = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"};
        String[] targetRemoveProduct = {"Sauce Labs Bolt T-Shirt"};

        CheckOutPage checkOutPage = new CheckOutPage(driver);
        HomePage homePage = checkOutPage.clickbtn_backToHome(); // Kembali ke halaman utama
        homePage.addProducttoCartbyName(productNeeded); // Tambahkan produk ke keranjang
        homePage.removeProductFormCartbyName(targetRemoveProduct); // Hapus produk dari keranjang
        Assert.assertEquals(homePage.getCountProductCart(), "2"); // Verifikasi jumlah produk di keranjang
    }

    // Validasi penghapusan produk di halaman keranjang berhasil
    @Test(dependsOnMethods = {"TC8_ValidateRemoveProductformCartSuccessfull"})
    public void TC9_ValidateRemoveProductinCartPageSuccessfull() {
        String[] targetRemoveProduct = {"Sauce Labs Backpack"};
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.goToCart(); // Akses halaman keranjang
        cartPage.removeProductFormCartbyName(targetRemoveProduct); // Hapus produk dari keranjang
        Assert.assertEquals(homePage.getCountProductCart(), "1"); // Verifikasi jumlah produk di keranjang
    }
}















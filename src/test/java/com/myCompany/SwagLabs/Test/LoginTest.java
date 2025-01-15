package com.myCompany.SwagLabs.Test;

import com.myCompany.SwagLabs.PageObjects.HomePage;
import com.myCompany.SwagLabs.TestComponent.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // Validasi login berhasil dengan data valid
    @Test
    public void TC1_ValidateLoginSuccessfulwithvalidData() {
        HomePage homePage = loginPage.validLogin("standard_user", "secret_sauce"); // Login dengan kredensial valid
        Assert.assertEquals(homePage.getHomeHeaderTextDashboard(), "Swag Labs"); // Verifikasi teks header halaman utama
    }

    // Validasi logout berhasil setelah login
    @Test(dependsOnMethods = { "TC1_ValidateLoginSuccessfulwithvalidData" })
    public void TC2_ValidateLogOutSuccessful() {
        HomePage homePage = new HomePage(driver);
        homePage.click_btnburgerMenuHeader(); // Klik tombol menu di header
        homePage.click_a_Logout(); // Klik tombol logout
        Assert.assertEquals(loginPage.getHeaderTextLogin(), "Swag Labs"); // Verifikasi teks header halaman login
    }

    // Validasi login gagal dengan username dan password kosong
    @Test(dependsOnMethods = { "TC2_ValidateLogOutSuccessful" })
    public void TC3_ValidateLoginUnsuccessfulwithinvalidDataNullusernamePassword() {
        loginPage.clickbtnLogin(); // Klik tombol login tanpa mengisi username dan password
        Assert.assertEquals(loginPage.getErrorMessageLogin(), "Epic sadface: Username is required"); // Verifikasi pesan kesalahan
    }

    // Validasi login gagal dengan username kosong
    @Test(dependsOnMethods = { "TC2_ValidateLogOutSuccessful" })
    public void TC4_ValidateLoginUnsuccessfulwithinvalidDataNullusername() {
        loginPage.clearUsernamePassword(); // Hapus data username dan password
        loginPage.enterPassword("secret_sauce"); // Isi password saja
        loginPage.clickbtnLogin(); // Klik tombol login
        Assert.assertEquals(loginPage.getErrorMessageLogin(), "Epic sadface: Username is required"); // Verifikasi pesan kesalahan
    }

    // Validasi login gagal dengan password kosong
    @Test(dependsOnMethods = { "TC2_ValidateLogOutSuccessful" })
    public void TC5_ValidateLoginUnsuccessfulwithinvalidDataNullPassword() {
        loginPage.clearUsernamePassword(); // Hapus data username dan password
        loginPage.enterUserName("standard_user"); // Isi username saja
        loginPage.clickbtnLogin(); // Klik tombol login
        Assert.assertEquals(loginPage.getErrorMessageLogin(), "Epic sadface: Password is required"); // Verifikasi pesan kesalahan
    }

    // Validasi login gagal dengan username salah
    @Test(dependsOnMethods = { "TC2_ValidateLogOutSuccessful" })
    public void TC6_ValidateLoginUnsuccessfulwithinvalidDatausername() {
        loginPage.clearUsernamePassword(); // Hapus data username dan password
        loginPage.enterUserName("premium_user"); // Isi username yang salah
        loginPage.enterPassword("secret_sauce"); // Isi password yang benar
        loginPage.clickbtnLogin(); // Klik tombol login
        Assert.assertEquals(loginPage.getErrorMessageLogin(), "Epic sadface: Username and password do not match any user in this service"); // Verifikasi pesan kesalahan
    }

    // Validasi login gagal dengan password salah
    @Test(dependsOnMethods = { "TC2_ValidateLogOutSuccessful" })
    public void TC7_ValidateLoginUnsuccessfulwithinvalidDataPassword() {
        loginPage.clearUsernamePassword(); // Hapus data username dan password
        loginPage.enterUserName("standard_user"); // Isi username yang benar
        loginPage.enterPassword("tomato_sauce"); // Isi password yang salah
        loginPage.clickbtnLogin(); // Klik tombol login
        Assert.assertEquals(loginPage.getErrorMessageLogin(), "Epic sadface: Username and password do not match any user in this service"); // Verifikasi pesan kesalahan
    }

    // Validasi login gagal dengan username dan password salah
    @Test(dependsOnMethods = { "TC2_ValidateLogOutSuccessful" })
    public void TC8_ValidateLoginUnsuccessfulwithinvalidDatausernamepassword() {
        loginPage.clearUsernamePassword(); // Hapus data username dan password
        loginPage.enterUserName("premium_user"); // Isi username yang salah
        loginPage.enterPassword("tomato_sauce"); // Isi password yang salah
        loginPage.clickbtnLogin(); // Klik tombol login
        Assert.assertEquals(loginPage.getErrorMessageLogin(), "Epic sadface: Username and password do not match any user in this service"); // Verifikasi pesan kesalahan
    }
}


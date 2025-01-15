package com.myCompany.SwagLabs.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
     WebDriver driver;
     HomePage homePage;

    // Elemen halaman yang diidentifikasi menggunakan anotasi @FindBy
    @FindBy(id ="user-name") // Input field untuk username
    private WebElement userName;

    @FindBy(id ="password") // Input field untuk password
    private WebElement password;

    @FindBy(id = "login-button") // Tombol login
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']") // Elemen untuk pesan error
    private WebElement h3_errormessage;

    @FindBy(css = ".login_logo") // Elemen untuk header teks login
    private WebElement headerText_login;

    // Konstruktor untuk menginisialisasi elemen halaman menggunakan PageFactory
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Metode untuk login dengan kredensial valid dan navigasi ke HomePage
    public HomePage validLogin(String userName, String password) {
        this.userName.sendKeys(userName); // Mengisi username
        this.password.sendKeys(password); // Mengisi password
        this.loginButton.click(); // Klik tombol login
        return homePage = new HomePage(driver); // Mengembalikan instance HomePage
    }

    // Metode untuk mengisi username secara manual
    public void enterUserName(String userName) {
        this.userName.sendKeys(userName);
    }

    // Metode untuk mengisi password secara manual
    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }

    // Mendapatkan teks header pada halaman login
    public String getHeaderTextLogin() {
        return headerText_login.getText();
    }

    // Klik tombol login
    public void clickbtnLogin() {
        loginButton.click();
    }

    // Mendapatkan pesan error dari elemen error message
    public String getErrorMessageLogin() {
        return h3_errormessage.getText();
    }

    // Menghapus data pada input username dan password
    public void clearUsernamePassword() {
        userName.sendKeys(Keys.CONTROL, "A", Keys.DELETE); // Select all dan hapus username
        password.sendKeys(Keys.CONTROL, "A", Keys.DELETE); // Select all dan hapus password
    }

    // Navigasi ke aplikasi dengan membuka URL tertentu
    public void goToApps() {
        driver.get("https://www.saucedemo.com/");
    }
}

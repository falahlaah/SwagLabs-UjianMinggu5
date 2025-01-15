package com.myCompany.SwagLabs.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
    WebDriver driver;
    HomePage homePage;

    // Elemen-elemen halaman yang diidentifikasi menggunakan anotasi @FindBy
    @FindBy(id = "first-name") // Input untuk nama depan
            WebElement firstName;

    @FindBy(id = "last-name") // Input untuk nama belakang
    WebElement lastName;

    @FindBy(id = "postal-code") // Input untuk kode pos
    WebElement postaCode;

    @FindBy(id = "continue") // Tombol "Continue" untuk melanjutkan proses checkout
    WebElement btn_continue;

    @FindBy(css = "h3[data-test='error']") // Elemen untuk menampilkan pesan error
    WebElement h3_errorMessage;

    @FindBy(id = "finish") // Tombol "Finish" untuk menyelesaikan pesanan
    WebElement btn_finish;

    @FindBy(css = ".complete-header") // Elemen yang menampilkan pesan konfirmasi pesanan
    WebElement h2_confirmationOrder;

    @FindBy(id = "back-to-products") // Tombol untuk kembali ke halaman produk
    WebElement btn_backtoHome;

    // Konstruktor untuk menginisialisasi elemen menggunakan PageFactory
    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Mengisi data checkout yang valid (nama depan, nama belakang, kode pos) dan mengklik tombol "Continue"
    public void validDataCheckout(String firtsname, String lastName, String postalcode) {
        this.firstName.sendKeys(firtsname);
        this.lastName.sendKeys(lastName);
        this.postaCode.sendKeys(postalcode);
        btn_continue.click();
    }

    // Membersihkan semua data yang ada di input checkout
    public void clearAlldataCheckout() {
        firstName.sendKeys(Keys.CONTROL, "A", Keys.DELETE); // Hapus data nama depan
        lastName.sendKeys(Keys.CONTROL, "A", Keys.DELETE); // Hapus data nama belakang
        postaCode.sendKeys(Keys.CONTROL, "A", Keys.DELETE); // Hapus data kode pos
    }

    // Memasukkan nama depan ke input
    public void enterFirstName(String firtsname) {
        this.firstName.sendKeys(firtsname);
    }

    // Memasukkan nama belakang ke input
    public void enterLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    // Memasukkan kode pos ke input
    public void enterPostalCode(String postaCode) {
        this.postaCode.sendKeys(postaCode);
    }

    // Klik tombol "Continue"
    public void clickbtnContinue() {
        btn_continue.click();
    }

    // Mendapatkan pesan error yang ditampilkan saat validasi gagal
    public String getErrorMessage() {
        return h3_errorMessage.getText();
    }

    // Klik tombol "Finish" untuk menyelesaikan pesanan
    public void clickbtnFinish() {
        btn_finish.click();
    }

    // Mendapatkan teks konfirmasi pesanan
    public String getConfirmationOrder() {
        return h2_confirmationOrder.getText();
    }

    // Klik tombol untuk kembali ke halaman produk dan mengembalikan instance `HomePage`
    public HomePage clickbtn_backToHome() {
        btn_backtoHome.click();
        homePage = new HomePage(driver);
        return homePage;
    }
}

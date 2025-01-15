package com.myCompany.SwagLabs.TestComponent;

import com.myCompany.SwagLabs.PageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;


public class BaseTest {

    // Deklarasi variabel untuk driver WebDriver dan halaman login
    public WebDriver driver;
    public LoginPage loginPage;

    // Metode untuk menginisialisasi driver WebDriver
    public WebDriver intializeDriver() throws IOException {

        // Menentukan nama browser yang akan digunakan
        String browsername = "chrome";

        // Logika untuk memilih driver berdasarkan nama browser
        if (browsername.contains("chrome")) {
            driver = new ChromeDriver(); // Inisialisasi untuk Chrome
        } else if (browsername.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver(); // Inisialisasi untuk Firefox
        } else if (browsername.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver(); // Inisialisasi untuk Edge
        }

        // Mengatur waktu tunggu dan memaksimalkan jendela browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().window().maximize();

        // Mengembalikan objek driver
        return driver;
    }

    // Metode yang dijalankan sebelum semua pengujian dimulai
    @BeforeClass
    public LoginPage launchApp() throws IOException {
        driver = intializeDriver(); // Menginisialisasi driver
        loginPage = new LoginPage(driver); // Membuat instance LoginPage
        loginPage.goToApps(); // Mengarahkan ke aplikasi
        return loginPage; // Mengembalikan halaman login
    }

    // Metode yang dijalankan setelah semua pengujian selesai
    @AfterClass
    public void tearsDrop() {
        driver.close(); // Menutup browser
    }
}
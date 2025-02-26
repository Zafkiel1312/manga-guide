package com.github.zafkiel1312.mangaguidebackend.sources.scraper

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class RequestUtil {
    private val logger = LoggerFactory.getLogger(this::class.java)

    companion object {
        private val seleniumDriver = prepareSeleniumDriver()

        private fun prepareSeleniumDriver(): FirefoxDriver {
            val options = FirefoxOptions()
            options.addArguments("--headless")
            return FirefoxDriver(options)
        }
    }

    private fun clickCookieDenyButton() {
        val driver = seleniumDriver
        if (driver.pageSource?.contains("id=\"consent-deny-all\"") == true)
            driver.findElement(By.id("consent-deny-all")).click()
    }

    fun requestWebsiteWithRetry(url: String, shouldContain: List<String> = listOf(), shouldNotContain: List<String> = listOf(), scrollDown: Boolean = false): String {
        val driver = seleniumDriver
        driver.get(url)

        clickCookieDenyButton()

        val maxRetries = 5
        for (i in 1..maxRetries) {
            logger.info("try $i for request to $url")
            if (isDoneLoading(driver.pageSource ?: "", shouldContain, shouldNotContain)) {
                if (scrollDown) {
                    (driver as JavascriptExecutor)
                        .executeScript("window.scrollBy({top: document.body.scrollHeight, behavior: \"smooth\"})")
                    Thread.sleep(2000)
                }
                return driver.pageSource!!
            }
            Thread.sleep(1000)
        }

        logger.error("$url did not load properly")
        return ""
    }

    private fun isDoneLoading(
        html: String,
        shouldContain: List<String>,
        shouldNotContain: List<String>
    ): Boolean =
        shouldContain.all {
            html.contains(it)
        } && shouldNotContain.none {
            html.contains(it)
        }

}
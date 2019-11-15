package com.lemonfactory.pegass.client

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlElement
import com.gargoylesoftware.htmlunit.html.HtmlForm
import com.gargoylesoftware.htmlunit.html.HtmlInput
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.lemonfactory.pegass.client.errorHandlers.PegassCssErrorHandlers

const val connectUrl = "https://id.authentification.croix-rouge.fr/my.policy"

class PegassSession(
        private val username: String,
        private val password: String
) {
    private val webClient = WebClient(BrowserVersion.CHROME)

    init {
        webClient.options.isJavaScriptEnabled = false
        webClient.options.isThrowExceptionOnFailingStatusCode = false
        webClient.options.isThrowExceptionOnScriptError = false
        webClient.options.isUseInsecureSSL = true
        webClient.cookieManager.isCookiesEnabled = true
        webClient.cssErrorHandler = PegassCssErrorHandlers()

        val form = webClient.getPage<HtmlPage>(connectUrl)
                .getFirstByXPath<HtmlElement>("//a[@href='/']")
                .click<HtmlPage>()
                .getFirstByXPath<HtmlForm>("//form")

        form.getInputByName<HtmlInput>("username").valueAttribute = this.username
        form.getInputByName<HtmlInput>("password").valueAttribute = this.password
        form.getFirstByXPath<HtmlElement>("//input[@class='credentials_input_submit']")
                .click<HtmlPage>()

        webClient.getPage<HtmlPage>("https://pegass.croix-rouge.fr/")
                .getFirstByXPath<HtmlElement>("//input[@value='Continue']")
                .click<HtmlPage>()
    }

    fun getPage(url: String): String {
        webClient.getPage<HtmlPage>(url)
        return webClient.currentWindow.enclosedPage.webResponse.contentAsString
    }
}

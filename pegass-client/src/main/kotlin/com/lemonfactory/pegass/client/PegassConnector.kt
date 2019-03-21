package com.lemonfactory.pegass.client

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlElement
import com.gargoylesoftware.htmlunit.html.HtmlForm
import com.gargoylesoftware.htmlunit.html.HtmlInput
import com.gargoylesoftware.htmlunit.html.HtmlPage

const val connectUrl = "https://id.authentification.croix-rouge.fr/my.policy"

fun main(args: Array<String>) {
    java.util.logging.Logger.getLogger("com.gargoylesoftware").level = java.util.logging.Level.OFF

    val pegassConnector = PegassConnector()
    pegassConnector.connect("", "")

    val calendarResponse = pegassConnector.getPage("https://pegass.croix-rouge.fr/crf/rest/seance?debut=2019-03-18&fin=2019-03-25&groupeAction=1&page=0&pageInfo=true&perPage=2147483647&zoneGeoId=75&zoneGeoType=departement")
    println(calendarResponse)
}

class PegassConnector() {
    private val webClient: WebClient = WebClient(BrowserVersion.CHROME)

    fun connect(username: String, password: String) {
        webClient.options.isJavaScriptEnabled = false
        webClient.options.isThrowExceptionOnFailingStatusCode = false
        webClient.options.isThrowExceptionOnScriptError = false
        webClient.options.isUseInsecureSSL = true
        webClient.cookieManager.isCookiesEnabled = true

        val form = webClient.getPage<HtmlPage>(connectUrl)
                .getFirstByXPath<HtmlElement>("//a[@href='/']")
                .click<HtmlPage>()
                .getFirstByXPath<HtmlForm>("//form")

        form.getInputByName<HtmlInput>("username").valueAttribute = username
        form.getInputByName<HtmlInput>("password").valueAttribute = password
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
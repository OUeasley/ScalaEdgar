package models

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import org.jsoup.nodes._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model.Element


/**
	* Sub class for the entry in the xml.
	*
	* @param accessionNumber
	* @param act
	* @param fileNumber
	* @param fileNumberHref
	* @param filingType
	* @param filmNumber
	* @param formName
	* @param size
	*/
case class Content(accessionNumber: String, act: String, fileNumber: String, fileNumberHref: String, filingType:
String, filingHref: String,
                   filmNumber: String, formName: String, size: String)

/**
	* Entry from the xml feed from edgar.
	*
	* @param content
	* The content of the entity.
	* @param id
	* THe id of the entry.
	* @param link
	* The link of the entry to a sub page.
	* @param summaryType
	* The summary type of the edgar entry.
	* @param title
	* The title of the entry.
	* @param updated
	* The last updated date of the entry..
	*/
case class EdgarEntry(content: Content, id: String, link: String, summaryType: String, title: String, updated:
String) {
	def getDocument: String = {
		val browser = JsoupBrowser()
		val doc = browser.get(content.filingHref)
		val items: List[Element] = doc >> elementList("a")
		items.filter(element => {
			element.attr("href").contains(".xml")
		}).head.attr("href")
	}
}

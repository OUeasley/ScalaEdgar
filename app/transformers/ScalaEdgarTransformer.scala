package transformers

import models.{Content, EdgarEntry, EdgarFeed}
import models.company.{Address, CompanyInfo, Name}

import scala.xml.NodeSeq

/**
	* Created by Easle on 8/20/2016.
	*/
object ScalaEdgarTransformer {

	def egdarFeedFromXml(seq: NodeSeq): EdgarFeed = {
		val companyInfo = getCompanyInfo(seq \\ "company-info")
		val entries = getEntries(seq \\ "entry")
		EdgarFeed(companyInfo, entries)
	}

	def getAddress(seq: NodeSeq): Seq[Address] = {
		var addresses = List[Address]()
		seq.foreach { address =>
			val city = address \ "city" text
			val phone = address \ "phone" text
			val state = address \ "state" text
			val street1 = address \ "street1" text
			val street2 = address \ "street2" text
			val zip = address \ "zip" text
			val addressType = address \ "@type" text
			val newAddress = Address(addressType, city, phone, state, street1, street2, zip)
			addresses ::= newAddress
		}

		addresses
	}

	def getNames(seq: NodeSeq): Seq[Name] = {

		null
	}

	def getCompanyInfo(seq: NodeSeq): CompanyInfo = {
		val addresses = getAddress(seq \\ "address")
		val assignedSic = seq \\ "assigned-sic" text
		val assignedSicDesc = seq \\ "assigned-sic-desc" text
		val assignedSicHref = seq \\ "assigned-sic-href" text
		val assitantDirector = seq \\ "assitant-director" text
		val cik = seq \ "cik" text
		val cikHref = seq \ "cik-href" text
		val conformedName = seq \ "conformed-name" text
		val fiscalYearEnd = seq \ "fiscal-year-end" text
		val names = getNames(seq)
		val stateLocation = seq \ "state-location" text
		val stateLocationHref = seq \ "state-location-href" text
		val stateOfIncorporation = seq \ "state-of-incorporation" text

		CompanyInfo(addresses, assignedSic.toInt, assignedSicDesc, assignedSicHref,
			assitantDirector.toInt,
			cik.toInt,
			cikHref, conformedName, fiscalYearEnd.toInt, stateLocation, stateLocationHref, stateOfIncorporation)
	}

	def getEntries(seq: NodeSeq): Seq[EdgarEntry] = {
		var entries = List[EdgarEntry]()
		seq.foreach { entry =>
			val content = getContent(entry \ "content")
			val id = entry \ "id" text
			val link = entry \ "link" \ "@href" text
			val summaryType = entry \ "summary-type" text
			val title = entry \ "title" text
			val updated = entry \ "updated" text
			val newEntry = EdgarEntry(content, id, link, summaryType, title, updated)
			entries ::= newEntry
		}
		entries
	}

	def getContent(seq: NodeSeq): Content = {
		val accessionNumber = seq \ "accession-nunber" text
		val act = seq \ "act" text
		val filingDate = seq \ "filing-date" text
		val filingHref = seq \ "filing-href" text
		val fileNumber = seq \ "file-number" text
		val fileNumberHref = seq \ "file-number-href" text
		val filingType = seq \ "filing-type" text
		val formName = seq \ "form-name" text
		val filmNumber = seq \ "film-number" text
		val size = seq \ "size" text
		val content = Content(accessionNumber, act, fileNumber, fileNumberHref, filingType, filingHref, filmNumber,
			formName,
			size)
		content
	}

}

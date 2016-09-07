package services

/**
	* Created by Easle on 8/15/2016.
	*/
//https://www.sec.gov/cgi-bin/browse-edgar
//?action=getcompany
//&CIK=0000320193
//&CIK=0000320193
//&type=s-8
//&dateb=20011209
//&owner=include
//&start=0
//&count=100
//&output=ato
case class ScalaEdgarConfiguration(ticker: String = "", baseUrl: String = "sec.gov", formType: Option[String] = None, dateBefore: Option[String] = None, owner: String = "include", start: Int = 0, count: Int = 0, output: String = "atom")

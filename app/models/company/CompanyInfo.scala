package models.company

/**
  * Created by Easle on 8/20/2016.
  */
case class CompanyInfo(addresses: Seq[Address] , assignedSic: Int, assignedSicDesc: String, assignedSecHref: String,
                       assitantDirector: Int, cik: Int, cikHref: String, conformedName: String, fiscalYearEnd
                       : Int, stateLocation: String, stateLocationHref: String, stateOfIncorporation: String) {

}

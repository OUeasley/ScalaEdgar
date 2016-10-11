package spatutorial.shared

/**
  * Created by beasley on 9/26/2016.
  */
import boopickle.Default._

sealed trait FilingType

case object Filing10Q extends FilingType

case object Filing10K extends FilingType

case class Filing(company: String, date: String, filingType: FilingType,link: String)

object FilingType {
  implicit val filingTypePicker: Pickler[FilingType] = generatePickler[FilingType]
}

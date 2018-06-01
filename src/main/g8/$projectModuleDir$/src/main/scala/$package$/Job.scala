package $package$
import $package$.Utils
import $packageCommon$.utils.spark._
import com.typesafe.scalalogging.StrictLogging

object Job extends SparkInstrumentedJob with StrictLogging {

  override def jobName = "$projectModule$ job"

  override def run(args: Array[String]) = {

    //timer
    val start = System.currentTimeMillis()


    //....


    //show Global information
    val end = System.currentTimeMillis()
    val duration = end - start
    logger.info(s"[$projectModule$] end of the job, DURATION in millisecond : "+ duration )
  }
}
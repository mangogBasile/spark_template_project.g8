package $package$.$modulePackage$

import $package$.common.utils.spark.SparkInstrumentedJob
import $package$.$modulePackage$.Utils
import $package$.$modulePackage$.Settings

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
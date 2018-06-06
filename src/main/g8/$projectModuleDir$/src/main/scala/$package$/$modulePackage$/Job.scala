package $package$.$modulePackage$

import $package$.common.utils.spark.SparkInstrumentedJob
import $package$.common.utils.prometheus.PrometheusTools
import io.prometheus.client.Gauge
import io.prometheus.client.exporter.PushGateway
import $package$.$modulePackage$.Utils
import $package$.$modulePackage$.Settings
import com.typesafe.scalalogging.StrictLogging


object Job extends SparkInstrumentedJob with StrictLogging {

  override def jobName = "$projectModule$ job"

  override def run(args: Array[String]) = {

    //timer
    val start = System.currentTimeMillis()

    //we create our metric
    val metrics  = new scala.collection.mutable.HashMap[String, Gauge]
    val pg = new PushGateway(Settings.config.prometheus.pushGatewayUrl)


    //....


    //show Global information
    val end = System.currentTimeMillis()
    val duration = end - start

    //we send metrics
    sendLineCountPrometheus(metrics, Settings.config.prometheus.metricName, pg)
    logger.info(s"[$projectModule$] end of the job, DURATION in millisecond : "+ duration )
  }
}
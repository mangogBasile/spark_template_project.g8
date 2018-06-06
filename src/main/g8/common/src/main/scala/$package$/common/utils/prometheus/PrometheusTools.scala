package $package$.common.utils.prometheus

import io.prometheus.client.Gauge
import io.prometheus.client.exporter.PushGateway
import org.apache.spark.sql.DataFrame
import org.apache.spark.rdd.RDD
import scala.collection.mutable.HashMap
import com.typesafe.scalalogging.StrictLogging


object PrometheusTools extends StrictLogging {

  def addDataFrameLineCount(df: DataFrame, hm: HashMap[String, Gauge], tableName: String)
  {
    val pg = hm get tableName match {
      case Some(gauge) => gauge.inc(df.count)
      case None =>
        var dfLinesCounter: Gauge = Gauge.build()
          .name("lines_imported_" + tableName)
          .help("Number of lines in the data frame (table " + tableName + ").")
          .register()
        dfLinesCounter.inc(df.count)
        hm(tableName) = dfLinesCounter
    }
  }


  def addRDDLineCount(rdd: RDD[(String, String)], hm: HashMap[String, Gauge], tableName: String)
  {
    val pg = hm get tableName match {
      case Some(gauge) => gauge.inc(rdd.count)
      case None =>
        var dfLinesCounter: Gauge = Gauge.build()
          .name("lines_imported_" + tableName)
          .help("Number of lines in the data frame (table " + tableName + ").")
          .register()
        dfLinesCounter.inc(rdd.count)
        hm(tableName) = dfLinesCounter
    }
  }

  def addRDDLineCount2(rdd: RDD[String], hm: HashMap[String, Gauge], tableName: String)
  {
    val pg = hm get tableName match {
      case Some(gauge) => gauge.inc(rdd.count)
      case None =>
        var dfLinesCounter: Gauge = Gauge.build()
          .name("lines_imported_" + tableName)
          .help("Number of lines in the data frame (table " + tableName + ").")
          .register()
        dfLinesCounter.inc(rdd.count)
        hm(tableName) = dfLinesCounter
    }
  }

  def addListLineCount(listFile: List[String], hm: HashMap[String, Gauge], listName: String )
  {
    val pg = hm get listName match {
      case Some(gauge) => gauge.inc(listFile.length)
      case None =>
        var dfLinesCounter: Gauge = Gauge.build()
          .name("lines_imported_" + listName)
          .help("Number of lines in the list (list " + listName + ").")
          .register()
        dfLinesCounter.inc(listFile.length)
        hm(listName) = dfLinesCounter
    }
  }

  def addValueCount(value: Long, hm: HashMap[String, Gauge], valueName: String )
  {
    val pg = hm get valueName match {
      case Some(gauge) => gauge.inc(value)
      case None =>
        var dfLinesCounter: Gauge = Gauge.build()
          .name("job_import_" + valueName)
          .help("Total Value of " + valueName )
          .register()
        dfLinesCounter.inc(value)
        hm(valueName) = dfLinesCounter
    }
  }

  def sendLineCountPrometheus(hm: HashMap[String, Gauge], metricName: String, pg: PushGateway)
  {
    hm.foreach {
      case (key, gauge) => try {
        pg.push(gauge, metricName)
        logger.info("[" + key + ":" + gauge.get() +"] succeed pushed to prometheus push_gateway")
      } catch {
        case e: Exception => logger.error("Can't send metrics to the prometheus gateway.")
          e.printStackTrace()
      }
        logger.info("[" + key + ":" + gauge.get() +"] + lines inserted in the data frame (pushed to push_gateway)")
    }
  }

  def sendHeartBeat(pg: PushGateway, metricName: String)
  {
    var heartBeat: Gauge = Gauge.build()
      .name(metricName + "_heartbeat")
      .help(metricName + " heartbeat.")
      .register()
    heartBeat.setToCurrentTime()
    if (pg != null) {
      try {
        pg.push(heartBeat, metricName)
      } catch {
        case e: Exception => logger.error("Can't send metrics to the prometheus gateway.")
      }
    }
    logger.info("[" + metricName + "] sent heartbeat")
  }
}

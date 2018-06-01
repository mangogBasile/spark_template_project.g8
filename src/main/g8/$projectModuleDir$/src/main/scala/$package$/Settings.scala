package $package$

import com.typesafe.config.{Config, ConfigFactory}
import configs.{ConfigError, Configs}
import org.slf4j.{Logger, LoggerFactory}

//...


object Settings {

  val log: Logger = LoggerFactory.getLogger(getClass)

  val conf: Config = ConfigFactory.load()

  val config = Configs[importConf].get(conf, "$projectModuleFmt$").valueOrThrow(configErrorsToException)

  def configErrorsToException(err: ConfigError) =
    new IllegalStateException(err.entries.map(_.messageWithPath).mkString(","))
}

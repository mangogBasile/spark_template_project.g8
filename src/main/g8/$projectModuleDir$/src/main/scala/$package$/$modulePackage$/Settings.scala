package $package$.$modulePackage$

//...


object Settings {

  val log: Logger = LoggerFactory.getLogger(getClass)

  val conf: Config = ConfigFactory.load()

  val config = Configs[importConf].get(conf, "$projectModuleFmt$").valueOrThrow(configErrorsToException)

  def configErrorsToException(err: ConfigError) =
    new IllegalStateException(err.entries.map(_.messageWithPath).mkString(","))
}

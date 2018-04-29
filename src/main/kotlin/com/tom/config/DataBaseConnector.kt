package com.tom.config

import com.mysql.cj.jdbc.MysqlDataSource
import java.sql.Connection

class DataBaseConnector {

    val connection: Connection

    init {
        val dataSource = MysqlDataSource()
        dataSource.user = "readuser"
        dataSource.setPassword("changeme123")
        dataSource.serverName = "localhost"
        dataSource.databaseName = "blogdatabase"

        this.connection = dataSource.connection
    }
}
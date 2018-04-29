package com.tom.repository

import com.tom.config.SystemConfig

class BlogPostRepository(systemConfig: SystemConfig) {

    private val connection = systemConfig.dataBaseConnector.connection

    fun getBlogPost(id: Int): String {
        val preparedStatement = connection.prepareStatement("SELECT body FROM blogpost WHERE id = ?;")
        preparedStatement.setInt(1, id)
        val resultSet = preparedStatement.executeQuery()

        val resultList: MutableList<String> = arrayListOf()

        while(resultSet.next()){
            resultList.add(resultSet.getString("body"))
        }

        return resultList.first()
    }

}
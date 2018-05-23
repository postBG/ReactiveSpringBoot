package com.example.reactive.configs

import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.config.SchemaAction


@Configuration
class CassandraConfig: AbstractCassandraConfiguration() {
    override fun getKeyspaceName(): String = "ReactiveTest"
    override fun getSchemaAction(): SchemaAction = SchemaAction.CREATE_IF_NOT_EXISTS
    override fun getEntityBasePackages(): Array<String> = arrayOf("com.example.reactive")
}
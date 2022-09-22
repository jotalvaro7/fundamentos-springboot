package com.fundamentosplatzi.springboot.fundamentos.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * Esta clase representa la conexion a una base de datos de 2 maneras con 2 metodos:
 *
 * 1) 1er Metodo, utilizando las propiedades directamente quemadas en el codigo
 *
 * 2) 2do Metodo, utilizando un archivo llamado connection.properties con las variables y propiedades para la base de datos
 *    Aqui se utiliza una clase de encapsulacion para obtener las propiedades ejm DbConnectionCapsule con el fin de no usar la
 *    anotacion @value ya que cualquier cambio se debe realizar en todas las clases que la implementan.
 */
@Configuration
public class GeneralConfiguration {



    private final DbConnectionCapsule dbConnectionCapsule;

    public GeneralConfiguration(DbConnectionCapsule dbConnectionCapsule) {
        this.dbConnectionCapsule = dbConnectionCapsule;
    }


//    @Bean
//    public DataSource dataSource(){
//        return DataSourceBuilder.create()
//                .driverClassName("org.h2.Driver")
//                .url("jdbc:h2:mem:testdb")
//                .username("sa")
//                .password("")
//                .build();
//    }

    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName(dbConnectionCapsule.getDriver())
                .url(dbConnectionCapsule.getJdbc_url())
                .username(dbConnectionCapsule.getUsername())
                .password(dbConnectionCapsule.getPassword())
                .build();
    }
}

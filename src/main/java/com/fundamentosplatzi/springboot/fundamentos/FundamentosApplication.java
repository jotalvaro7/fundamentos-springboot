package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(User.class)
public class FundamentosApplication implements CommandLineRunner {


    //LLamando logs
    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private User user;

    public FundamentosApplication(ComponentDependency componentDependency, MyBean myBean,
                                  MyBeanWithDependency myBeanWithDependency, User user) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.user = user;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);

    }

    @Override
    public void run(String... args) {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(user.nombreCompleto());
        try {
			//Codigo
            int value = 10/0;
            LOGGER.info("Mi valor : " + value);
        } catch (Exception e) {
            LOGGER.error("Esto es un error al dividir por cero" + e.getMessage());
        }

    }
}

package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import com.fundamentosplatzi.springboot.fundamentos.pojo.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(User.class)
public class FundamentosApplication implements CommandLineRunner {


    //LLamando logs
    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private User user;

    private UserRepository userRepository;

    public FundamentosApplication(ComponentDependency componentDependency, MyBean myBean,
                                  MyBeanWithDependency myBeanWithDependency, User user, UserRepository userRepository) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.user = user;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);

    }

    @Override
    public void run(String... args) {
        //ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlFromUserEntity();
    }

    private void saveUsersInDataBase() {
        UserEntity user1 = new UserEntity("Julio", "julio@mail.com", LocalDateTime.now());
        UserEntity user2 = new UserEntity("Luisa", "luisa@mail.com", LocalDateTime.now());
        UserEntity user3 = new UserEntity("Luisa", "camila@mail.com", LocalDateTime.now());
        UserEntity user4 = new UserEntity("Valeria", "valeria@mail.com", LocalDateTime.now());
        UserEntity user5 = new UserEntity("Andres", "andres@mail.com", LocalDateTime.now());
        UserEntity user6 = new UserEntity("Julian", "julio1@mail.com", LocalDateTime.now());
        UserEntity user7 = new UserEntity("Juan", "julio2@mail.com", LocalDateTime.now());

        List<UserEntity> listUsers = Arrays.asList(user1, user2, user3, user4, user5, user6, user7);
        userRepository.saveAll(listUsers);

    }

    private void getInformationJpqlFromUserEntity(){
        LOGGER.info("Usuario con el metodo findByUserEmail" + userRepository.findByUserEmail("julio@mail.com")
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

        userRepository.findAndSort("Ju", Sort.by("id").descending())
                .forEach(user -> LOGGER.info("usuario con metodo sort: " + user));

        userRepository.findByName("Luisa").forEach(user -> LOGGER.info("Usuario con query method " + user));

        LOGGER.info("usuario con el metodo findByNameAndEmail " + userRepository.findByNameAndEmail("Julio", "julio@mail.com"));

    }

    private void ejemplosAnteriores() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(user.nombreCompleto());
        try {
            //Codigo
            int value = 10 / 0;
            LOGGER.info("Mi valor : " + value);
        } catch (Exception e) {
            LOGGER.error("Esto es un error al dividir por cero" + e.getMessage());
        }
    }
}

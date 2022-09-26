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

import java.time.LocalDate;
import java.time.LocalDate;
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
        UserEntity user1 = new UserEntity("Julio", "julio@mail.com", LocalDate.now());
        UserEntity user2 = new UserEntity("Luisa", "luisa@mail.com", LocalDate.now());
        UserEntity user3 = new UserEntity("Camila", "camila@mail.com", LocalDate.now());
        UserEntity user4 = new UserEntity("Valeria", "valeria@mail.com", LocalDate.now());
        UserEntity user5 = new UserEntity("Andres", "andres@mail.com", LocalDate.now());
        UserEntity user6 = new UserEntity("Julian", "julian@mail.com", LocalDate.now());
        UserEntity user7 = new UserEntity("Juan", "juan@mail.com", LocalDate.now());

        List<UserEntity> listUsers = Arrays.asList(user1, user2, user3, user4, user5, user6, user7);
        userRepository.saveAll(listUsers);

    }

    private void getInformationJpqlFromUserEntity(){
//        LOGGER.info("Usuario con el metodo findByUserEmail" + userRepository.findByUserEmail("julio@mail.com")
//                .orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
//
//        userRepository.findAndSort("Ju", Sort.by("id").descending())
//                .forEach(user -> LOGGER.info("usuario con metodo sort: " + user));
//
//        userRepository.findByName("Luisa").forEach(user -> LOGGER.info("Usuario con query method " + user));
//
//        LOGGER.info("usuario con el metodo findByNameAndEmail " + userRepository.findByNameAndEmail("Julio", "julio@mail.com"));
//
//        userRepository.findByNameLike("%u%")
//                .forEach(userEntity -> LOGGER.info("Usuario con findByNameLike " + userEntity ));
//
//        userRepository.findByNameOrEmail(null, "andres@mail.com")
//                .forEach(userEntity -> LOGGER.info("Usuario con findByNameOrEmail " + userEntity ));

        userRepository
                .findByBirthDateBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2022, 9, 25))
                .forEach(userEntity -> LOGGER.info("Usuarios con findByBirthDateBetween " + userEntity));

        userRepository
                .findByNameLikeOrderByIdDesc("%l%")
                .forEach(user -> LOGGER.info("Usuarios con findByNameLikeOrderByIdDesc y ordenado de forma descendente " + user));

        userRepository
                .findByNameContainingOrderByIdDesc("l")
                .forEach(user -> LOGGER.info("Usuarios con findByNameContainingOrderByIdDesc y ordenado de forma descendente " + user));

        userRepository
                .findByNameContainsOrderByIdAsc("l")
                .forEach(user -> LOGGER.info("Usuarios con findByNameContainsOrderByIdAsc y ordenado de forma ascendente " + user));

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

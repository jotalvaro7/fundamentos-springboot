package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.UserEntity;
import com.fundamentosplatzi.springboot.fundamentos.pojo.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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

    private UserService userService;

    public FundamentosApplication(ComponentDependency componentDependency, MyBean myBean,
                                  MyBeanWithDependency myBeanWithDependency, User user, UserRepository userRepository,
                                  UserService userService) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.user = user;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);

    }

    @Override
    public void run(String... args) {
        //ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlFromUserEntity();
        saveWithErrorTransaccional();
    }

    private void saveWithErrorTransaccional(){
        UserEntity user = new UserEntity("UserTest", "UserTest@domain.com", LocalDate.now());
        UserEntity user2 = new UserEntity("UserTest2", "UserTest2@domain.com", LocalDate.now());
        UserEntity user3 = new UserEntity("UserTest3", "UserTest@domain.com", LocalDate.now());
        UserEntity user4 = new UserEntity("UserTest4", "UserTest4@domain.com", LocalDate.now());

        List<UserEntity> users = Arrays.asList(user, user2, user3, user4);

        try{
            userService.saveTransaccional(users);

        }catch (Exception e){
            LOGGER.error("Esta es una excepcion dentro del metodo transaccional " + e);
        }

        userService.getAllUsers().forEach(usuario -> LOGGER.info("Este es el usuario dentro del metodo transaccional " + usuario));

    }

    private void saveUsersInDataBase() {
        UserEntity user1 = new UserEntity("Julio", "julio@mail.com", LocalDate.of(2022, 9, 26));
        UserEntity user2 = new UserEntity("Luisa", "luisa@mail.com", LocalDate.of(2022, 9, 26));
        UserEntity user3 = new UserEntity("Camila", "camila@mail.com", LocalDate.of(2022, 9, 26));
        UserEntity user4 = new UserEntity("Valeria", "valeria@mail.com", LocalDate.of(2022, 9, 26));
        UserEntity user5 = new UserEntity("Andres", "andres@mail.com", LocalDate.of(2022, 9, 26));
        UserEntity user6 = new UserEntity("Julian", "julian@mail.com", LocalDate.of(2022, 9, 26));
        UserEntity user7 = new UserEntity("Juan", "juan@mail.com", LocalDate.of(2022, 9, 26));

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

        LOGGER.info("El usuarios a partir del named parameter es : " + userRepository.encontrarUsuarioConDTO(
                LocalDate.of(2022, 9, 26), "luisa@mail.com"));

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

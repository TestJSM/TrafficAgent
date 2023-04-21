package com.uco.TrafficAgent.infrastructure.repository.user.controller;

import com.uco.TrafficAgent.application.service.user.comand.ServiceApplicationDeleteUser;
import com.uco.TrafficAgent.application.service.user.comand.ServiceApplicationSaveUser;
import com.uco.TrafficAgent.application.service.user.comand.ServiceApplicationUpdateUser;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoSaveUser;
import com.uco.TrafficAgent.application.service.user.comand.dto.DtoUpdateUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ComandControllerUser {

    private final ServiceApplicationSaveUser serviceApplicationSaveUser;
    private final ServiceApplicationDeleteUser serviceApplicationDeleteUser;
    private final ServiceApplicationUpdateUser serviceApplicationUpdateUser;

    public ComandControllerUser(ServiceApplicationSaveUser serviceApplicationSaveUser, ServiceApplicationDeleteUser serviceApplicationDeleteUser, ServiceApplicationUpdateUser serviceApplicationUpdateUser) {
        this.serviceApplicationSaveUser = serviceApplicationSaveUser;
        this.serviceApplicationDeleteUser = serviceApplicationDeleteUser;
        this.serviceApplicationUpdateUser = serviceApplicationUpdateUser;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public void saveUser(@RequestBody DtoSaveUser dtoSaveUser){
        this.serviceApplicationSaveUser.execute(dtoSaveUser);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id){
        this.serviceApplicationDeleteUser.execute(id);
    }


    @PutMapping("/update")
    public void updateUser(@RequestParam String id, @RequestBody DtoUpdateUser dtoUpdateUser){
        this.serviceApplicationUpdateUser.execute(id, dtoUpdateUser);
    }
}

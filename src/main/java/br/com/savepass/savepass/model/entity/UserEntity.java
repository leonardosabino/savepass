package br.com.savepass.savepass.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document(value = "user")
@TypeAlias(value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -7041368214043842253L;

    @Id
    private String id;

    private String username;

    private String password;

    private boolean active;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastModifiedDate;

    public UserEntity(UserEntity userEntity) {
        this.id = userEntity.id;
        this.username = userEntity.username;
        this.password = userEntity.password;
        this.active = userEntity.active;
        this.createdDate = userEntity.createdDate;
        this.lastModifiedDate = userEntity.lastModifiedDate;
    }

}

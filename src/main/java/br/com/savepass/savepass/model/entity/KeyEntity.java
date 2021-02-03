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

@Document(value = "key")
@TypeAlias(value = "key")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyEntity implements Serializable {

    private static final long serialVersionUID = 3303255090712891306L;

    @Id
    private String id;

    private String idUser;

    private String local;

    private String password;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastModifiedDate;
}

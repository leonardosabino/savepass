package br.com.savepass.savepass.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String id;

    private String username;

    private String password;

    @ApiModelProperty(hidden = true)
    private LocalDate createdDate;

    @ApiModelProperty(hidden = true)
    private LocalDate lastModifiedDate;
}

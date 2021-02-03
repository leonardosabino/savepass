package br.com.savepass.savepass.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    @ApiModelProperty(hidden = true)
    private String id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @ApiModelProperty(hidden = true)
    private LocalDate createdDate;

    @ApiModelProperty(hidden = true)
    private LocalDate lastModifiedDate;
}

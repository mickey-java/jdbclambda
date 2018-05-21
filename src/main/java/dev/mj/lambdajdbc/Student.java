package dev.mj.lambdajdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private Long id;
    private String name;
    private Integer age;
}

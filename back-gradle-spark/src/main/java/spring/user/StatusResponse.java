package spring.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  StatusResponse {

    SUCCESS ("Success"),
    ERROR ("Error");

    private String status;
}

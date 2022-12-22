package cg.park.board_2022.comm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse<T> {
    private String code;
    private String msg;
    private T data;
}

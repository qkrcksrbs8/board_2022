package cg.park.board_2022.comm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDataResponse {
    private String token;
    private String subject;
    private String issuedTime;
    private String expiredTime;
}

package io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCdo {

    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

}

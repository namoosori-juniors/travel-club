package io.namoosori.travelclub.spring.service.sdo;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCdo {
    //
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    public void checkValidation() {
        //
        CommunityMember.checkEmailValidation(email);
    }
}

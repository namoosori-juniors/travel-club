package io.namoosori.travelclub.phase7.da.map.member.jpo;

import io.namoosori.travelclub.phase7.spec.aggregate.club.vo.Address;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("member")
@Getter
@Setter
@NoArgsConstructor
public class MemberJpo implements Serializable {
    //
    @Id
    private String id;
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private List<Address> addresses;

    public MemberJpo(CommunityMember communityMember) {
        //
        BeanUtils.copyProperties(communityMember, this);
    }

    public CommunityMember toDomain(){
        CommunityMember communityMember = new CommunityMember(id);
        communityMember.setEmail(this.email);
        communityMember.setName(this.name);
        communityMember.setNickName(this.nickName);
        communityMember.setBirthDay(this.birthDay);
        communityMember.setPhoneNumber(this.phoneNumber);
        communityMember.setAddresses(this.addresses);

        return communityMember;
    }
}

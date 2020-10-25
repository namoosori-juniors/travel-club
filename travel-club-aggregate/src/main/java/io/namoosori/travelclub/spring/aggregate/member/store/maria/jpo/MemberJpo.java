package io.namoosori.travelclub.spring.aggregate.member.store.maria.jpo;

import io.namoosori.travelclub.spring.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.spec.aggregate.club.vo.Address;
import io.namoosori.travelclub.spring.spec.util.helper.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "COMMUNITY_MEMBER")
public class MemberJpo implements Serializable {
    //
    @Id
    private String id;
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private String addressesJson;

    public MemberJpo(CommunityMember communityMember) {
        //
        BeanUtils.copyProperties(communityMember, this);
        this.addressesJson = JsonUtil.toJson(communityMember.getAddresses());
    }

    public CommunityMember toDomain(){
        CommunityMember communityMember = new CommunityMember(id);
        communityMember.setEmail(this.email);
        communityMember.setName(this.name);
        communityMember.setNickName(this.nickName);
        communityMember.setBirthDay(this.birthDay);
        communityMember.setPhoneNumber(this.phoneNumber);
        communityMember.setAddresses(JsonUtil.fromJsonList(this.addressesJson, Address.class));

        return communityMember;
    }
}

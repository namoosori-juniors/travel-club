package io.namoosori.travelclub.phase7.aggregate.membership.store.mongo.jpo;

import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.vo.RoleInClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("membership")
@Getter
@Setter
@NoArgsConstructor
public class MembershipJpo {
    //
    @Id
    private String id;
    private String clubId;
    private String memberId;
    private RoleInClub role;
    private String joinDate;

    public MembershipJpo(Membership membership) {
        //
        BeanUtils.copyProperties(membership, this);
    }

    public Membership toDomain() {
        //
        Membership membership = new Membership(id);
        membership.setClubId(clubId);
        membership.setMemberId(memberId);
        membership.setRole(role);
        membership.setJoinDate(joinDate);

        return membership;
    }
}

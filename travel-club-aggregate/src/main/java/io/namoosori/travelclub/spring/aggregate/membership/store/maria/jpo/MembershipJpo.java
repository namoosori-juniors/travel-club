package io.namoosori.travelclub.spring.aggregate.membership.store.maria.jpo;

import io.namoosori.travelclub.spring.spec.aggregate.club.Membership;
import io.namoosori.travelclub.spring.spec.aggregate.club.vo.RoleInClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MEMBERSHIP")
public class MembershipJpo {
    //
    @Id
    private String id;
    private String clubId;
    private String memberId;
    @Enumerated
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

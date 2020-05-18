package io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo;

import io.namoosori.travelclub.phase7.spec.aggregate.club.RoleInClub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCdo {
    //
    private String clubId;
    private String memberId;
    private RoleInClub role;
}

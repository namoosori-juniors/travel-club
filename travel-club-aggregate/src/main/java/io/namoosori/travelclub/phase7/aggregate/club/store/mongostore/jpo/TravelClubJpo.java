package io.namoosori.travelclub.phase7.aggregate.club.store.mongostore.jpo;

import io.namoosori.travelclub.phase7.spec.aggregate.club.ClubMembership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Document(collection = "club")
@Getter
@Setter
@NoArgsConstructor
public class TravelClubJpo implements Serializable {
    //
    @Id
    private String usid;
    private String name;
    private String intro;
    private long foundationTime;
    private String boardId;
    private List<ClubMembership> membershipList;

    public TravelClubJpo(TravelClub travelClub) {
        //
        BeanUtils.copyProperties(travelClub, this);
    }

    public TravelClub toDomain() {
        //
        TravelClub travelClub = new TravelClub(usid, name, intro);
        travelClub.setBoardId(boardId);
        travelClub.setFoundationTime(foundationTime);
        travelClub.setMembershipList(membershipList);
        return travelClub;
    }
}

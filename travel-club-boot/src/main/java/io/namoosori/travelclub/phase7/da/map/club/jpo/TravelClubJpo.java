package io.namoosori.travelclub.phase7.da.map.club.jpo;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("clubs")
@Getter
@Setter
@NoArgsConstructor
public class TravelClubJpo implements Serializable {
    //
    @Id
    private String id;
    private String usid;
    private String name;
    private String intro;
    private long foundationTime;
    private String boardId;

    public TravelClubJpo(TravelClub travelClub) {
        //
        BeanUtils.copyProperties(travelClub, this);
    }

    public TravelClub toDomain() {
        //
        TravelClub travelClub = new TravelClub(id);
        travelClub.setUsid(usid);
        travelClub.setName(name);
        travelClub.setIntro(intro);
        travelClub.setFoundationTime(foundationTime);
        travelClub.setBoardId(boardId);

        return travelClub;
    }
}

package io.namoosori.travelclub.spring.aggregate.club.store.maria.jpo;

import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
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
@Table(name = "TRAVEL_CLUB")
public class TravelClubJpo implements Serializable {
    //
    @Id
    private String id;
    private String name;
    private String intro;
    private long foundationTime;

    public TravelClubJpo(TravelClub travelClub) {
        //
        BeanUtils.copyProperties(travelClub, this);
    }

    public TravelClub toDomain() {
        //
        TravelClub travelClub = new TravelClub(id);
        travelClub.setName(name);
        travelClub.setIntro(intro);
        travelClub.setFoundationTime(foundationTime);

        return travelClub;
    }
}

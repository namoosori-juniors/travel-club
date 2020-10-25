package io.namoosori.travelclub.spring.service.sdo;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelClubCdo implements Serializable {
    //
    private String name;
    private String intro;

    public void checkValidation() {
        //
        TravelClub.checkNameValidation(name);
        TravelClub.checkIntroValidation(intro);
    }
}

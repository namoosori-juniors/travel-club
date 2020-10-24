package io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
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
    private String presidentEmail;

    public void checkValidation() {
        //
        if (name.length() < TravelClub.MINIMUM_NAME_LENGTH) {
            //
            throw new IllegalArgumentException("Name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
        }

        if (intro.length() < TravelClub.MINIMUM_INTRO_LENGTH) {
            //
            throw new IllegalArgumentException("Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
        }
    }
}

package io.namoosori.travelclub.phase7.aggregate.sequence.mongoStore.jpo;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("idSequence")
@NoArgsConstructor
public class IdSequenceJpo {
    //
    @Id
    private String key;
    private int sequence;

    public IdSequenceJpo(String key, int sequence) {
        //
        this.key = key;
        this.sequence = sequence;
    }

    public int toSequence() {
        //
        return sequence;
    }
}

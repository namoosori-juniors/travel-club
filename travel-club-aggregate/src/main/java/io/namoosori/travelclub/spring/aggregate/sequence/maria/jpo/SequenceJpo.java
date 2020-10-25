package io.namoosori.travelclub.spring.aggregate.sequence.maria.jpo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SEQUENCE")
public class SequenceJpo {
    //
    @Id
    private String className;
    private int sequence;

    public SequenceJpo(String className, int sequence) {
        //
        this.className = className;
        this.sequence = sequence;
    }

    public int toSequence() {
        //
        return sequence;
    }
}

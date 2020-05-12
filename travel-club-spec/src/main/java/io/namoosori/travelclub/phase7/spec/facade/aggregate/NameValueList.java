package io.namoosori.travelclub.phase7.spec.facade.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NameValueList {
    //
    private List<NameValue> nameValues;

    public NameValueList() {
        this.nameValues = new ArrayList<>();
    }

    public void addNameValue(NameValue nameValue) {
        this.nameValues.add(nameValue);
    }

    public void addNameValue(String name, String value) {
        this.nameValues.add(new NameValue(name, value));
    }

}

package com.example.springbatchpractice.job.parallel;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.example.springbatchpractice.dto.AmountDto;

public class AmountFieldSetMapper implements FieldSetMapper<AmountDto> {

    @Override
    public AmountDto mapFieldSet(FieldSet fieldSet) {
        AmountDto amount = new AmountDto();
        amount.setIndex(fieldSet.readInt(0));
        amount.setName(fieldSet.readString(1));
        amount.setAmount(fieldSet.readInt(2));
        return amount;
    }
}

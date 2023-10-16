package com.example.paymentbatch.batch.group;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.example.paymentbatch.domain.SettleGroup;
import com.example.paymentbatch.domain.repository.SettleGroupRepository;

@Component
@RequiredArgsConstructor
public class SettleGroupItemDBWriter implements ItemWriter<List<SettleGroup>> {

    private final SettleGroupRepository settleGroupRepository;

    @Override
    public void write(Chunk<? extends List<SettleGroup>> chunk) throws Exception {
        final List<SettleGroup> settleGroups = new ArrayList<>();

        chunk.forEach(settleGroups::addAll);

        settleGroupRepository.saveAll(settleGroups);
    }

}

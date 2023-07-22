package com.rene.core.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.rene.core.domain.RequestStatus;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "engagements")
public class Engagement extends BaseEntity{

    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "attendee_id")
    @ManyToOne
    private User attendee;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}

package com.agilesekeri.asugar_api.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sprint")
public class SprintEntity {
    @Id
    @SequenceGenerator(
            name = "sprint_sequence",
            sequenceName = "sprint_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sprint_sequence"
    )
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_project_id"))
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "creator_id", foreignKey = @ForeignKey(name = "fk_creator_id"))
    private AppUserEntity creator;

    @OneToMany(mappedBy = "sprint")
    private Set<IssueEntity> includedIssues;

    private LocalDateTime createdAt;

    private LocalDateTime plannedTo;

    private LocalDateTime endedAt;
}

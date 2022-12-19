package com.agilesekeri.asugar_api.project.epic;

import com.agilesekeri.asugar_api.appuser.AppUserEntity;
import com.agilesekeri.asugar_api.project.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Epic {
    @Id
    @SequenceGenerator(
            name = "epic_sequence",
            sequenceName = "epic_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "epic_sequence"
    )
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_project_id"))
    private Project project;

    @ManyToOne
    @JoinColumn(name = "reporter_id", foreignKey = @ForeignKey(name = "fk_reporter_id"))
    private AppUserEntity reporter;

    @ManyToOne
    @JoinColumn(name = "assigned_id", foreignKey = @ForeignKey(name = "fk_assigned_id"))
    private AppUserEntity assigned;

    //TODO included issues

    private LocalDateTime createdAt;

    private LocalDateTime plannedTo;

    private LocalDateTime endedAt;
}

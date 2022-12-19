package com.agilesekeri.asugar_api.project;

import com.agilesekeri.asugar_api.appuser.AppUserEntity;
import com.agilesekeri.asugar_api.project.epic.Epic;
import com.agilesekeri.asugar_api.project.sprint.Sprint;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class ProjectService {
    private ProjectRepository projectRepository;

    public Project createProject(String projectName, AppUserEntity admin) {
        Project project = new Project(projectName, admin);
        projectRepository.save(project);
        return project;
    }

    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow( () ->
                        new IllegalArgumentException("No project with this ID was found"));
    }

    public List<Project> getUserProjects(AppUserEntity user) {
        return projectRepository.findByMembers_Id(user.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("There are no projects found for the user"));
    }

    public void deleteProject(Long projectId, Long userId){
        Project target = projectRepository.findById(projectId)
                .orElseThrow( () ->
                        new IllegalStateException("No project with id " + projectId + " was found to delete"));

        if(target.getAdmin().getId() == userId)
            projectRepository.deleteById(projectId);
        else
            throw new IllegalStateException("Not qualified to delete the project with the id " + projectId);
    }

    public Set<AppUserEntity> getMemberSet(Long id) {
        Project project = getProject(id);
        return project.getMembers();
    }

    public boolean addMember(Long projectId, AppUserEntity user) {
        Project project = getProject(projectId);
        boolean result = project.addMember(user);
        projectRepository.save(project);
        return result;
    }

    public boolean removeMember(Long projectId, AppUserEntity user) {
        Project project = getProject(projectId);
        boolean result = project.removeMember(user);
        projectRepository.save(project);
        return result;
    }

    public Set<Sprint> getSprintSet(Long projectId) {
        Project project = getProject(projectId);
        return project.getSprints();
    }

    public Set<Epic> getEpicSet(Long projectId) {
        Project project = getProject(projectId);
        return project.getEpics();
    }
}

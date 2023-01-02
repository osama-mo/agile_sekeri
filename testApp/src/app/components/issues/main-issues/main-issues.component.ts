import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/components/auth/shared/auth.service';

@Component({
  selector: 'app-main-issues',
  templateUrl: './main-issues.component.html',
  styleUrls: ['./main-issues.component.css']
})
export class MainIssuesComponent {
  issues = [ {
    id: 2,
    title: "sec issue",
    description: "hey i am the sec issue",
    manHour: 7,
    condition: "in progress",
    issueType: "STORY",
    creatorUsername: "enes@gmail.com",
    assignedTo: "osama.@gmail.com",
    createdAt: ""
  }]
  projectId: string | null = "";
  projectName: String | null = "";

  constructor(private route: ActivatedRoute, private router: Router, private authsurvice: AuthService) {
    this.projectId = this.route.snapshot.queryParamMap.get('projectId');
    this.projectName = this.route.snapshot.queryParamMap.get('projectName');
  }

  ngOnInit(): void {
    document.body.className = "selector";
    this.projectId = this.route.snapshot.queryParamMap.get('projectId');
    this.projectName = this.route.snapshot.queryParamMap.get('projectName');

    this.authsurvice.getIssues(this.projectId!).subscribe(
      data => {
        console.log(data)
        this.issues = data
      }
      , error => {
        new Error(error)
      })
  }

  
 
 
  navigateToRemoveIssue() {
    this.router.navigate(['remove-issue'], { queryParams: { projectId: this.projectId, projectName: this.projectName } })
  }
  navigateToAddIssue() {
    this.router.navigate(['create-issue'], { queryParams: { projectId: this.projectId, projectName: this.projectName } })
  }
  navigateToProjects() {
    this.router.navigate(['list-project'], { queryParams: { projectId: this.projectId, projectName: this.projectName } })
  }
  navigateToBacklog() {
    this.router.navigate(['backlog'], { queryParams: { projectId: this.projectId, projectName: this.projectName } })
  }
  navigateToSprint() {
    this.router.navigate(['active-sprint'], { queryParams: { projectId: this.projectId, projectName: this.projectName } })
  }
  navigateToMembers() {
    this.router.navigate(['memberslist'], { queryParams: { projectId: this.projectId, projectName: this.projectName } })
  }
  navigateToIssues() {
    this.router.navigate(['issues'], { queryParams: { projectId: this.projectId, projectName: this.projectName } })
  }

  getShortDate(date: string) { 
    let shortdate = "";
    let n = 0;
    for(let letter of date){
      shortdate = shortdate + letter
      n++
      if(n== 10){
        break
      }
    }
    return shortdate;
  }
}

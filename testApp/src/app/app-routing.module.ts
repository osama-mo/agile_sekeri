import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './components/auth/signup/signup.component';
import { LoginComponent } from './components/auth/login/login.component';
import { ForgotMyPasswordComponent } from './components/auth/forgot-my-password/forgot-my-password.component';
import { ForgotMyPasswordConfirmationComponent } from './components/auth/forgot-my-password-confirmation/forgot-my-password-confirmation.component';

import { ResetPasswordComponent } from './components/auth/reset-password/reset-password.component';
import { ListProjectComponent } from './components/project/list-project/list-project.component';
import { CreateProjectComponent } from './components/project/create-project/create-project.component';
import { BacklogComponent } from './components/backlog/backlog.component';




const routes: Routes = [
  {path:'',component:ListProjectComponent},
  {path:'signup',component:SignupComponent},
  {path:'forgot-my-password',component:ForgotMyPasswordComponent},
  {path:'forgot-my-password-confirmation',component:ForgotMyPasswordConfirmationComponent},
  {path:'reset-password',component:ResetPasswordComponent},
  {path:'list-project',component:ListProjectComponent},
  {path:'create-project',component:CreateProjectComponent},
  {path:'backlog',component:BacklogComponent}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


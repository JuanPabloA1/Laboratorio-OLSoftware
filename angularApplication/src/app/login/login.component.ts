import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      username: ['',[Validators.required]],
      password: ['',[Validators.required]]
    })
  }

  ngOnInit(): void {
    if (localStorage.getItem('token')) {
      this.router.navigate(['/home'])
    }
  }

  async onLogin(event: any) {
    event.preventDefault();
    await this.loginService.getAuthentication(
      {user: this.form.get('username')?.value,
      pass: this.form.get('password')?.value});
    this.router.navigate(['/home']);
  }

}

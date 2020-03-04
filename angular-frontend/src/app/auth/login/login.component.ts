import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {LoginPayload} from '../../payloads/login-payload';
import {AuthService} from '../../services/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginPayload: LoginPayload;

  constructor(private authService: AuthService, private router: Router) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
    this.loginPayload = {
      username: '',
      password: ''
    };
  }

  ngOnInit() {
  }

  onSubmit(username: string, password: string) {
    this.loginPayload.username = username;
    this.loginPayload.password = password;

    this.authService.login(this.loginPayload).subscribe(data => {
      if (data) {
        this.router.navigateByUrl('');
      } else {
        console.log('login failed');
      }
    });
  }

  assertValidate(username: string, password: string) {
    return !(username != '' && password != '');
  }
}

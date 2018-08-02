import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import {Md5} from 'node_modules/ts-md5/dist/md5';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validForm:boolean = true;
  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
    private authenticateService: AuthenticationService,
    private router: Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required,Validators.email]],
      password: ['', Validators.required]
    });
  }

  handleSubmit(event) {
    event.preventDefault();
    if (this.loginForm.valid) {
      this.loginForm.value['password'] = Md5.hashStr(this.loginForm.value['password']);
      this.authenticateService.authenticateUser(this.loginForm.value).subscribe(response => {
        console.log(response);
        if (response['message'] === 'Login Successful') {
          this.authenticateService.storeUserData(response['token'], response['user']);
          this.router.navigate(['home']);
        }
        else{
          this.validForm=false;
          // this.router.navigate(['login']);
        }
      });
    }
    else{
      this.validForm=false;
    }
  }

}

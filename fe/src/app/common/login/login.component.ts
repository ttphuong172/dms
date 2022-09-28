import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";
import {LoginService} from "../../../service/login.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin = new FormGroup({
    username: new FormControl(''),
    password: new FormControl()
  });

  constructor(
    private authService:AuthService,
    private loginService: LoginService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
  }

  login() {
    if (this.formLogin.get('username')?.value.trim() == '' || this.formLogin.get('username')?.value === null) {
      this.snackBar.open("Username is required !", '', {duration: 3000});
    } else if (this.formLogin.get('password')?.value == '' || this.formLogin.get('password')?.value === null) {
      this.snackBar.open("Password is required !", '', {duration: 3000});
    } else {
      this.loginService.login(this.formLogin.value).subscribe(
        (data: any) => {
          this.authService.setToken(data.jwtToken);
          this.authService.setUsername(data.username);
          this.authService.setRole(data.role);
          this.snackBar.open(data.message, '', {duration: 3000});
          if (data.role == 'ROLE_ADMIN') {
            this.router.navigateByUrl("/campus");
          } else {
            this.router.navigateByUrl("");
          }
        },
        (data)=>{
          this.snackBar.open(data.message, '', {duration: 3000});
        }
      )
    }


  }

}

import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule,HttpClientModule,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})

export class RegisterComponent {

  myData: MyData = { status: '', message: '', data: '' };
  apiUrl: String = environment.apiUrl;

  userObj = { username: '', password: '', confirmpassword: '', age: ''};

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit(form: any): void {
    if (form.valid) {
      console.log('Username:', this.userObj.username);
      console.log('Password:', this.userObj.password);
      if(this.userObj.password!=this.userObj.confirmpassword){
        console.error('Password Not Match');
        alert('Password Not Match');
      }else{
        const url = `${this.apiUrl}/register/${this.userObj.username}/${this.userObj.password}/${this.userObj.age}`;
        this.http.post(url, {})
          .subscribe((response : any) => {
            this.myData = response;
            if(this.myData.status=='200'){
              this.router.navigate(['/login']);
            }else{
              alert(this.myData.message);
            }
            // Handle successful login
          }, error => {
            console.error('Register failed', error);
            // Handle login error
          });
        }
    }
  }
}

export interface MyData {
  message: string;
  status: string;
  data: string;
}
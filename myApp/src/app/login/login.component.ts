import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,HttpClientModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  myData: MyData = { status: '', message: '', data: '' };
  apiUrl: String = environment.apiUrl;

  loginObj = { username: '', password: '' };

  constructor(private http: HttpClient, private router: Router) {}
  

  onSubmit(form: any): void {
    if (form.valid) {
      const url = `${this.apiUrl}/login/${this.loginObj.username}/${this.loginObj.password}`;
      this.http.post(url, {})
        .subscribe((response : any) => {
          this.myData = response;
          if(this.myData.status=='200'){
            this.router.navigate(['/view']);
          }else{
            alert(this.myData.message);
          }
          
        }, error => {
          console.error('Login failed', error);
          // Handle login error
        });
    }
  }

}

export interface MyData {
  message: string;
  status: string;
  data: string;
}
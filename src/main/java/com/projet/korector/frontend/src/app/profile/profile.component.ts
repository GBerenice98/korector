import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';

const AUTH_API = 'http://localhost:8085/';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username = '';
  userRole : string;
  name = '';
  imageURL = '';
  email = '';
  htmlURL = '';
  error = '';
  roles: String;
  isLoggedIn = false;
  isLoginFailed = false;
  toReload = true;
  showAdminBoard = false;
  showStudentBoard = false;
  showModeratorBoard = false;
  constructor(private http: HttpClient, 
    private tokenStorage: TokenStorageService) { }

  ngOnInit(){
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' , 
      'Authorization' : 'Bearer ' + this.tokenStorage.getToken()})
    };
    //you want reload once after first load
window.onload = function() {
  //considering there aren't any hashes in the urls already
  if(!window.location.hash) {
      //setting window location
      window.location.href  = window.location + '#loaded';
      //using reload() method to reload web page
      window.location.reload();
  }
}
    // reload page 
    //this.reloadPage();
    this.http.get(AUTH_API + 'user/me', httpOptions).subscribe(
      data => {
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles.map(x => x.name).join(',');
        this.roles = this.roles.replace("ROLE_", "");
        console.log("profile",this.roles)
        this.username = this.tokenStorage.getUser().username;
        console.log("User name  = " + this.username);
        this.showStudentBoard = this.roles.includes('ROLE_ETUDIANT');

        this.name = this.tokenStorage.getUser().name;
        this.imageURL = this.tokenStorage.getUser().imageUrl;
        if(this.imageURL == null){
          this.imageURL = 'https://img.icons8.com/clouds/2x/user.png';
        }
        console.log(this.imageURL);
        this.email = this.tokenStorage.getUser().email;
        this.htmlURL = this.tokenStorage.getUser().githubAccount;
        console.log(data);
        console.log("Profile: roles = " + this.roles);
        if(this.name == ''){
          this.name = 'Name not provided';
        }
        if(this.email == ''){
          this.email  = 'Email not provided';
        }
      },
      err => {
        this.error = err.error.message;
        this.isLoginFailed = true;
      }
    )

    //reload to update navBar
    if(this.tokenStorage.getFLAGREF() == "true"){
      console.log("Reload");
        window.location.reload();
        setTimeout(function () {
          window.location.reload();
          
      }, 500);

      this.tokenStorage.toggleFLAGREF();
        
    }




    
  }

  reloadPage() {
    window.location.reload();
  }

}

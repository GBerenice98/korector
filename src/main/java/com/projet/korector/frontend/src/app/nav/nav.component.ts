import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import {User} from '../classes/user';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  user: User;
  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(){
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.user = this.tokenStorageService.getUser();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
     // alert("Connected");
      //alert(user);

     // this.roles = user.roles;
     this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      console.log("Nav bar: showAdminBoard = " + this.showAdminBoard);
      this.showModeratorBoard = this.roles.includes('ROLE_ENSEIGNANT');
      console.log("Nav bar: roles = " + this.roles); 

    

  }
}

logout() {
  this.tokenStorageService.signOut();
  window.location.reload();
}

}

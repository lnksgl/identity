import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {UsersService} from '../users.service';

@Component({
  selector: 'app-source-users',
  templateUrl: './source-users.component.html',
  styleUrls: ['./source-users.component.css']
})
export class SourceUsersComponent implements OnInit {

  page: number = 1;

  constructor(private userService: UsersService) {
  }

  ngOnInit() {
  }

  deleteUser(id) {
    this.userService.deleteUser(id).subscribe();
  }

  searchUser(value: string) {
  }
}

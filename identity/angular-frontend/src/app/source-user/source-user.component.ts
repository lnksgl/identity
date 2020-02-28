import {Component, OnInit} from '@angular/core';
import {UserPayload} from './user-payload';
import {UsersService} from '../users.service';
import {Router} from '@angular/router';
import {LocalStorageService} from 'ngx-webstorage';
import {Observable} from 'rxjs';
import {PostPayload} from '../add-post/post-payload';
import {UserAveragePayload} from './user-average-payload';
import {FormControl, FormGroup} from '@angular/forms';
import * as Tesseract from '../../js/tesseract/src/Tesseract.js';
import {ImageLike} from 'tesseract.js';

@Component({
  selector: 'app-source-user',
  templateUrl: './source-user.component.html',
  styleUrls: ['./source-user.component.css']
})
export class SourceUserComponent implements OnInit {

  userForm: FormGroup;
  userPayload: UserPayload;
  user: UserAveragePayload;

  constructor(private userService: UsersService, private router: Router, private localStorageService: LocalStorageService) {
  }

  ngOnInit() {
    this.userService.getCurrentUsername(this.localStorageService.retrieve('username')).subscribe((data: UserAveragePayload) => {
      this.user = data;
    }, (error: any) => {
      console.log('Failure Response');
    });
    this.userForm = new FormGroup({
      username: new FormControl(),
      email: new FormControl(),
      average: new FormControl()
    });

  }

  onFileChanged(file: ImageLike, username: string, email: string) {
      Tesseract.recognize(
        file,
        'rus',
        {logger: m => console.log(m)}
      ).then(({data: {text}}) => {
        console.log(text);

        this.user.average = text;
        this.userService.updateUser(this.user).subscribe(data => {
          this.router.navigateByUrl('v1/user');
        }, error => {
          console.log('Failure Response');
        });
      });
    }
}

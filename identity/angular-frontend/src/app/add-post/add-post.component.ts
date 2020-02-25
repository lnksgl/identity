import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, NgModel} from '@angular/forms';
import {PostPayload} from './post-payload';
import {AddPostService} from '../add-post.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  addPostForm: FormGroup;
  postPayload: PostPayload;
  title = new FormControl('');
  content = new FormControl('');

  constructor(private addPostService: AddPostService, private router: Router) {
    this.addPostForm = new FormGroup({
      title: this.title,
      content: this.content
    });
    this.postPayload = {
      id: '',
      title: '',
      content: ''
    };
  }

  ngOnInit() {
  }

  addPost(title: string, body: string) {
    this.postPayload.content = body;
    this.postPayload.title = title;
    this.addPostService.addPost(this.postPayload).subscribe(data => {
      this.router.navigateByUrl('');
    }, error => {
      console.log('Failure Response');
    });
  }

  assertValidate(title: string, category: string, body: string) {
    return !(title != '' && category != '' && body != '');
  }
}

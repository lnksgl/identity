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
  name = new FormControl('');
  content = new FormControl('');

  constructor(private addPostService: AddPostService, private router: Router) {
    this.addPostForm = new FormGroup({
      name: this.name,
      content: this.content
    });
    this.postPayload = {
      id: '',
      name: '',
      content: '',
      average: ''
    };
  }

  ngOnInit() {
  }

  addPost(name: string, content: string) {
    this.postPayload.name = name;
    this.postPayload.content = content;
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

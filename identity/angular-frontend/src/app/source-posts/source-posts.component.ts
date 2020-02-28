import { Component, OnInit } from '@angular/core';
import {PostPayload} from '../add-post/post-payload';
import {Observable} from 'rxjs';
import {AddPostService} from '../add-post.service';
import {SourcePayload} from './source-payload';
import {LocalStorageService} from 'ngx-webstorage';
import {Router} from '@angular/router';

@Component({
  selector: 'app-source-posts',
  templateUrl: './source-posts.component.html',
  styleUrls: ['./source-posts.component.css']
})
export class SourcePostsComponent implements OnInit {

  page: number = 1;
  post: PostPayload;
  enquiry: SourcePayload;

  posts: Observable<Array<PostPayload>>;

  constructor(private postService: AddPostService, private localStorageService: LocalStorageService, private router: Router) {
  }

  ngOnInit() {
    this.posts = this.postService.getAllPosts();
    this.posts.forEach(data => {
      console.log(data);
    });
    this.enquiry = {
      username: this.localStorageService.retrieve('username'),
      name: ''
    };
  }

  create(name: string) {
    this.enquiry.name = name;
    this.postService.createEnquiry(this.enquiry).subscribe(data => {
      if (data) {
        this.router.navigateByUrl('/v1/posts');
      } else {
        console.log('login failed');
      }
    });
  }

  deletePost(id) {
    this.postService.deletePost(id).subscribe();
  }

  searchTitle(value: string) {
    if (value === '') {
      this.ngOnInit();

    } else {
      this.posts = this.postService.getTitle(value);
    }
  }

  searchCategory(value: string) {
    if (value === undefined) {
      this.ngOnInit();
    } else {
      this.posts = this.postService.getCategory(value);
    }
  }
}

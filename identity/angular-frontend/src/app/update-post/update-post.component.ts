import { Component, OnInit } from '@angular/core';
import {PostPayload} from "../add-post/post-payload";
import {ActivatedRoute, Router} from "@angular/router";
import {AddPostService} from "../add-post.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-update-post',
  templateUrl: './update-post.component.html',
  styleUrls: ['./update-post.component.css']
})
export class UpdatePostComponent implements OnInit {

  updatePostForm: FormGroup;
  permaLink: number;
  post: PostPayload;
  name = new FormControl('');
  content = new FormControl('');

  constructor(private activatedRoute: ActivatedRoute, private postService: AddPostService, private router: Router) {
    this.updatePostForm = new FormGroup({
      title: this.name,
      body: this.content
    });
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.permaLink = params.id;
    });

    this.postService.getPost(this.permaLink).subscribe((data: PostPayload) => {
      this.post = data;
    }, (error: any) => {
      console.log('Failure Response');
    });
  }

  updatePost(name: string, content: string) {
    this.post.content = content;
    this.post.name = name;
    this.postService.updatePost(this.post).subscribe(data => {
      this.router.navigateByUrl('/');
    }, error => {
      console.log('Failure Response');
    });
  }

  assertValidate(title: string, category: string, body: string) {
    return !(title != '' && category != '' && body != '');
  }

  deletePost(id) {
    this.postService.deletePost(id).subscribe();
  }
}

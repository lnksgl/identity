import { Component, OnInit } from '@angular/core';
import {GroupPayload} from "../payloads/group-payload";
import {ActivatedRoute, Router} from "@angular/router";
import {GroupService} from '../services/group/group.service';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-update-post',
  templateUrl: './update-group.component.html',
  styleUrls: ['./update-group.component.css']
})
export class UpdateGroupComponent implements OnInit {

  updatePostForm: FormGroup;
  permaLink: number;
  post: GroupPayload;
  name = new FormControl('');
  content = new FormControl('');

  constructor(private activatedRoute: ActivatedRoute, private groupService: GroupService, private router: Router) {
    this.updatePostForm = new FormGroup({
      title: this.name,
      body: this.content
    });
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.permaLink = params.id;
    });

    this.groupService.getGroup(this.permaLink).subscribe((data: GroupPayload) => {
      this.post = data;
    }, (error: any) => {
      console.log('Failure Response');
    });
  }

  updatePost(name: string, content: string) {
    this.post.content = content;
    this.post.name = name;
    this.groupService.updateGroup(this.post).subscribe(data => {
      this.router.navigateByUrl('/');
    }, error => {
      console.log('Failure Response');
    });
  }

  assertValidate(title: string, category: string, body: string) {
    return !(title != '' && category != '' && body != '');
  }

  deletePost(id) {
    this.groupService.deleteGroup(id).subscribe();
  }
}

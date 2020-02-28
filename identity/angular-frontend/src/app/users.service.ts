import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserPayload} from './source-user/user-payload';
import {UserAveragePayload} from './source-user/user-average-payload';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private url = 'https://localhost:7070/api/v1/users';

  constructor(private httpClient: HttpClient) { }

  getAllUsers(): Observable<Array<UserPayload>> {
    return this.httpClient.get<Array<UserPayload>>(this.url);
  }

  deleteUser(permaLink: number): Observable<any> {
    return this.httpClient.delete(this.url + '/' + permaLink);
  }

  getUsername(username: string): Observable<Array<UserPayload>> {
    return this.httpClient.get<Array<UserPayload>>(this.url + '/username/' + username);
  }

  getCurrentUsername(username: string): Observable<UserAveragePayload> {
    return this.httpClient.get<UserAveragePayload>(this.url + '/current-username/' + username);
  }

  updateUser(user: UserAveragePayload): Observable<any> {
    return this.httpClient.put(this.url, user);
  }
}

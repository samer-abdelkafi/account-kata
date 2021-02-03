import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Operation} from '../model/operation';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private URI = '/api/accounts/';

  constructor(private http: HttpClient) {
  }

  getOperation(id: number): Observable<Operation[]> {
    return this.http.get<Operation[]>(this.URI + id);
  }

  deposit(id: number, amount: number) :Observable<void> {
    return this.http.post<void>(this.URI + id + '/deposit', {amount});
  }

  withdrawal(id: number, amount: number) :Observable<void> {
    return this.http.post<void>(this.URI + id + '/withdrawal', {amount});
  }

}

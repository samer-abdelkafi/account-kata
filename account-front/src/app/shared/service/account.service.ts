import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Operation} from '../model/operation';
import {Account} from '../model/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private URI = '/api/accounts/';

  constructor(private http: HttpClient) {
  }

  getAccount(id: number): Observable<Account> {
    return this.http.get<Account>(this.URI + id );
  }

  getOperation(id: number): Observable<Operation[]> {
    return this.http.get<Operation[]>(this.URI + id + '/operations');
  }

  deposit(id: number, amount: number) :Observable<void> {
    return this.http.post<void>(this.URI + id + '/deposit', {amount});
  }

  withdrawal(id: number, amount: number) :Observable<void> {
    return this.http.post<void>(this.URI + id + '/withdrawal', {amount});
  }

}

import {Component, OnInit} from '@angular/core';
import {AccountService} from '../shared/service/account.service';
import {Operation} from '../shared/model/operation';
import {Account} from '../shared/model/account';


@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  private accountId = 1;
  operations: Operation[] = [];
  account: Account;

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.getOperation(this.accountId)
      .subscribe(response => this.operations = response);

    this.accountService.getAccount(this.accountId)
      .subscribe(response => this.account = response);
  }

}

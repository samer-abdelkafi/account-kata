import {Component, OnInit} from '@angular/core';
import {AccountService} from '../shared/service/account.service';
import {Operation} from '../shared/model/operation';


@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  private accountId = 1;

  balance = 0;

  operations: Operation[] = [];

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.getOperation(this.accountId)
      .subscribe(response => {
        this.operations = response;
        if(response[0] && response[0].amount) {
          this.balance = response[0]?.amount;
        } else {
          this.balance = 0;
        }
      });
  }

}
